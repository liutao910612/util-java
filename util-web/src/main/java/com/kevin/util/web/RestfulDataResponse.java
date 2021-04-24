package com.kevin.util.web;


/**
 * 返回数据封装
 *
 * @author: LIUTAO
 * @Description:
 * @Date: Created in 14:23 2018/6/6
 * @Modified By:
 */
public class RestfulDataResponse<T> extends RestfulResponse {

    /**
     * 空构造函数
     */
    public RestfulDataResponse() {
    }

    /**
     * 有参构造函数
     */
    public RestfulDataResponse(int code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    /**
     * 实体类型
     */
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "RestfulDataResponse{" +
                "data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
