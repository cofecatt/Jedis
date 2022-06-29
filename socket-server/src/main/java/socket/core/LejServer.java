package socket.core;


import basic.Command;
import basic.Domain;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import socket.basic.StrategyContext;
import socket.constant.OperationConstant;
import socket.factory.ThreadPoolFactory;
import socket.interfece.IOperationStrategy;
import socket.interfece.imp.GetOperationStrategy;
import socket.interfece.imp.SetOperationStrategy;

import java.nio.charset.Charset;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author HLJ
 */
public class LejServer {
    private final static Logger logger = Logger.getLogger("LejServer");
    private final ConcurrentHashMap<String, Object> instance = LocalMap.getInstance();
    private final ThreadPoolExecutor executor = ThreadPoolFactory.getDefault();
    private Domain resource = null;
    public LejServer(){
        resource = ResourceLoader.getResource();
    }

    public void start(){
        logger.log(Level.INFO,"服务器启动！");

        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                ByteBuf buffer = (ByteBuf) msg;
                                String commend = buffer.toString(Charset.defaultCharset());

                                // comend结构示例 set hello world
                                // get hello
                                // 返回 world
                                String[] s = commend.split(" ");
                                Command command = new Command();
                                if(s.length > 0) {
                                    Object res = null;
                                    IOperationStrategy operationStrategy = null;
                                    if(s[0].equalsIgnoreCase(OperationConstant.GET)) {
                                        operationStrategy = new GetOperationStrategy();

                                    } else if(s[0].equalsIgnoreCase(OperationConstant.SET)) {
                                         operationStrategy = new SetOperationStrategy();
                                         command.setValue(s[2]);
                                    }else {
                                        return;
                                    }
                                    command.setOrder(s[0]);
                                    command.setKey(s[1]);
                                    StrategyContext strategyContext = new StrategyContext(operationStrategy);
                                    res = strategyContext.opt(command, instance);
                                    ByteBuf response = ctx.alloc().buffer();
                                    response.writeBytes(res.toString().getBytes());
                                    ctx.writeAndFlush(response);
                                }
                            }
                        });
                    }
                }).bind(Integer.parseInt(resource.getPort()));
    }


    public static void main(String[] args) {
        LejServer tcpThreadPoolEchoServer = new LejServer();
        tcpThreadPoolEchoServer.start();
    }

}

