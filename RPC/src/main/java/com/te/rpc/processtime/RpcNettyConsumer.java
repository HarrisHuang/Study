package com.te.rpc.processtime;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class RpcNettyConsumer {

    public static void main(String[] args) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // ��������ʼ�� Netty �ͻ��� Bootstrap ����
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel channel) throws Exception {
                    ChannelPipeline pipeline = channel.pipeline();
                    pipeline.addLast(new RpcEncoder(RpcRequest.class)); // ���� RPC ����
                    pipeline.addLast(new RpcDecoder(RpcResponse.class)); // ���� RPC ��Ӧ
                    pipeline.addLast(new RpcClientHandler()); // ���� RPC ��Ӧ
                }
            });
            bootstrap.option(ChannelOption.TCP_NODELAY, true);
            // ���� RPC ������
            ChannelFuture future = bootstrap.connect("127.0.0.1", 8087).sync();
            // д�� RPC �������ݲ��ر�����
            Channel channel = future.channel();

            RpcRequest rpcRequest = new RpcRequest();
            rpcRequest.setRequestId(123456L);

            channel.writeAndFlush(rpcRequest).sync();
            channel.closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

}
