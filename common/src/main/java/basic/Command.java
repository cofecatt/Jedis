package basic;

import java.io.Serializable;

/**
 * @Author: HLJ
 * @Date: 2022/5/20 20:26
 */
public class Command extends Message {
    private static final long serialVersionUID = 23213213L;
    private String order;
    private String key;
    private Object value;

    @Override
    public String toString() {
        return "Command{" +
                "order='" + order + '\'' +
                ", key='" + key + '\'' +
                ", value=" + value +
                '}';
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }


    @Override
    public int getMessageType() {
        return Command;
    }
}
