package com.airin;

/**
 * Created by herasimau on 04/05/17.
 */
public class Utils {

    public static String replaceBrackets(String input){
        return input.replaceAll("\\[", "").replaceAll("]", "");
    }
    public static String removeLastChar(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length()-1)=='b') {
            str = str.substring(0, str.length()-1);
        }
        return str;
    }
}
