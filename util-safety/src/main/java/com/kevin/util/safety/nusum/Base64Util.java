package com.kevin.util.safety.nusum;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * base64工具类
 *
 * @Author:LIUTAO
 * @Description:
 * @Date:Created in 10:06 2018/7/15
 * @Modified By:
 */
public class Base64Util {

    /**
     * 将字节码转换成base64
     *
     * @param bytes
     * @return
     */
    public static String byteToBase64(byte[] bytes) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(bytes);
    }

    /**
     * 将base64转换成字节码
     * @param base64
     * @return
     * @throws IOException
     */
    public static byte[] base64ToBytes(String base64) throws IOException {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        return base64Decoder.decodeBuffer(base64);
    }
}
