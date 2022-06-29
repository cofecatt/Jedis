package socket.core;

import basic.Domain;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: HLJ
 * @Date: 2022/5/20 20:29
 */
public class LocalMap {
    private static ConcurrentHashMap<String, Object> concurrentHashMap;
    private static Domain resource = null;
    static {
        resource = ResourceLoader.getResource();
        concurrentHashMap = new ConcurrentHashMap<String, Object>(resource.getMinMapSize());
    }
    private LocalMap(){}
    public static ConcurrentHashMap<String, Object> getInstance() {
        return concurrentHashMap;
    }

}
