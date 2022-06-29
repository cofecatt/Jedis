package socket.core;


import socket.basic.Command;
import socket.basic.Domain;
import socket.basic.Response;
import socket.constant.Errors;
import socket.constant.OperationConstant;
import socket.factory.ThreadPoolFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author HLJ
 */
public class LejServer {
    private final static Logger logger = Logger.getLogger("LejServer");
    private ServerSocket listenSocket;
    private final ConcurrentHashMap<String, Object> instance = LocalMap.getInstance();
    private final ThreadPoolExecutor executor = ThreadPoolFactory.getDefault();

    public LejServer(){
        Domain resource = ResourceLoader.getResource();
        try {
            listenSocket = new ServerSocket(Integer.parseInt(resource.getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        logger.log(Level.INFO,"服务器启动！");

        while (true) {
            //在这个代码中，通过创建线程，就能保证在调用完accept()之后就能立刻再次返回调用accept().
            Socket clientSocket = null;
            try {
                clientSocket = listenSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //创建一个线程池来给客户端提供服务
            Socket finalClientSocket = clientSocket;
            executor.submit(() -> processConnection(finalClientSocket));
        }
    }

    private void processConnection(Socket clientSocket) {
        logger.log(Level.INFO,"["+clientSocket.getInetAddress().toString()+":"+
                clientSocket.getPort()+"]客户端已上线！");
        InputStream inputStream = null;
        OutputStream outputStream = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            oos = new ObjectOutputStream(outputStream);
            ois = new ObjectInputStream(inputStream);
            try{
                while (true) {
                    assert ois != null;
                    Command command = (Command) ois.readObject();

                    //2.根据请求计算响应
                    Response response = process(command);

                    //3.构造响应并返回
                    oos.writeObject(response);
                    oos.flush();
                    //打印
                    logger.log(Level.INFO,"["+clientSocket.getInetAddress().toString()+":"+
                            clientSocket.getPort()+"],request:"+command+", response:"+response);
                }

            }catch (SocketException | EOFException e){
                //如果连接关闭，才会触发到这个情况！
                logger.log(Level.INFO,"["+clientSocket.getInetAddress().toString()+":"+
                        clientSocket.getPort()+"]客户端已下线。");
            } catch(IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                assert oos != null;
                oos.close();
                assert ois != null;
                ois.close();
                assert inputStream != null;
                inputStream.close();
                assert outputStream != null;
                outputStream.close();
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    public Response process(Command request) {
        if(Objects.isNull(request)) {
            return Response.error(Errors.BAD_PARAM);
        }
        String key = request.getKey();
        String order = request.getOrder();
        Object value = request.getValue();

        if(OperationConstant.SET.equals(order)) {
            if(Objects.isNull(value)) {
                return Response.error(Errors.BAD_PARAM);
            }
            return Response.ok(instance.put(key, value));
        } else if(OperationConstant.GET.equals(order)) {
            return Response.ok(instance.get(key));
        }
        return Response.error(Errors.BAD_PARAM);
    }

    public static void main(String[] args) {
        LejServer tcpThreadPoolEchoServer = new LejServer();
        tcpThreadPoolEchoServer.start();
    }

}

