package socket.basic;

/**
 * @Author: HLJ
 * @Date: 2022/2/22 22:42
 */
/**
 * 跳表节点模型对象
 * 1.拥有上下左右四个指针
 */
public class SkipListNode<T> {
    /**
     * data存储结构，key定义成integer是为了使用
     */
    private Integer key;
    private T value;
    /**
     * 上下左右四个指针,存的是结点的内存地址
     */
    public SkipListNode up,down,left,right;
    /**
     * 每个层级头尾节点的key
     */
    public static final Integer negativeInfinity= Integer.MIN_VALUE; // 负无穷
    public static final Integer positiveInfinity= Integer.MAX_VALUE; // 正无穷

    /**
     * 构造函数
     * @param key
     * @param value
     */
    public SkipListNode(Integer key, T value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

