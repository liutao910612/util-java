package com.liutao.nusum.test;

import com.liutao.smmetric.AESUtil;
import com.liutao.smmetric.DESUtil;

/**
 * @Author:LIUTAO
 * @Description:
 * @Date:Created in 10:03 2018/7/15
 * @Modified By:
 */
public class MD5Test {
    public static void main(String[] args) {
//        System.out.println(HexadecimalUtil.bytesToHex(MD5Util.encoderByMd5("liutao")));
//
//        String pwd = MD5Util.encoderByMd5WithSalt("liutao");
//        System.out.println(pwd);
//        System.out.println(MD5Util.verifyPwd("liutao",pwd));
//
//        System.out.println(SHAUtil.SHA256("liutao"));
//        System.out.println(SHAUtil.SHA512("liutao"));

//        DESUtil.setKey("liutaokey");

        testDESUtil();
        testAESUtil();
    }

    public static void testDESUtil() {
        String str = "测试内容";
        DESUtil.setPwd("DASDSFSDFSADF");
        String result = null;
        String resultD = null;
        try {
            result = DESUtil.encrypt(str);
            resultD = DESUtil.decrypt(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("加密后：" + result);
        System.out.println("解密后：" + resultD);
    }

    public static void testAESUtil() {
        String str = "测试内容";
        DESUtil.setPwd("DASDSFSDFSADF");
        String result = null;
        String resultD = null;
        try {
            result = AESUtil.encrypt(str);
            resultD = AESUtil.decrypt(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("加密后：" + result);
        System.out.println("解密后：" + resultD);
    }
}
