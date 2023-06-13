package com.chains.library.common;

import lombok.Data;
import org.springframework.web.util.pattern.PathPattern;
@Data
public class Result {
    public static final String SUCCESS_CODE = "200";
    public static final String ERROR_CODE = "-1";

    private String code;
    private String msg;
    private Object data;

    public static Result success(){
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        return result;
    }

    public static Result success(Object obj){
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setData(obj);
        return result;
    }
    public static Result error(String msg){
        Result result = new Result();
        result.setMsg(msg);
        result.setCode(ERROR_CODE);
        return result;
    }
    public static Result error(String code,String msg){
        Result result = new Result();
        result.setMsg(msg);
        result.setCode(code);
        return result;
    }

}
