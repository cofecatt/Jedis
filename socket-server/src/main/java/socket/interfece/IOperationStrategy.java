package socket.interfece;

import basic.Command;
import socket.basic.Node;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @Author: HLJ
 * @Date: 2022/6/29 14:35
 */
public interface IOperationStrategy {
    /**
     * 采用策略模式进行操作
     * @param command 定义操作
     * @param map map 哈希表
     * @return
     */
    Object operation(Command command, Map<String, Node<Object>> map);


    /**
     * 重载实现对跳表和哈希表的操作
     * @param command 操作命令
     * @param skipList 跳表
     * @return
     */
    Object operation(Command command, ConcurrentSkipListMap<String, Node<Object>> skipList);
}
