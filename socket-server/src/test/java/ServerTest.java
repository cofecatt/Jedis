import basic.Command;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import socket.basic.StrategyContext;
import socket.core.LocalMap;
import socket.interfece.imp.SetOperationStrategy;

import java.nio.charset.Charset;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: HLJ
 * @Date: 2022/5/23 8:49
 */
public class ServerTest {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Object> instance = LocalMap.getInstance();

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

                                System.out.println(commend);

                                String[] s = commend.split(" ");
                                Command command = new Command();
                                if(s.length > 1) {
                                    command.setKey(s[1]);
                                    command.setOrder(s[0]);
                                }
                                if(s.length > 2){
                                    command.setValue(s[2]);
                                }

                                System.out.println(command);

                                SetOperationStrategy setOperationStrategy = new SetOperationStrategy();
                                StrategyContext strategyContext = new StrategyContext(setOperationStrategy);
                                Object set = strategyContext.opt(command, instance);

                                for (String s1 : instance.keySet()) {
                                    System.out.println(instance.get(s1));
                                }




                                // 建议使用 ctx.alloc() 创建 ByteBuf
                                ByteBuf response = ctx.alloc().buffer();
                                response.writeBytes(set.toString().getBytes());
                                ctx.writeAndFlush(response);

                                // 思考：需要释放 buffer 吗
                                // 思考：需要释放 response 吗
                            }
                        });
                    }
                }).bind(8000);
    }
}


