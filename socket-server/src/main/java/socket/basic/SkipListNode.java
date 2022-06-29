package socket.basic;


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
    public SkipListNode up, down, left, right;
    /**
     * 每个层级头尾节点的key
     */
    public static final Integer NEGATIVEINTEGER = Integer.MIN_VALUE;
    public static final Integer POSITIVEINTEGER = Integer.MAX_VALUE;

    /**
     * 构造函数
     *
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

