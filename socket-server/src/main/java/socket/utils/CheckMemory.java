package socket.utils;

import socket.loader.CustomLoader;

/**
 * @Author: HLJ
 * @Date: 2022/7/6 21:00
 */
public class CheckMemory {
    /**
     * 判断剩余空间是否小于5mb
     * @return
     */
    public static boolean plentiful() {
        Runtime run = Runtime.getRuntime();
        long free = run.freeMemory()/(1024*1024);
        return free > 5;
    }
}
