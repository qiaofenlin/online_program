//package com.example.online_program.netty_component;
//
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.socket.SocketChannel;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
///**
// * @Author: qfl
// * @Date: 19-1-5 下午5:26
// * @Description:
// */
//@Component
//public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
//    @Resource
//    private DiscardServerHandler discardServerHandler;
//
//    public void initChannel(SocketChannel socketChannel) throws Exception {
//        socketChannel.pipeline().addLast(discardServerHandler);
//    }
//}