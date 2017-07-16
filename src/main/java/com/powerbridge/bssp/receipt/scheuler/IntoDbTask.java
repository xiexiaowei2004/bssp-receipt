package com.powerbridge.bssp.receipt.scheuler;

import com.powerbridge.bssp.receipt.controller.ReciptParserRout;
import com.powerbridge.bssp.receipt.util.ReceiptMsg;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 第一步 回执文件写入数据库
 * Created by powerbridge on 2017/6/11.
 */
@Component
@Configurable
@EnableScheduling
public class IntoDbTask extends AbstractTask {
    private static final Log logger = LogFactory.getLog(IntoDbTask.class);
    @Autowired
    private ReciptParserRout reciptParserRout;

    @Override
    @Scheduled(cron = "0/30 * *  * * * ")
    public void execute() {
        try {
            ReceiptMsg.getInstance().publishInfo("1.回执文件写入数据库开始");
//            ReciptParserRout reciptParserRout = new ReciptParserRout();
            reciptParserRout.execute();
            ReceiptMsg.getInstance().publishInfo("1.回执文件写入数据库完成");
        } catch (Exception e) {
            ReceiptMsg.getInstance().publishError("1.回执文件写入数据库异常" + e);
            e.printStackTrace();
            logger.error(e);
        }
    }

}
