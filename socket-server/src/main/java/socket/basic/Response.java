package socket.basic;

import socket.constant.Errors;

import java.io.Serializable;

/**
 * @Author: HLJ
 * @Date: 2022/5/20 20:57
 */
public class Response implements Serializable {
    private static final long serialVersionUID = 232123213L;
    private int code;
    private String message;
    private Object result;

    private Response(){}

    private Response(int code, String message, Object o) {
        this.code = code;
        this.message = message;
        this.result = o;
    }
    public static Response error(Errors errors) {
        return new Response(errors.getCode(), errors.getMessage(),null);
    }

    public static Response ok(Object o) {
        return new Response(0, null, o);
    }

    public Response(Object o) {
        this.result = o;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }


}
