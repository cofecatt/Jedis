import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * @Author: HLJ
 * @Date: 2022/6/27 20:57
 */
public class ClientTest {
    public static void main(String[] args) {
        try {
            new Bootstrap().group(new NioEventLoopGroup())
                    .channel(NioSocketChannel.class).handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                    nioSocketChannel.pipeline().addLast(new StringEncoder());
                }
            }).connect(new InetSocketAddress("localhost", 7000))
                    .sync()
                    .channel()
                    .writeAndFlush("hello")
                    .channel().read();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
