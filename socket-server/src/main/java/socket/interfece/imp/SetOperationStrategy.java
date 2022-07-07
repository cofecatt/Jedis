package socket.interfece.imp;

import basic.Command;
import socket.basic.Node;
import socket.constant.Errors;
import socket.interfece.IOperationStrategy;
import socket.utils.CheckOperation;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @Author: HLJ
 * @Date: 2022/6/29 14:41
 */
public class SetOperationStrategy implements IOperationStrategy {

    @Override
    public Object operation(Command command, Map<String, Node<Object>> map) {
        if(CheckOperation.check(command)) {
            String key = command.getKey();
            Object value = command.getValue();
            Node<Object> objectNode = new Node<>(value, -100, System.currentTimeMillis());
            map.put(key, objectNode);
        } else {
            return Errors.BAD_PARAM.toString();
        }
        return "success";
    }

    @Override
    public Object operation(Command command, ConcurrentSkipListMap skipList) {
        if(CheckOperation.check(command)) {
            String key = command.getKey();
            Object value = command.getValue();
            try {
                skipList.put(key, value);
            }catch (NumberFormatException e) {
                e.printStackTrace();
                return Errors.BAD_PARAM.toString();
            }

        } else {
            return Errors.BAD_PARAM.toString();
        }
        return "success";
    }
}
