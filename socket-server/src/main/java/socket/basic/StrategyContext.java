package socket.basic;

import socket.interfece.IOperationStrategy;

import java.util.Map;

/**
 * @Author: HLJ
 * @Date: 2022/6/29 14:47
 */
public class StrategyContext {

    private final IOperationStrategy operationStrategy;


    public StrategyContext(IOperationStrategy operationStrategy) {
        this.operationStrategy = operationStrategy;
    }


    public Object opt(Command command, SkipList skipList){
        // 通过接口变量调用对应的具体策略
        return operationStrategy.operation(command, skipList);
    }

    public Object opt(Command command, Map map){
        // 通过接口变量调用对应的具体策略
        return operationStrategy.operation(command, map);
    }
}
