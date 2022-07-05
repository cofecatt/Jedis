package socket.interfece.imp;

import basic.Command;
import socket.basic.SkipList;
import socket.constant.Errors;
import socket.interfece.IOperationStrategy;
import socket.utils.CheckOperation;

import java.util.Map;

/**
 * @Author: HLJ
 * @Date: 2022/6/29 14:41
 */
public class SetOperationStrategy implements IOperationStrategy {

    @Override
    public Object operation(Command command, Map<String, Object> map) {
        if(CheckOperation.check(command)) {
            String key = command.getKey();
            Object value = command.getValue();
            map.put(key, value);
        } else {
            return Errors.BAD_PARAM.toString();
        }
        return "success";
    }

    @Override
    public Object operation(Command command, SkipList skipList) {
        if(CheckOperation.check(command)) {
            String key = command.getKey();
            Object value = command.getValue();
            try {
                int i = Integer.parseInt(key);
                skipList.set(i, value);
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
