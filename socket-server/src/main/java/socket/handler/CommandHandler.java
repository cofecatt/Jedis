package socket.handler;

import basic.Command;
import basic.Response;
import constant.OperationConstant;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import socket.basic.SkipList;
import socket.basic.StrategyContext;
import socket.interfece.IOperationStrategy;
import socket.interfece.imp.GetOperationStrategy;
import socket.interfece.imp.SetOperationStrategy;

/**
 * @Author: HLJ
 * @Date: 2022/7/4 16:31
 */
@ChannelHandler.Sharable
public class CommandHandler extends SimpleChannelInboundHandler<Command> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Command msg) throws Exception {
        Object res;
        IOperationStrategy operationStrategy = null;
        if(msg != null) {
            if(OperationConstant.GET.equalsIgnoreCase(msg.getOrder())) {
                operationStrategy = new GetOperationStrategy();

            } else if(OperationConstant.SET.equalsIgnoreCase(msg.getOrder())) {
                operationStrategy = new SetOperationStrategy();
            }
        }else {
            return;
        }
        StrategyContext strategyContext = new StrategyContext(operationStrategy);
        res = strategyContext.opt(msg, SkipList.def);
        Response response = new Response();
        response.setCode(0);
        response.setRes(res);
        ctx.writeAndFlush(response);
    }
}