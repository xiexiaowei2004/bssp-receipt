package com.powerbridge.bssp.receipt.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.receipt.entity.ManageResult;
import com.powerbridge.bssp.receipt.service.IReceiptParser;
import com.powerbridge.bssp.receipt.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：AbstractReceiptParser
 * 类描述：菜单控制器
 * 创建人：danagao
 * 创建时间：2017/6/7 18:15
 *
 * @version 1.0
 */
@Component
public abstract class AbstractReceiptParser implements IReceiptParser {
    Document document;          //xml数据
    String seqNo;               //单据编号
    String xmlBizType;          //回执类型
    Map infoMap;                //数据集合
    Integer chgTmsCnt;          //变更次数
    Object bscBeanObject;       //表头数据对象
    IService bscService;        //表头服务
    Element bscElement;         //表头数据节点
    String result;              //回执结果
    Element dataElement;        //表体数据节点
    //获取容器（上下文环境）
    @Autowired
    WebApplicationContext webApplicationContext;

    public void setParam(Document document, String seqNo, Map<String, String> infoMap) {
        this.document = document;
        this.xmlBizType = document.getRootElement().getName();
        this.seqNo = seqNo;
        this.infoMap = infoMap;
    }

    /**
     * @param obj
     * @param element
     * @throws Exception
     */
    void getEdiXmlInfoMap(Object obj, Element element) throws Exception {
        Map map = ReceiptUtil.getXmlMapBean(obj);
        wayAll(obj, element, map);
    }

    /**
     * 比较优先级以及获取数据
     *
     * @return
     * @throws Exception
     */
    boolean compareToManageResult() throws Exception {
        //分别获取xml和表数据的优先级并判断优先级
        result = XMLUtils.getElementByXpath("//manageResult", document).getText();
        ManageResult xmlManageResult = ReceiptKindConstant.getManageResult(xmlBizType, result);
        //获取回执所对应的数据库数据
        selectOneBsc();
        //回执状态
        String retChannel = BeanUtils.getProperty(bscBeanObject, "retChannel");
        String retChannelCode = StringUtil.isEmpty(retChannel) ? retChannel : retChannel.trim().substring(0, 1);
        //获取本地数据优先级
        ManageResult dataManageResult = ReceiptKindConstant.getManageResult(xmlBizType, retChannelCode);

        //比较优先级
        if (xmlManageResult.compareTo(dataManageResult) > 0) {
            BeanUtil.setProperty(bscBeanObject, "retChannel", xmlManageResult.getRetChannel());
            //单据状态 不为成功或失败时 值为null 不修改状态
            BeanUtil.setProperty(bscBeanObject, "chkStatus", xmlManageResult.getChkStatus());
            if ((boolean) infoMap.get("isLastData")) {
                //更新表头回执状态
                bscService.updateById(bscBeanObject);
            }
            return true;
        } else {
            //优先级比本地数据低时，不回填本地数据
            return false;
        }
    }

    boolean compareToManageResult(String bscElementName) throws Exception {
        //获取回执xml数据
        bscElement = XMLUtils.getElementByXpath("//" + bscElementName, document);
        chgTmsCnt = Integer.parseInt(XMLUtils.getElementByXpath("//tmsCnt" , document).getText());
        return compareToManageResult();
    }

    boolean compareToManageResult(String bscElementName, String chgTmsCntName) throws Exception {
        //获取回执xml数据
        bscElement = XMLUtils.getElementByXpath("//" + bscElementName, document);
        chgTmsCnt = Integer.parseInt(bscElement.elementText(chgTmsCntName));
        return compareToManageResult();
    }


    /**
     * xml对应的节点中element 解析出数据，  存储到Object（实体类）中，
     * 注意：1：service的值为ediXmlMapDbService
     * 2：对应的Object的实体类中，如果属性是对应的时间在其set方法中，进行以下判断：如
     * public void setCol3(String col3) {
     * this.col3 = DateUtil.timeSwitch(col3);
     * }
     *
     * @Title: wayAll
     * @Description:
     * @param: @param obj
     * @param: @param element
     * @param: @param service
     * @param: @return
     * @param: @throws Exception
     * @return: Object
     */
    private Object wayAll(Object obj, Element element, Map<String, String> map) throws Exception {

        if (null != element) {
            List<Element> elementList = element.elements();
            for (Element e : elementList) {
                BeanUtil.setProperty(obj, map.get(e.getName()), e.getData());

            }

        }
        return obj;
    }

    /**
     * 获取回执所对应的数据库数据，3套表后，预录入表审批同意后，数据会删除。
     */
    void selectOneBsc() throws Exception{
        bscBeanObject = bscService.selectOne(getEntityWrapper());
        if (bscBeanObject == null) {
            saveDtDataElementException(getEntityWrapper(), bscService);
        }
    }

    /**
     * 通用新增表头
     *
     * @param cusObject  正式表头对象
     * @param cusService 正式表头服务
     * @throws Exception
     */
    void saveBsc(Object cusObject, IService cusService) throws Exception {
        saveBsc(cusObject, cusService, chgTmsCnt);
    }

    /**
     * 通用新增表头 重载用于只做新增或只做修改
     *
     * @param cusObject  正式表头对象
     * @param cusService 正式表头服务
     * @param chgTmsCnt  变更次数
     * @throws Exception
     */
    void saveBsc(Object cusObject, IService cusService, Integer chgTmsCnt) throws Exception {
        //把xml中的值添加到对象中
        getEdiXmlInfoMap(bscBeanObject, bscElement);
        //对象属性复制
        BeanUtilsEx.copyProperties(cusObject, bscBeanObject);
        //根据变更次数判断是对正式表的操作
        if (chgTmsCnt == 0 && null == cusService.selectOne(getEntityWrapper())) {
            BeanUtil.setProperty(cusObject, "uid", UUIDGenerator.getUUID());
            cusService.insert(cusObject);
        } else {
            BeanUtil.setProperty(cusObject, "uid", null);
            cusService.update(cusObject, getEntityWrapper());
        }
    }


