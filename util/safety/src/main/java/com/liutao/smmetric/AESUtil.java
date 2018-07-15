package com.liutao.smmetric;

import com.liutao.nusum.Base64Util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * DES加密工具
 *
 * @Author:LIUTAO
 * @Description:
 * @Date:Created in 15:13 2018/7/15
 * @Modified By:
 */
public class AESUtil extends BaseSmmetric {

    /**
     * 加密
     * 将加密后的内容转换成base64
     * @param source  需要加密的字符串
     * @return
     * @throws Exception
     */
    public static String encrypt(String source) throws Exception {
        setType("AES");
        byte[] endSource = getEncrypt(source);
        return Base64Util.byteToBase64(endSource);
    }



    /**
     * 解密
     * @param source 需要解密的base64
     * @return
     * @throws Exception
     */
    public static String decrypt(String source) throws Exception {
        setType("AES");
        byte[] bytes = getDecrypt(source);
        return new String(bytes);
    }

}
