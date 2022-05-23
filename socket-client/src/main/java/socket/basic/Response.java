package socket.basic;

import java.io.Serializable;

/**
 * @Author: HLJ
 * @Date: 2022/5/20 20:57
 */
public class Response implements Serializable {
    private static final long serialVersionUID = 232123213L;
    private int code;
    private String message;

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

    private Object result;
}
