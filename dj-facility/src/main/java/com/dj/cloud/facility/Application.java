package com.dj.cloud.facility;

import com.dj.cloud.facility.netty.server.NettyServer;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.net.InetSocketAddress;

@SpringBootApplication
@EnableDiscoveryClient // 开启注册到注册中心
@EnableJpaAuditing
@ComponentScan(basePackages = {"com.dj.cloud"})
public class Application implements CommandLineRunner {

    @Value("${netty.host}")
    private String nettyHost;

    @Value("${netty.port}")
    private int nettyPort;

    @Autowired
    private NettyServer nettyServer;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(nettyHost, nettyPort);
        ChannelFuture channelFuture = nettyServer.bind(inetSocketAddress);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> nettyServer.destory()));
        channelFuture.channel().closeFuture().syncUninterruptibly();
    }
}
