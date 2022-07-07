package socket.core;

import basic.Command;
import basic.Domain;

import basic.Message;
import basic.Response;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import factory.ThreadPoolFactory;
import protocol.MyProtocol;
import protocol.ProtocolFrameDecoder;
import protocol.SequenceIdGenerator;
import socket.loader.DomainLoader;

import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;

import java.util.logging.Logger;

/**
 * @Author: HLJ
 * @Date: 2022/5/20 19:30
 */
public class LejClient {
    private final static Logger logger = Logger.getLogger("LejClient");
    private static Channel channel = null;
    private static final Domain resource = DomainLoader.getResource();
    private static final NioEventLoopGroup group;
    static  {
        group = new NioEventLoopGroup();

        try {
            channel = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ProtocolFrameDecoder());
                            ch.pipeline().addLast(new MyProtocol());
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                    Response msg1 = (Response) msg;
                                    System.out.println(msg1.getCode() == 0 ? msg1.getRes() : msg1.getMsg());
                                    System.out.print(resource.getIp()+":"+resource.getPort()+">");
                                }
                            });
                        }
                    }).connect(resource.getIp(), Integer.parseInt(resource.getPort())).sync().channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        channel.closeFuture().addListener(future -> {
            System.out.println("连接关闭");
            group.shutdownGracefully();
        });
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(resource.getIp()+":"+resource.getPort()+">");
            String line = scanner.nextLine();
            if ("exit".equals(line)) {
                channel.close();
                break;
            }
            String[] s = line.split(" ");
            Command command = new Command();
            if(s.length > 1) {
                command.setOrder(s[0]);
                command.setKey(s[1]);
            }
            if(s.length > 2) {
                command.setValue(s[2]);
            }
            command.setSequenceId(SequenceIdGenerator.nextId());
            command.setMessageType(Message.Command);
            channel.writeAndFlush(command);
        }

    }
}
