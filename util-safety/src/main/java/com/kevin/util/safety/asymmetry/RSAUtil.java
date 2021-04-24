package com.kevin.util.safety.asymmetry;

import com.kevin.util.safety.nusum.Base64Util;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA工具类
 *
 * @Author:LIUTAO
 * @Description:
 * @Date:Created in 19:51 2018/7/15
 * @Modified By:
 */
public class RSAUtil {
    private static KeyPair keyPair;
    private static String publicKeyStr;
    private static String privateKeyStr;

    public static String getPublicKeyStr() {
        return publicKeyStr;
    }

    public static void setPublicKeyStr(String publicKeyStr) {
        RSAUtil.publicKeyStr = publicKeyStr;
    }

    public static String getPrivateKeyStr() {
        return privateKeyStr;
    }

    public static void setPrivateKeyStr(String privateKeyStr) {
        RSAUtil.privateKeyStr = privateKeyStr;
    }

    static {
        try {
            keyPair = getKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }

        publicKeyStr = getPublicKey();
        privateKeyStr = getPrivateKey();
    }

    /**
     * 生成密钥对
     * @return
     * @throws Exception
     */
    private static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    /**
     * 获取公钥(Base64编码)
     * @return
     */
    private static String getPublicKey(){
        PublicKey publicKey = keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return Base64Util.byteToBase64(bytes);
    }

    /**
     * 获取私钥(Base64编码)
     * @return
     */
    private static String getPrivateKey(){
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return Base64Util.byteToBase64(bytes);
    }


    /**
     * 公钥加密
     * 加密后的内容经过了base64转码
     * @param msg
     * @return
     * @throws Exception
     */
    public static String publicEncrypt(String msg) throws Exception{
        byte[] content = msg.getBytes();
        byte[] keyBytes = Base64Util.base64ToBytes(publicKeyStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(content);
        return Base64Util.byteToBase64(bytes);
    }

    /**
     * 私钥解密
     *
     * @param publicEncrypt 经过base64转码的密文
     * @return
     * @throws Exception
     */
    public static String privateDecrypt(String publicEncrypt) throws Exception{
        byte[] content = Base64Util.base64ToBytes(publicEncrypt);
        byte[] keyBytes = Base64Util.base64ToBytes(privateKeyStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(content);
        return new String(bytes);
    }

}
