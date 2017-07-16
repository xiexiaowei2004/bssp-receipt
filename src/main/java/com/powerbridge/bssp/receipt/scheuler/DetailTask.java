package com.powerbridge.bssp.receipt.scheuler;

import com.powerbridge.bssp.receipt.controller.ReceiptParserDetail;
import com.powerbridge.bssp.receipt.util.ReceiptMsg3;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：DetailTask
 * 类描述：第三步 回执详细处理 返填数据库数据
 * 创建人：danagao
 * 创建时间：2017/6/15 14:20
 *
 * @version 1.0
 */
@Component
@Configurable
@EnableScheduling
public class DetailTask extends AbstractTask {
    private static final Log logger = LogFactory.getLog(DetailTask.class);

    @Autowired
    private ReceiptParserDetail receiptParserDetail;

    @Override
    @Scheduled(cron = "0/30 * *  * * * ")
    public void execute() {
        try {
            ReceiptMsg3.getInstance().publishInfo("3.回执详细处理开始");
            receiptParserDetail.parse();
            ReceiptMsg3.getInstance().publishInfo("3.回执详细处理完成");
        } catch (Exception e) {
            ReceiptMsg3.getInstance().publishError("3.回执详细处理异常" + e);
            e.printStackTrace();
            logger.error(e);
        }
    }
}
