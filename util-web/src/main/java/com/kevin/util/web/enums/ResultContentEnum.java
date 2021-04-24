package com.kevin.util.web.enums;

/**
 * 调用接口返回内容枚举类
 *
 * @author: LIUTAO
 * @Description:
 * @Date: Created in 14:23 2018/6/6
 * @Modified By:
 */
public enum ResultContentEnum {

    RESULT_CODE {
        public String getName() {
            return "code";
        }
    },
    RESULT_MESSAGE {
        public String getName() {
            return "message";
        }
    },
    RESULT_DATA{
        public String getName() {
            return "data";
        }
    };

    public abstract String getName();

}
