package com.liutao.nusum;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * MD5加密工具类
 * @Author:LIUTAO
 * @Description:
 * @Date:Created in 9:41 2018/7/15
 * @Modified By:
 */
public class MD5Util {

    /**
     * 加密
     * @param content
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static byte[] encoderByMd5(String content){
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance(DigestEnum.MD5.getName());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[0];
        try {
            bytes = md5.digest(content.getBytes("utf8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 使用MD5和十六进制处理密码
     * @param content
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String encoderByMd5AndHex(String content){
       String hex = HexadecimalUtil.bytesToHex(encoderByMd5(content));
       return hex;
    }

    /**
     * 使用随机盐加密
     * @param password
     * @return
     */
    public static String encoderByMd5WithSalt(String password){
        Random r = new Random();
        StringBuilder sb = new StringBuilder(16);
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        int len = sb.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sb.append("0");
            }
        }
        String salt = sb.toString();
        password = encoderByMd5AndHex(password + salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }

    /**
     * 验证密码正确性
     * @param pwd
     * @param pwdByMd5
     * @return
     */
    public static boolean verifyPwd(String pwd, String pwdByMd5) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = pwdByMd5.charAt(i);
            cs1[i / 3 * 2 + 1] = pwdByMd5.charAt(i + 2);
            cs2[i / 3] = pwdByMd5.charAt(i + 1);
        }
        String salt = new String(cs2);
        return encoderByMd5AndHex(pwd + salt).equals(new String(cs1));
    }


}
