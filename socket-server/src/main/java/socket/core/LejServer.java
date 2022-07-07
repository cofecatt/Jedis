package socket.core;


import basic.Domain;
import socket.handler.CommandHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import socket.basic.StorageProducer;
import factory.ThreadPoolFactory;
import protocol.MyProtocol;
import protocol.ProtocolFrameDecoder;
import socket.loader.DomainLoader;

import java.util.HashMap;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author HLJ
 */
public class LejServer {
    private final static Logger logger = Logger.getLogger("LejServer");
    private final Domain resource;

    public LejServer(){
        resource = DomainLoader.getResource();
    }

    public void start(){
        logger.log(Level.INFO,"服务器启动！");
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        MyProtocol myProtocol = new MyProtocol();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        CommandHandler commandHandler = new CommandHandler();
        serverBootstrap.group(boss, worker);
        serverBootstrap.channel(NioServerSocketChannel.class);
        try {
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ProtocolFrameDecoder());
                    ch.pipeline().addLast(myProtocol);
                    ch.pipeline().addLast(commandHandler);
                }
            });
            Channel channel = serverBootstrap.bind(Integer.parseInt(resource.getPort())).sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }


    public static void main(String[] args) {
        LejServer server = new LejServer();
        server.start();
    }

}

