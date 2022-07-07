package socket.expire.Impl;

import socket.expire.EliminationStrategy;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @Author: HLJ
 * @Date: 2022/7/6 20:55
 */
public class LruEliminationStrategy implements EliminationStrategy {
    @Override
    public void eliminate(ConcurrentSkipListMap skipList) {

    }

    @Override
    public void eliminate(Map<String, Object> map) {

    }
}
