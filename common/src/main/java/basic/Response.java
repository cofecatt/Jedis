package basic;

/**
 * @Author: HLJ
 * @Date: 2022/7/4 17:33
 */
public class Response extends Message {

    private static final long serialVersionUID = 23213212L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 响应体
     */
    private Object res;
    /**
     * 附加信息
     */
    private String msg;
    @Override
    public int getMessageType() {
        return Response;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", res=" + res +
                ", msg='" + msg + '\'' +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getRes() {
        return res;
    }

    public void setRes(Object res) {
        this.res = res;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
