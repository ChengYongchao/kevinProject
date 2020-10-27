
package com.kevin.netty.chapter1;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年10月29日
 */
public class Client
{
    private final String host;

    private final int port;

    public Client(String host, int port)
    {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws Exception
    {
        String host = "127.0.0.1";
        int port = 888;
        new Client(host, port).start();
    }

    public void start() throws Exception
    {
        EventLoopGroup group = new NioEventLoopGroup();
        try
        {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>()
                    {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception
                        {
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });

            ChannelFuture f = b.connect().sync();
            f.channel().closeFuture().sync();
        }
        finally
        {
            group.shutdownGracefully().sync();
        }
    }

}
