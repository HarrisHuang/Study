package com.te.rpc.processtime;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class RpcServerHandler extends SimpleChannelInboundHandler<RpcRequest> {

    @Override
    public void channelRead0(final ChannelHandlerContext ctx, RpcRequest request) throws Exception {
        RpcResponse rpcResponse = invoke(request);
        // д�� RPC ��Ӧ�����Զ��ر�����
        ctx.writeAndFlush(rpcResponse).addListener(ChannelFutureListener.CLOSE);
    }

    private RpcResponse invoke(RpcRequest rpcRequest) {
        //ģ�ⷴ�����
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setRequestId(rpcRequest.getRequestId());
        //... some operation
        return rpcResponse;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}