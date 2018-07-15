package com.liutao.nusum;

/**
 * @Author:LIUTAO
 * @Description:
 * @Date:Created in 9:53 2018/7/15
 * @Modified By:
 */
public enum DigestEnum {
    MD5("MD5"),
    SHA1("SHA-1"),
    SHA256("SHA-256"),
    SHA384("SHA-384"),
    SHA512("SHA-512");
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 构造方法
    DigestEnum(String name) {
        this.name = name;
    }
}
