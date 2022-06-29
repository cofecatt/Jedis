package socket.interfece.imp;

import socket.basic.Command;
import socket.basic.Response;
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
    public Response operation(Command command, Map<String, Object> map) {
        if(CheckOperation.check(command)) {
            String key = command.getKey();
            Object value = command.getValue();
            map.put(key, value);
        } else {
            return Response.error(Errors.BAD_PARAM);
        }
        return Response.ok("success");
    }

    @Override
    public Response operation(Command command, SkipList skipList) {
        if(CheckOperation.check(command)) {
            String key = command.getKey();
            Object value = command.getValue();
            try {
                int i = Integer.parseInt(key);
                skipList.put(i, value);
            }catch (NumberFormatException e) {
                e.printStackTrace();
                return Response.error(Errors.BAD_PARAM);
            }

        } else {
            return Response.error(Errors.BAD_PARAM);
        }
        return Response.ok("success");
    }
}
