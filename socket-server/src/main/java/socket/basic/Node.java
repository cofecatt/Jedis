package socket.basic;

import java.io.Serializable;

/**
 * @Author: HLJ
 * @Date: 2022/7/7 17:00
 */
public class Node<V> implements Serializable {
    private static final long serialVersionUID = 23215212L;
    private V value;
    private long survivalTime;
    private long birthTime;

    public Node() {}

    /**
     *
     * @param value 值
     * @param survivalTime 存活时间
     * @param birthTime 生成时间
     */
    public Node(V value, long survivalTime, long birthTime) {
        this.value = value;
        this.survivalTime = survivalTime;
        this.birthTime = birthTime;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public long getSurvivalTime() {
        return survivalTime;
    }

    public void setSurvivalTime(long survivalTime) {
        this.survivalTime = survivalTime;
    }

    public long getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(long birthTime) {
        this.birthTime = birthTime;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", survivalTime=" + survivalTime +
                ", birthTime=" + birthTime +
                '}';
    }
}
