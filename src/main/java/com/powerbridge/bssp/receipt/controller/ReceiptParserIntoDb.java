package com.powerbridge.bssp.receipt.controller;

import com.powerbridge.bssp.common.util.FileUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.edi.entity.EdiMessageData;
import com.powerbridge.bssp.edi.entity.EdiMessageLog;
import com.powerbridge.bssp.edi.service.IEdiMessageDataService;
import com.powerbridge.bssp.edi.service.IEdiMessageLogService;
import com.powerbridge.bssp.receipt.util.CommonEnum;
import com.powerbridge.bssp.receipt.util.ReceiptKindConstant;
import com.powerbridge.bssp.receipt.util.ReceiptMsg;
import com.powerbridge.bssp.receipt.util.TransTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：ReceiptParserIntoDb
 * 类描述：读取文件，并将文件放入到报文数据表，此类主要目的是快速读取文件并处理，避免文件挤压。
 * 创建人：danagao
 * 创建时间：2017/6/5 20:59
 *
 * @version 1.0
 */
@Component
public class ReceiptParserIntoDb {

    private static final Logger logger = LoggerFactory.getLogger(ReceiptParserIntoDb.class);
    private Iterator iterator;
    private Map fileMap;
    private String xmlPath;
    private File oActiveFile_ = null;
    //报文数据服务
    @Autowired
    private IEdiMessageDataService ediMessageDataService;
    //报文日志服务
    @Autowired
    private IEdiMessageLogService ediMessageLogService;
    //报文数据
    private EdiMessageData ediMessageData;

    /**
     * 加载路径下的所有文件
     *
     * @return true:false
     */
    private boolean loadAllFileName() {
        boolean _bRetVal = false;
        Enumeration oFileList_ = getFileList(xmlPath);
        if (oFileList_.hasMoreElements()) {
            _bRetVal = true;

            fileMap = new HashMap();
            File oFile;
            String oFileName;
            while (oFileList_.hasMoreElements()) {
                oFile = (File) oFileList_.nextElement();
                oFileName = oFile.getName();
                if (isCanUseFile(oFileName)) {
                    fileMap.put(oFileName, oFile);
                }
            }
            iterator = fileMap.keySet().iterator();
        }
        return _bRetVal;
    }

    /**
     * 获取文件列表
     *
     * @param sDirectory 文件路径
     * @return 文件枚举
     */
    private Enumeration getFileList(String sDirectory) {

        File f = new File(sDirectory);
        if (f.isDirectory()) {
            File[] list = f.listFiles();
            if ((list != null) && (list.length > 0)) {
                Arrays.sort(list);
                CommonEnum _oEnum = new CommonEnum();
                for (File tmpF : list) {
                    if (tmpF.isFile()) {
                        _oEnum.add(tmpF);
                    }
                }
                return _oEnum;
            }
        }
        return new CommonEnum();
    }

    /**
     * 判断文件类型
     *
     * @param strFileName 文件名
     * @return true:false
     */
    private boolean isCanUseFile(String strFileName) {
        boolean bRet = false;
        strFileName = strFileName.toUpperCase();
        if (strFileName.endsWith(".XML")) {
            bRet = true;
        }
        return bRet;
    }

    /**
     * 获取下一个文件
     *
     * @return true:false
     */
    private boolean getNextFile() {
        while (iterator.hasNext()) {
            File oFile = (File) fileMap.get(iterator.next());
            if (oFile.canWrite()) {
                oActiveFile_ = oFile;
                return true;
            }
        }
        return false;
    }

    /**
     * @return
     * @Description 将报文保存到报文数据对象
     */
    private void setXMLFileData(byte[] bytes) throws Exception {
        ediMessageData = new EdiMessageData();
        ediMessageData.setUid(UUIDGenerator.getUUID());                        //id
        ediMessageData.setFileName(oActiveFile_.getName());                    //报文名称
        ediMessageData.setTransType(TransTypeEnum.R);                          //传输类型   S：发送 R：接收
        ediMessageData.setBigData(bytes);                                      //大数据
        ediMessageData.setStorageTime(DateUtil.now());                         //存储时间
        ediMessageData.setStatus(TransTypeEnum.N);                             //处理标识 Y:已处理 N:未处理
        ediMessageData.setPassageway(ReceiptKindConstant.PASSAGEWAY);          //通道
    }

    /**
     * 解析xml的主流程
     */
    public void parse(String sPath) {
        this.xmlPath = sPath;
        //加载文件夹中的所有文件
        if (loadAllFileName()) {
            ReceiptMsg.getInstance().publishInfo("加载文件完成，共：" + fileMap.size() + "个");
            while (getNextFile()) {//循环每一个文件
                EdiMessageLog ediMessageLog = new EdiMessageLog();
                try {

                    File file = oActiveFile_;
                    ReceiptMsg.getInstance().publishInfo("正在处理：" + file.getName());
//                    SAXReader reader = new SAXReader();
                    byte[] bytes = readXml(file);
                    //存储数据到对象中
                    setXMLFileData(bytes);
                    //插入数据
                    if (ediMessageDataService.insert(ediMessageData)) {
                        ediMessageLog.setMessageUid(ediMessageData.getUid());
                        ediMessageLog.setStatus(TransTypeEnum.EDI_INTODB_SUCCESS);
                    } else {
                        ediMessageLog.setStatus(TransTypeEnum.EDI_INTODB_ERROR);
                    }
                    //文件删除备份
                    backFile("bak");
                    ReceiptMsg.getInstance().publishInfo("处理完成：" + file.getName());
                } catch (Exception e) {
                    backFile("err");
                    ediMessageLog.setStatus(TransTypeEnum.EDI_INTODB_ERROR);
                    ediMessageLog.setProcessingLog(e.toString().getBytes());
                    e.printStackTrace();
                    logger.error("receiptParserIntoDb---error", e);
                    ReceiptMsg.getInstance().publishError(oActiveFile_.getName() + "处理失败：" + e.toString());
                } finally {
                    //写报文日志
                    ediMessageLog.setUid(UUIDGenerator.getUUID());
                    ediMessageLog.setProcessingTime(DateUtil.now());
                    ediMessageLog.setFileName(oActiveFile_.getName());
                    ediMessageLogService.insert(ediMessageLog);
                }
            }
        }
    }

    /**
     * 使用流的方式读取XML，解决文件解析异常被锁住问题。
     *
     * @param file XML文件
     * @return XML节点集
     */
    @SuppressWarnings("unchecked")
    private byte[] readXml(File file) throws Exception {
        ByteArrayOutputStream bos = null;
        BufferedInputStream in = null;
        try {
            bos = new ByteArrayOutputStream((int) file.length());
            in = new BufferedInputStream(new FileInputStream(file));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (bos != null) {
                bos.close();
            }
        }
        return bos.toByteArray();
    }


    /**
     * 报文处理完成后备份
     */
    private void backFile(String dirName) {
        try {
            String today = DateUtil.today();
            String bakDir = xmlPath + File.separator + dirName;
            FileUtils.isFolderExitAndCreate(bakDir);
            bakDir += File.separator + today;
            FileUtils.isFolderExitAndCreate(bakDir);

            if (FileUtils.copyFileCover(oActiveFile_, bakDir + File.separator + oActiveFile_.getName(), true)) {
                FileUtils.deleteFile(oActiveFile_);
            }
        } catch (Exception e) {
            logger.error("备份文件异常-" + dirName, e);
            ReceiptMsg.getInstance().publishError(e.toString());
        }
    }

}
