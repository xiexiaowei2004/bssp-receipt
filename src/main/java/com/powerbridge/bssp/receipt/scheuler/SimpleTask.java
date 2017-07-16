package com.powerbridge.bssp.receipt.scheuler;

import com.powerbridge.bssp.receipt.controller.ReceiptParserSimple;
import com.powerbridge.bssp.receipt.util.ReceiptMsg2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 第二部 回执初步解析
 * Created by powerbridge on 2017/6/11.
 */
@Component
@Configurable
@EnableScheduling
public class SimpleTask extends AbstractTask {
    private static final Log logger = LogFactory.getLog(SimpleTask.class);

    @Autowired
    private ReceiptParserSimple receiptParserSimple;

    @Override
    @Scheduled(cron = "0/30 * *  * * * ")
    public void execute() {
        try {
            ReceiptMsg2.getInstance().publishInfo("2.回执初步解析开始");
            receiptParserSimple.parse();
            ReceiptMsg2.getInstance().publishInfo("2.回执初步解析完成");
        } catch (Exception e) {
            ReceiptMsg2.getInstance().publishError("2.回执初步解析异常" + e);
            e.printStackTrace();
            logger.error(e);
        }
    }

}
