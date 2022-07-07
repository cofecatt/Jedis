package socket.interfece.imp;

import basic.Command;
import socket.basic.Node;
import socket.constant.Errors;
import socket.interfece.IOperationStrategy;
import socket.utils.CheckOperation;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @Author: HLJ
 * @Date: 2022/6/29 16:29
 */
public class GetOperationStrategy implements IOperationStrategy {
    @Override
    public Object operation(Command command, Map<String, Node<Object>> map) {
        if(CheckOperation.check(command)) {
            String key = command.getKey();
            Node<Object> o = map.get(key);
            if(o != null) {
                return o.getValue();
            }
        } else {
            return Errors.BAD_PARAM.toString();
        }
        return "nil";
    }

    @Override
    public Object operation(Command command, ConcurrentSkipListMap<String, Node<Object>> skipList) {
        if(CheckOperation.check(command)) {
            String key = command.getKey();
            try {
                Object search = skipList.get(key);
                if(search != null) {
                    return search;
                }
            }catch (NumberFormatException e) {
                e.printStackTrace();
                return Errors.BAD_PARAM.toString();
            }

        } else {
            return Errors.BAD_PARAM.toString();
        }
        return "nil";
    }
}
