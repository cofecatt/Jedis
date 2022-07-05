package constant;

/**
 * @Author: HLJ
 * @Date: 2022/5/20 22:38
 */
public enum  Errors {
    BAD_PARAM(1, "参数错误");

    private final int code;
    private final String message;

    Errors(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
