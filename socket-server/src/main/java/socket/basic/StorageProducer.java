package socket.basic;


import socket.save.BgSave;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;


/**
 * @Author: HLJ
 * @Date: 2022/5/20 20:29
 */
public class StorageProducer {
    private static final ConcurrentHashMap<String, Node<Object>> concurrentHashMap;

    private static final ConcurrentSkipListMap<String, Node<Object>> skipList;
    static {
        concurrentHashMap = new ConcurrentHashMap<>(200);
        skipList = new ConcurrentSkipListMap<>();
        BgSave.reload(concurrentHashMap);

    }
    private StorageProducer(){}
    public static ConcurrentHashMap<String, Node<Object>> getConcurrentHashMap() {
        return concurrentHashMap;
    }

    public static ConcurrentSkipListMap<String, Node<Object>> getSkipList() {
        return skipList;
    }



}
