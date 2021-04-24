package com.kevin.util.safety.nusum;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA工具类
 * @Author:LIUTAO
 * @Description:
 * @Date:Created in 9:48 2018/7/15
 * @Modified By:
 */
public class SHAUtil {
    /**
     * 传入文本内容，返回 SHA-256 串
     *
     * @param strText
     * @return
     */
    public static String SHA256(final String strText) {
        return SHA(strText, DigestEnum.SHA256.getName());
    }

    /**
     * 传入文本内容，返回 SHA-512 串
     *
     * @param strText
     * @return
     */
    public static String SHA512(final String strText) {
        return SHA(strText, DigestEnum.SHA512.getName());
    }

    /**
     * 字符串SHA加密
     * @param strText
     * @param strType
     * @return
     */
    private static String SHA(final String strText, final String strType) {
        String strResult = null;
        if (strText != null && strText.length() > 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                messageDigest.update(strText.getBytes());
                byte byteBuffer[] = messageDigest.digest();

                StringBuffer strHexString = new StringBuffer();
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return strResult;
    }

}
