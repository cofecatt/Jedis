package socket.basic;

import basic.Domain;
import socket.core.ResourceLoader;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: HLJ
 * @Date: 2022/5/20 20:29
 */
public class LocalMap {
    private static final ConcurrentHashMap<String, Object> concurrentHashMap;
    private static final Domain resource;
    static {
        resource = ResourceLoader.getResource();
        concurrentHashMap = new ConcurrentHashMap<>(resource.getMinMapSize());
    }
    private LocalMap(){}
    public static ConcurrentHashMap<String, Object> getInstance() {
        return concurrentHashMap;
    }

}
