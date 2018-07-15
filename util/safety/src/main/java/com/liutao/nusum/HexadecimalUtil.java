package com.liutao.nusum;

/**
 * 十六进制工具类
 *
 * @Author:LIUTAO
 * @Description:
 * @Date:Created in 10:07 2018/7/15
 * @Modified By:
 */
public class HexadecimalUtil {

    /**
     * 转换成十六进制编码
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes){
        StringBuilder hex = new StringBuilder();
        for (int i =0; i <bytes.length;i++){
            byte b = bytes[i];
            boolean isNegative = false;
            if(b < 0){
                isNegative = true;
            }

            int inte = Math.abs(b);
            if(isNegative){
                inte = inte | 0x80;
            }

            String temp = Integer.toHexString(inte & 0xFF);
            if(temp.length() == 1){
                hex.append("0");
            }

            hex.append(temp.toLowerCase());
        }

        return hex.toString();
    }

    /**
     * 十六进制转换成字节码
     * @param hex
     * @return
     */
    public static byte[] hexToBytes(String hex){
        byte[] bytes = new byte[hex.length()/2];
        for(int i = 0;i < hex.length();i = i +2){
            String subStr = hex.substring(i,i+2);
            boolean isNegative = false;
            int inte = Integer.parseInt(subStr,16);

            if(inte > 127){
                isNegative = true;
            }

            if(inte == 128){
                inte = -128;
            }else if(isNegative){
                inte = 0 - (inte & 0x7F);
            }

            byte b = (byte)inte;
            bytes[i/2] = b;
        }
        return bytes;
    }
}
