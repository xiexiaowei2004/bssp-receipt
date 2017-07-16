package com.powerbridge.bssp.common.util;

import java.io.Serializable;
import java.net.InetAddress;

public class UUIDGenerator {
    private static final UUIDGenerator uuidGenerator = new UUIDGenerator();
    private static final int IP;
    private static final int JVM = (int) (System.currentTimeMillis() >>> 8);
    private final static String sep = "";
    private static short counter = (short) 0;

    static {
        int ipadd;
        try {
            ipadd = IptoInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception e) {
            ipadd = 0;
        }
        IP = ipadd;
    }

    private UUIDGenerator() {
    }

    public static String getUUID() {
        return uuidGenerator.generate().toString();
    }

    private static int IptoInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
        }
        return result;
    }

    /**
     * Unique across JVMs on this machine (unless they load this class
     * in the same quater second - very unlikely)
     */
    private int getJVM() {
        return JVM;
    }

    /**
     * Unique in a millisecond for this JVM instance (unless there
     * are > Short.MAX_VALUE instances created in a millisecond)
     */
    private short getCount() {
        synchronized (UUIDGenerator.class) {
            if (counter < 0) counter = 0;
            return counter++;
        }
    }

    /**
     * Unique in a local network
     */
    private int getIP() {
        return IP;
    }

    /**
     * Unique down to millisecond
     */
    private short getHiTime() {
        return (short) (System.currentTimeMillis() >>> 32);
    }

    private int getLoTime() {
        return (int) System.currentTimeMillis();
    }

    private String format(int intval) {
        String formatted = Integer.toHexString(intval);
        StringBuffer buf = new StringBuffer("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }

    private String format(short shortval) {
        String formatted = Integer.toHexString(shortval);
        StringBuffer buf = new StringBuffer("0000");
        buf.replace(4 - formatted.length(), 4, formatted);
        return buf.toString();
    }

    private Serializable generate() {
        return format(getIP()) + sep +
                format(getJVM()) + sep +
                format(getHiTime()) + sep +
                format(getLoTime()) + sep +
                format(getCount());
    }
}
