package com.xjw.xrpc.communication;

import java.io.Serializable;

/**
 * 响应结果的封装
 * （后续大概会加上异常之类的？）
 */

public class Response implements Serializable {

    private Object result;

    private Exception exception=null;

    public Response(Object result){
        this.result=result;
    }
    public Response(){

    }
    public boolean hasException(){
        return exception!=null;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }


    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
