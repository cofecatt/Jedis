package socket.utils;

import basic.Command;
import socket.constant.OperationConstant;

/**
 * @Author: HLJ
 * @Date: 2022/6/29 14:52
 */
public class CheckOperation {
    public static boolean check(Command command) {
        if(command != null && command.getOrder() != null) {
            if(OperationConstant.SET.equalsIgnoreCase(command.getOrder())) {
                return command.getValue() != null && command.getKey() != null;
            }else if(OperationConstant.GET.equalsIgnoreCase(command.getOrder())) {
                return command.getKey() != null;
            }
        }
        return false;
    }
}
