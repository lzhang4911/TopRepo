package com.ebay.lzhang.util;

/**
 * Provides a basic logging facility for thsi project.
 * 
 * @author lzhang
 *
 */
public class Log {
    public static void log(String s) {
        System.out.println(s);
    }
    
    public static void error(String s, Exception e) {
        System.out.println("ERROR - " + s);
        e.printStackTrace();
    }
}
