package com.jzt.sync.core;

import com.github.pagehelper.PageInfo;
import com.jzt.sync.util.Response;

import java.util.List;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result genSuccessResult() {
        return Result.newSuccess();
    }

    public static <T> Result genSuccessResult(T data) {
        return Result.newSuccess()
                .putData(data);
    }

    public static Result genSuccessResultByList(List dataList) {
        PageInfo pageInfo = new PageInfo(dataList);
        return Result.newSuccess().putData(pageInfo);
    }

    public static Result genFailResult(String message) {
        return Result.newFail(message);
    }

    public static Response genSuccessResult(String message, Object data, boolean status) {
        Response result = new Response();
        result.setMessage(message);
        result.setSuccess(status);
        result.setData(data);
        return result;
    }

}
