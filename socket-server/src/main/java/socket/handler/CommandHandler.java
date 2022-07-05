package socket.handler;

import basic.Command;
import basic.Response;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import socket.basic.LocalMap;
import socket.basic.SkipList;
import socket.basic.StrategyContext;
import socket.constant.OperationConstant;
import socket.interfece.IOperationStrategy;


/**
 * @Author: HLJ
 * @Date: 2022/7/4 16:31
 */
@ChannelHandler.Sharable
public class CommandHandler extends SimpleChannelInboundHandler<Command> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Command msg) throws Exception {
        Object res = null;
        String order;
        IOperationStrategy operationStrategy = null;
        if(msg != null) {
            order = msg.getOrder();
            if(order != null) {
                operationStrategy = OperationConstant.getOperationStrategy(order);
                StrategyContext strategyContext = new StrategyContext(operationStrategy);
                String s = order.toLowerCase();
                if(s.contains("h")) {
                    res = strategyContext.opt(msg, LocalMap.getInstance());
                } else {
                    res = strategyContext.opt(msg, SkipList.def);
                }
            }
        }

        //将结果封装成response对象
        Response response = new Response();
        response.setCode(0);
        response.setRes(res);
        ctx.writeAndFlush(response);
    }
}