
import socket.basic.SkipList;

/**
 * @Author: HLJ
 * @Date: 2022/5/23 8:49
 */
public class Test {
    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.put(1,2);
        skipList.put(2,3);
        skipList.put(2,4);
        skipList.put(2,5);
        System.out.println(skipList);
    }
}
