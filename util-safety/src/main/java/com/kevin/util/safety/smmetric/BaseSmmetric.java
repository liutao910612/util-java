package com.kevin.util.safety.smmetric;

import com.kevin.util.safety.nusum.Base64Util;

import javax.crypto.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * 对称加密抽象父类
 * @Author:LIUTAO
 * @Description:
 * @Date:Created in 16:10 2018/7/15
 * @Modified By:
 */
public abstract class BaseSmmetric {

    private static String pwd = "";
    private static String type = "";



    public static void setPwd(String pwd) {
        BaseSmmetric.pwd = pwd;
    }

    public static String getPwd() {
        return pwd;
    }


    public static void setType(String type) {
        BaseSmmetric.type = type;
    }

    /**
     * 获取密钥
     *
     * @param password
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    protected static SecretKey getSecretKey(String password) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKey securekey;
        KeyGenerator kgen = KeyGenerator.getInstance(type);
        if(type.equals("DES")){
            kgen.init(56,new SecureRandom(password.getBytes()));
        }else {
            kgen.init(128, new SecureRandom(password.getBytes()));
        }
        securekey = kgen.generateKey();
        return securekey;
    }

    protected static Cipher getCipher(String password, int encryptMode) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException {
        SecureRandom random = new SecureRandom();
        SecretKey securekey = getSecretKey(password);
        Cipher cipher = Cipher.getInstance(type);
        cipher.init(encryptMode, securekey, random);
        return cipher;
    }

    public static byte[] getDecrypt(String source) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        byte[] dataSource = Base64Util.base64ToBytes(source);
        Cipher cipher = getCipher(getPwd(), Cipher.DECRYPT_MODE);
        return cipher.doFinal(dataSource);
    }

    public static byte[] getEncrypt(String source) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        byte[] datasource = source.getBytes();
        Cipher cipher = getCipher(getPwd(), Cipher.ENCRYPT_MODE);
        return cipher.doFinal(datasource);
    }
}
