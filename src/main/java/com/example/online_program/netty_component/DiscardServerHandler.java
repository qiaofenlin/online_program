package com.example.online_program.netty_component;

/**
 * @Author: qfl
 * @Date: 19-1-5 下午5:26
 * @Description:
 */
//@Component
//@ChannelHandler.Sharable
//public class DiscardServerHandler extends ChannelHandlerAdapter {
//    @Resource
//    private BaseService baseService;
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//
//        try {
//            ByteBuf in = (ByteBuf) msg;
//            System.out.println("传输内容是");
//            System.out.println(in.toString(CharsetUtil.UTF_8));
//            //这里调用service服务
//            baseService.test();
//        }  finally {
//            ReferenceCountUtil.release(msg);
//        }
//    }
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        // 出现异常就关闭
//        cause.printStackTrace();
//        ctx.close();
//    }
//}
