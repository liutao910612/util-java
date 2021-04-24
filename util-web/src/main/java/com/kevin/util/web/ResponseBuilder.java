package com.kevin.util.web;
import com.kevin.util.web.enums.ResultTypeEnum;

import java.util.Map;

/**
 * 创建响应实体
 *
 * @author: LIUTAO
 * @Description:
 * @Date: Created in 14:23 2018/6/6
 * @Modified By:
 */
public class ResponseBuilder {

    /**
     * 成功响应
     *
     * @return
     */
    public static RestfulResponse buildOkResponse() {
        return new RestfulResponse(ResultTypeEnum.SUCCESS.getCode(), ResultTypeEnum.SUCCESS.getMessage());
    }

    /**
     * 成功响应（有返回数据data）
     *
     * @return
     */
    public static <T> RestfulDataResponse<T> buildOkDataResponse(T data) {
        return new RestfulDataResponse(ResultTypeEnum.SUCCESS.getCode(), ResultTypeEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 失败响应
     *
     * @param message 失败原因
     * @return
     */
    public static RestfulResponse buildFailResponse(String message) {
        return new RestfulResponse(ResultTypeEnum.FAIL.getCode(), message);
    }

    /**
     * 失败响应（有返回数据data）
     *
     * @param message 失败原因
     * @param data    响应数据
     * @param <T>
     * @return
     */
    public static <T> RestfulDataResponse<T> buildFailDataResponse(String message, T data) {
        return new RestfulDataResponse(ResultTypeEnum.FAIL.getCode(), message, data);
    }

    /**
     * 自定义响应
     *
     * @param code    状态码
     * @param message 信息
     * @return
     */
    public static RestfulResponse buildResponse(int code, String message) {
        return new RestfulResponse(code, message);
    }

    /**
     * 自定义响应      对于service层返回操作的成功，失败，的提示信息 方法
     * map  格式     {
     * "code": int  ,      （必填，来自ResultTypeEnum ）
     * "message": String ，（键值非必填，错误时填写的提示信息（默认错误提示：操作失败|成功提示：操作成功））
     * "data":object       （键值非必填 ，数据）
     * }
     *
     * @param map
     * @return RestfulResponse
     */
    public static RestfulResponse buildResponse(Map<String, Object> map) {
        if (map == null) {
            throw new RuntimeException("map is null");
        }
        try {
            Integer code = (Integer) map.get("code");
            String message = (String) map.get("message");
            Object data = map.get("data");
            if (code == null) {
                throw new RuntimeException("code is null");
            }
            if (code.equals(ResultTypeEnum.SUCCESS.getCode())) {
                return data == null ? new RestfulResponse(ResultTypeEnum.SUCCESS.getCode(), ResultTypeEnum.SUCCESS.getMessage()) :
                        new RestfulDataResponse(ResultTypeEnum.SUCCESS.getCode(), ResultTypeEnum.SUCCESS.getMessage(), data);
            } else if (code.equals(ResultTypeEnum.FAIL.getCode())) {
                if (message == null) {
                    return data == null ? new RestfulResponse(ResultTypeEnum.FAIL.getCode(), "操作失败") :
                            new RestfulDataResponse(ResultTypeEnum.FAIL.getCode(), "操作失败", data);
                } else {
                    return data == null ? new RestfulResponse(ResultTypeEnum.FAIL.getCode(), message) :
                            new RestfulDataResponse(ResultTypeEnum.FAIL.getCode(), message, data);
                }
            }
            return data == null ? new RestfulResponse(code, message) :
                    new RestfulDataResponse(code, message, data);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 自定义响应
     *
     * @param code    状态码
     * @param message 信息
     * @param data    数据
     * @param <T>
     * @return
     */
    public static <T> RestfulDataResponse<T> buildDataResponse(int code, String message, T data) {
        return new RestfulDataResponse<T>(code, message, data);
    }
}
