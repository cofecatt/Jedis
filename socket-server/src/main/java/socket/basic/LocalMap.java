package socket.basic;

import basic.Domain;
import socket.loader.CustomLoader;
import socket.loader.DomainLoader;
import socket.save.BgSave;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: HLJ
 * @Date: 2022/5/20 20:29
 */
public class LocalMap {
    private static final ConcurrentHashMap<String, Object> concurrentHashMap;
    static {
        concurrentHashMap = (ConcurrentHashMap<String, Object>) BgSave.reload();
    }
    private LocalMap(){}
    public static ConcurrentHashMap<String, Object> getInstance() {
        return concurrentHashMap;
    }

}
