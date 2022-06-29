package socket.interfece.imp;

import basic.Command;
import socket.basic.SkipList;
import socket.basic.SkipListNode;
import socket.constant.Errors;
import socket.interfece.IOperationStrategy;
import socket.utils.CheckOperation;

import java.util.Map;

/**
 * @Author: HLJ
 * @Date: 2022/6/29 16:29
 */
public class GetOperationStrategy implements IOperationStrategy {
    @Override
    public Object operation(Command command, Map<String, Object> map) {
        if(CheckOperation.check(command)) {
            String key = command.getKey();
            Object o = map.get(key);
            if(o != null) {
                return o;
            }
        } else {
            return Errors.BAD_PARAM.toString();
        }
        return "nil";
    }

    @Override
    public Object operation(Command command, SkipList skipList) {
        if(CheckOperation.check(command)) {
            String key = command.getKey();
            try {
                int i = Integer.parseInt(key);
                SkipListNode search = skipList.search(i);
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
