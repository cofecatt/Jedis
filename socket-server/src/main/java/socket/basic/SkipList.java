package socket.basic;

import java.util.Objects;
import java.util.Random;

/**
 * @Author: HLJ
 * @Date: 2022/2/22 22:51
 * 跳表
 * 实现：查询、新增、删除、修改的功能
 */
public class SkipList<T> {
    /**
     * 跳表的头尾是head和tail结点
     */
    private  final SkipListNode<T> head = new SkipListNode<>(-1, null);
    private  final SkipListNode<T> tail = new SkipListNode<>(1, null);
    /**
     * 跳表的自身参数
     */
    // 跳表的结点数（正无穷，负无穷，head、tail结点不算）
    private Integer nodes;
    // 跳表的高度
    private Integer height;

    /**
     * 随机性升层
     */
    private Random random =new Random();
    private static final double PROBABILITY=0.5;//向上提升一个的概率

    /**
     * 无参构造
     */
    public SkipList() {
        // 创建一个新的空白层
        createNewList();
        // 初始化参数
        height=0;
        nodes=0;
    }

    /**
     * 获取当前跳表的节点数
     * @return
     */
    public Integer size(){
        return nodes;
    }

    /**
     * 新增或者修改
     * @param key
     */
    public void put(Integer key,T value){
        // 根据key查询跳表中是否存在对应的节点
        SkipListNode<T> p = findNode(key);
        // 存在节点就修改值
        if (Objects.equals(key, p.getKey())){
            p.setValue(value);
        }
        // 不存在就新增
        SkipListNode<T> q = new SkipListNode<>(key, value);// 创建新增节点
        // 底层链表新增节点
        backLink(p,q);
        // 对新增节点进行随机性升层操作
        probabilityUpgrade(q,key);
    }

    /**
     * 根据key查对应对节点
     * @param key
     * @return
     */
    public SkipListNode<T> search(Integer key){
        SkipListNode<T> node = findNode(key);
        if (Objects.equals(node.getKey(), key)){
            return node;
        }
        return null;
    }

    /**
     * 根据key删除
     * @param key
     */
    public void remove(Integer key){
        // 删除各层的节点
        SkipListNode<T> pointer = findNode(key);
        if (!Objects.equals(pointer.getKey(), key)){
            return;
        }
        while (true){
            // 前后的节点指针重新指向地址
            horizontalLink(pointer.left,pointer.right);
            if (pointer.up==null){
                break;
            }
            pointer = pointer.up;
        }
    }


    /**
     * 对新增节点进行随机性升层操作
     * @param q
     * @param key
     */
    private void probabilityUpgrade(SkipListNode<T> q, Integer key) {
        int currentLevel=0;// 当前所在层数为0，底层是用于存value的
        // 指针指向新增节点
        SkipListNode<T> pointer =q;
        // 进行概率性的升层操作 随机数决定新增节点的高度
        while (random.nextDouble() > PROBABILITY){
            // 如果超出了高度，需要重新建一个顶层
            if (currentLevel>=height){
                height++;
                // 新建一个顶层
                createNewList();
            }
            //将p移动到上一层
            SkipListNode<T> oldPointer =pointer;// 存储
            while (pointer.up==null) {
                pointer=pointer.left;
            }
            pointer=pointer.up; // 指针指向新增节点的上一层
            // 素引层是不需要value的只需保存key，用于查询即可
            SkipListNode<T> index = new SkipListNode<>(key, null);
            // 在这一层新增节点
            backLink(pointer,index);
            // 垂直双向连接索引节点和底层存值节点
            verticalLink(index, oldPointer);
            // 当前层数+1
            currentLevel++;
            // 指针上移到上一层
            pointer=index;
        }
    }

    /**
     * 创建一个新的空白层
     */
    private void createNewList() {
        // 跳表的每一层的头尾都是正负无穷的结点
        SkipListNode<T> p1 = new SkipListNode<>(SkipListNode.negativeInfinity, null);
        SkipListNode<T> p2 = new SkipListNode<>(SkipListNode.positiveInfinity, null);
        // 水平横向连接
        horizontalLink(p1,p2);
        // 由于初始化的时候跳表只有最底层，所以负无穷p1与head连接
        if (head.down !=null && tail.down !=null){
            verticalLink(p1,head.down);
            verticalLink(p2,tail.down);
        }
        verticalLink(this.head,p1);
        // 垂直双向连接
        verticalLink(this.tail,p2);
    }

    /**
     * 垂直双向连接
     * @param p1
     * @param p2
     */
    private void verticalLink(SkipListNode<T> p1, SkipListNode<T> p2) {
        p1.down=p2;
        p2.up=p1;
    }


    /**
     * 新增节点，p插入到q后面
     * @param p
     * @param q
     */
    private void backLink(SkipListNode<T> p, SkipListNode<T> q) {
        // 先保存p的右指针地址
        SkipListNode right = p.right;
        // 横向连接p和新增节点q,q和right
        horizontalLink(p,q);
        horizontalLink(q,right);
        // 节点数加1
        nodes++;
    }

    /**
     * 根据key找小于等于key的节点
     * @param key
     * @return
     */
    private SkipListNode<T> findNode(Integer key) {
        // 指针指向head,跳表的查询，从head或者tail开始
        SkipListNode<T> pointer =head.down;
        while (true){
            // 如果一层只有正负无穷，就没必要水平查找了
            while (pointer.right.getKey()!=SkipListNode.positiveInfinity
                    && pointer.right.getKey()<=key){
                // 每层找最接近key的节点
                pointer=pointer.right;
            }
            // 水平找到最接近key的节点后，再垂直向下一层,然后继续直到最底层
            if (pointer.down==null){
                break;
            }
            pointer=pointer.down;
        }
        return pointer;
    }

    /**
     * 水平横向连接 （p1在左，p2在右）
     * @param p1
     * @param p2
     */
    private void horizontalLink(SkipListNode<T> p1, SkipListNode<T> p2) {
        p1.right=p2;
        p2.left=p1;
    }

    public static void main(String[] args) {
        SkipList<String> list = new SkipList<>();
        list.put(2, "yan");
        SkipListNode<String> search1 = list.search(2);
        list.remove(2);
        SkipListNode<String> search2 = list.search(2);
        list.put(1, "wo");
        list.put(3, "ai");
        list.put(1, "cao");
        list.put(4, "xiao");
        list.put(6, "zhi");
        list.put(5, "障");
        System.out.println(search1.getValue());
        System.out.println(search2);
    }
}
