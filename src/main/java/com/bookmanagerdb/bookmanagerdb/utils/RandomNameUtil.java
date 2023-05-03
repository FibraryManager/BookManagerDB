package com.bookmanagerdb.bookmanagerdb.utils;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class RandomNameUtil {
    //自动生成名字（中文）
    public static String getRandomChineseCharacters(int length) {
        String ret = "";
        for (int i = 0; i < length; i++) {
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); // 获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); // 获取低位值
            byte[] b = new byte[2];
            b[0] = (Integer.valueOf(hightPos).byteValue());
            b[1] = (Integer.valueOf(lowPos).byteValue());
            try {
                str = new String(b, "GBK"); // 转成中文
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            ret += str;
        }
        return ret;
    }

    //生成随机用户名，数字和字母组成,
    public static String getRandomCharacters(int length) {
        var chars = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz1234567890";
        var random = new Random();
        //参数length，表示生成几位随机数
        var buf = new char[length];
        for (int i = 0; i < length; i++) {
            buf[i] = chars.charAt(random.nextInt(chars.length()));
            if (i == 0 && Character.isDigit(buf[0])) {
                // 不能以数字开头
                i--;
            }
        }
        return new String(buf);
    }
}
