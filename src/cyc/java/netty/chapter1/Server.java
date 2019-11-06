/*
 * @(#)Server.java, 2019年10月29日 上午10:12:40
 *
 * Copyright (c) 2018-2019, 四川蜀天梦图数据科技有限公司.
 * All rights reserved.
 */
package cyc.java.netty.chapter1;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年10月29日
 */
public class Server
{
    private final int port;

    public Server(int port)
    {
        this.port = port;
    }

    public static void main(String[] args) throws Exception
    {
        int port = 888;
        new Server(port).start();
    }

    public void start() throws Exception
    {
        final SeverHandler severHandler = new SeverHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try
        {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>()
                    {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception
                        {
                            ch.pipeline().addLast(severHandler);
                        }
                    });

            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        }
        finally
        {
            group.shutdownGracefully().sync();
        }
    }
}
