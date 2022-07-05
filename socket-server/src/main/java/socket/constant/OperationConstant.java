package socket.constant;

import socket.interfece.IOperationStrategy;
import socket.interfece.imp.GetOperationStrategy;
import socket.interfece.imp.SetOperationStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HLJ
 * @Date: 2022/5/20 22:34
 */
public class OperationConstant {
    public static final String SET = "set";
    public static final String GET = "get";
    public static final String HGET = "hget";
    public static final String HSET = "hset";
    public static final Map<String, IOperationStrategy> strategyMap = new HashMap<>();
    static {
        strategyMap.put(SET, new SetOperationStrategy());
        strategyMap.put(GET, new GetOperationStrategy());
        strategyMap.put(HSET, strategyMap.get(SET));
        strategyMap.put(HGET, strategyMap.get(GET));
    }
    public static IOperationStrategy getOperationStrategy(String operation) {
        return strategyMap.get(operation.toLowerCase());
    }
}
