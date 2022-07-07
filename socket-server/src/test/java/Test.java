import socket.utils.CheckMemory;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @Author: HLJ
 * @Date: 2022/7/6 19:29
 */
public class Test {
    public static void main(String[] args) {
        ConcurrentSkipListMap<String, String> map = new ConcurrentSkipListMap<>();
        map.put("test", "get");
        map.put("u", "u");
        System.out.println(map.ceilingKey("test"));
    }
}
