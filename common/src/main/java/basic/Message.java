package basic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HLJ
 * @Date: 2022/7/4 17:24
 */
public abstract class Message implements Serializable {


    private int sequenceId;

    private int messageType;

    /**
     * 获取消息类型对应的整型值
     * @return
     */
    public abstract int getMessageType();

    public static final int Command = 0;
    public static final int Response = 1;
    /**
     * 请求类型 byte 值
     */
    public static final int RPC_MESSAGE_TYPE_REQUEST = 101;

    /**
     * 响应类型 byte 值
     */
    public static final int  RPC_MESSAGE_TYPE_RESPONSE = 102;

    private static final Map<Integer, Class<? extends Message>> messageClasses = new HashMap<>();

    static {
        messageClasses.put(Command, Command.class);
        messageClasses.put(Response, Response.class);
    }


    public int getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(int sequenceId) {
        this.sequenceId = sequenceId;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    /**
     * 根据消息类型字节，获得对应的消息 class
     * @param messageType 消息类型字节
     * @return 消息 class
     */
    public static Class<? extends Message> getMessageClass(int messageType) {
        return messageClasses.get(messageType);
    }



}