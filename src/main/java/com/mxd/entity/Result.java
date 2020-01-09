package com.mxd.entity;

import com.alibaba.fastjson.JSONObject;

public class Result {
    public static int SUCCESS_CODE = 0;
    public static final int LACK_REGISTER_CODE = 1;
    public static final int LACK_IMEI = 2;
    public static final int REGISTER_CODE_ERROR = 3;
    public static final int IMEI_ERROR = 4;
    public static final int LACK_REGISTER_FORBIDDEN = 5;
    public static final int VER_TOO_LOW = 6;
    public static int FAIL_CODE = 400;
    private int code;
    private String message;

    public Result(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
