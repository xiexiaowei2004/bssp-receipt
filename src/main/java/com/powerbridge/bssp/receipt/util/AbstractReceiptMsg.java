package com.powerbridge.bssp.receipt.util;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：AbstractReceiptMsg
 * 类描述：回执消息处理类
 * 创建人：danagao
 * 创建时间：2017/6/29 11:42
 *
 * @version 1.0
 */
public abstract class AbstractReceiptMsg {

    static final Queue<String> queue = new LinkedList<>();
    static final Queue<String> queue2 = new LinkedList<>();
    static final Queue<String> queue3 = new LinkedList<>();
    private final int queueSize = 2000;

    private static String nows() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        return sdf1.format(new Date());
    }

    protected abstract Queue<String> getQueue();

    private void push(String msg) {
        Queue<String> queue = getQueue();
        while (queue.size() >= queueSize) {
            queue.poll();
        }
        queue.add(msg);
    }

    private String stringEmpty(String str) {
        if (str == null) {
            return "null";
        } else {
            return str;
        }
    }

    public List printStack() {
        List list = new ArrayList(getQueue());
        Collections.reverse(list);
        return list;
    }

    public void publishInfo(String msg) {
        getClassName(msg, "");
    }

    public void publishError(String msg) {
        getClassName(msg, "red");
    }

    private void publishMsg(String className, String msg, String color) {
        if ("".equals(color)) {
            if (msg.endsWith("开始") || msg.endsWith("完成")) {
                color = "green";
            } else {
                color = "black";
            }
        }


        String str = "<span style='color:" + color + "'>" + nows() + "<-->" + className + "<-->" + stringEmpty(msg) + "</span>";
        push(str);
    }

    private void getClassName(String msg, String color) {
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        String callName;
        String str;
        for (StackTraceElement ste : stack) {
            callName = ste.getClassName();
            if (!callName.startsWith("org.springframework.")
                    && !callName.startsWith("sun.reflect.")
                    //&& !callName.startsWith("java.lang.")
                    && !callName.startsWith("com.sun.")
                    && !callName.startsWith("org.quartz.")
                    && !callName.contains("BySpringCGLIB")
                    && !callName.equals("java.lang.Thread")
                    && !callName.endsWith("receipt.util.AbstractReceiptMsg")
                    ) {
                str = stringEmpty(ste.getFileName()) + "<-" + stringEmpty(String.valueOf(ste.getLineNumber())) + "->" + stringEmpty(ste.getMethodName());
                publishMsg(str, msg, color);
                if ("".equals(color)) {//如果是异常信息，就一直打印调用信息。
                    break;
                }
            }

        }
    }

}