    private EntityWrapper getEntityWrapper() {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("SEQ_NO", seqNo);
        return entityWrapper;
    }

    /**
     * 保存表体节点数据
     *
     * @param dtEntityWrapper 查询条件
     * @param arcrpService    预录入服务
     * @param cusObject       正式表对象
     * @param cusService      正式表服务
     * @throws Exception
     */
    void saveDtDataElement(EntityWrapper dtEntityWrapper, IService arcrpService, Object cusObject, IService cusService) throws Exception {
        //修改标记代码 0:未修改 1：修改 2：删除 3：新增
        String modfMarkcd = dataElement.elementText("modfMarkcd");
        saveDtDataElement(dtEntityWrapper, arcrpService, cusObject, cusService, modfMarkcd);

    }

    /**
     * 保存表体节点数据 重载用于只做新增或只做修改的
     *
     * @param dtEntityWrapper 查询条件
     * @param arcrpService    预录入服务
     * @param cusObject       正式表对象
     * @param cusService      正式表服务
     * @param modfMarkcd      修改标记
     * @throws Exception
     */
    void saveDtDataElement(EntityWrapper dtEntityWrapper, IService arcrpService, Object cusObject, IService cusService, String modfMarkcd) throws Exception {
        //预录入对象
        Object arcrpObject = arcrpService.selectOne(dtEntityWrapper);
        if (arcrpObject == null) {
            saveDtDataElementException(dtEntityWrapper, arcrpService);
        }
        saveDtDataElement(dtEntityWrapper, arcrpObject, cusObject, cusService, modfMarkcd);
    }

    /**
     * 保存表体节点数据 重载用于同步回执 不用查预录入表
     *
     * @param dtEntityWrapper 查询条件
     * @param arcrpObject     预录入对象
     * @param cusObject       正式表对象
     * @param cusService      正式表服务
     * @param modfMarkcd      修改标记
     * @throws Exception
     */
    void saveDtDataElement(EntityWrapper dtEntityWrapper, Object arcrpObject, Object cusObject, IService cusService, String modfMarkcd) throws Exception {
        //先把数据存到预录入对象中
        getEdiXmlInfoMap(arcrpObject, dataElement);
        //对象属性复制
        BeanUtilsEx.copyProperties(cusObject, arcrpObject);
        if (modfMarkcd.equals("1") || modfMarkcd.equals("2")) {
            BeanUtil.setProperty(cusObject, "uid", null);
            cusService.update(cusObject, dtEntityWrapper);
        } else if (modfMarkcd.equals("3")) {
            BeanUtil.setProperty(cusObject, "uid", UUIDGenerator.getUUID());
            cusService.insert(cusObject);
        }
    }

    /**
     * 通过节点名获取节点列表
     *
     * @param dtElementName
     * @return
     */
    List<Element> getElementList(String dtElementName) throws Exception {
        return XMLUtils.getElementListByXpath(dtElementName, document);
    }

    /**
     * 保存表体异常
     *
     * @param entityWrapper
     * @param service
     * @throws Exception
     */
    void saveDtDataElementException(EntityWrapper entityWrapper, IService service) throws Exception {
        String sqlSegment = entityWrapper.getSqlSegment();
        Map<String, Object> map = entityWrapper.getParamNameValuePairs();
        for (String value : map.keySet()) {
            sqlSegment = sqlSegment.replaceAll("#\\{ew.paramNameValuePairs." + value + "}", map.get(value).toString());
        }
        throw new RuntimeException(service.toString() + "数据不存在：" + sqlSegment);
    }

    /**
     * 把预录入表记录插入历史表
     *
     * @param hisObject  历史表对象
     * @param preService 预录入表服务
     * @param hisService 历史表服务
     * @throws Exception insertHis可能抛出异常
     */
    void insertHis(Object hisObject, IService preService, IService hisService) throws Exception {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("SEQ_NO", seqNo);
        List preObjectList = preService.selectList(entityWrapper);
        insertHis(hisObject, preObjectList, hisService);
    }

    /**
     * 把预录入表记录插入历史表
     *
     * @param hisObject     历史表对象
     * @param preObjectList 预录入表数据
     * @param hisService    历史表服务
     * @throws Exception setProperty可能出异常
     */
    private void insertHis(Object hisObject, List preObjectList, IService hisService) throws Exception {
        if (preObjectList != null) {
            for (Object arcrpBeanObject : preObjectList) {
                BeanUtilsEx.copyProperties(hisObject, arcrpBeanObject);
                BeanUtil.setProperty(hisObject, "uid", UUIDGenerator.getUUID());
                hisService.insert(hisObject);
            }
        }
    }

    /**
     * 把预录入表记录插入历史表
     *
     * @param hisObject  历史表对象
     * @param preObject  预录入表对象
     * @param hisService 历史表服务
     * @throws Exception setProperty可能出异常
     */
    void insertHis(Object hisObject, Object preObject, IService hisService) throws Exception {
        List list = new ArrayList();
        list.add(preObject);
        insertHis(hisObject, list, hisService);
    }

    @Override
    public void before() {
    }

    @Override
    public void after() {
    }

    @Override
    @Transactional
    public PostActionEnum execute() throws Exception {
        before();
        PostActionEnum postActionEnum = postAction();
        after();
        return postActionEnum;
    }

    abstract PostActionEnum postAction() throws Exception;

}
