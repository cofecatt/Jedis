package socket.core;

import socket.basic.Command;
import socket.basic.Domain;
import socket.basic.Response;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: HLJ
 * @Date: 2022/5/20 19:30
 */
public class LejClient {
    private final static Logger logger = Logger.getLogger("LejClient");
    private static Socket socket;

    static {
        Domain resource = ResourceLoader.getResource();
        try {
            socket = new Socket(resource.getIp(),
                        Integer.parseInt(resource.getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LejClient() {}

    /**
     * 执行操作
     * @param order 操作指令
     * @param key key
     * @param value value 如果是赋值命令的话
     * @return 返回结果
     */
    public static Response execute(String order, String key, String value) {
        InputStream inputStream;
        OutputStream outputStream;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            oos = new ObjectOutputStream(outputStream);
            ois = new ObjectInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //1.将读取的内容构造成请求发送给服务器
            Command command = new Command();
            command.setKey(key);
            command.setOrder(order);
            command.setValue(value);
            assert oos != null;
            oos.writeObject(command);
            oos.flush();

            //3.从服务器读取响应并解析
            assert ois != null;
            Response response = (Response) ois.readObject();
            //4.把结果显示到界面
            logger.log(Level.INFO, "request:"+command+" response:"+response);
            return response;
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }finally{
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
