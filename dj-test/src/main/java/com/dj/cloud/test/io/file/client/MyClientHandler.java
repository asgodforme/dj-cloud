package com.dj.cloud.test.io.file.client;

import com.dj.cloud.test.io.file.domain.Constants;
import com.dj.cloud.test.io.file.domain.FileBurstData;
import com.dj.cloud.test.io.file.domain.FileBurstInstruct;
import com.dj.cloud.test.io.file.domain.FileTransferProtocol;
import com.dj.cloud.test.io.file.util.FileUtil;
import com.dj.cloud.test.io.file.util.MsgUtil;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyClientHandler extends SimpleChannelInboundHandler {

    /**
     * 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息：本客户端链接到服务端。channelId：" + channel.id());
        System.out.println("链接报告IP:" + channel.localAddress().getHostString());
        System.out.println("链接报告Port:" + channel.localAddress().getPort());
        System.out.println("链接报告完毕");
        //通知客户端链接建立成功
        String str = "通知服务端链接建立成功" + " " + new Date() + " " + channel.localAddress().getHostString();
//        ctx.writeAndFlush(str);
    }

    /**
     * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("断开链接" + ctx.channel().localAddress().toString());
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
        if (!(msg instanceof FileTransferProtocol)) return;

        FileTransferProtocol fileTransferProtocol = (FileTransferProtocol) msg;
        System.out.println("客户端收到： " + new Gson().toJson(fileTransferProtocol));
        switch (fileTransferProtocol.getTransferType()) {
            case 1:
                FileBurstInstruct fileBurstInstruct = (FileBurstInstruct) fileTransferProtocol.getTransferObj();
                if (fileBurstInstruct.getStatus() == Constants.FileStatus.COMPLETE) {
//                    ctx.flush();
//                    ctx.close();
//                    System.exit(-1);
                    return;
                }

                FileBurstData fileBurstData = FileUtil.readFile(fileBurstInstruct.getClientFileUrl(), fileBurstInstruct.getReadPosition());
                ctx.writeAndFlush(MsgUtil.buildTransferData(fileBurstData));
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " bugstack虫洞栈客户端传输文件信息。 FILE：" + fileBurstData.getFileName() + " SIZE(byte)：" + (fileBurstData.getEndPos() - fileBurstData.getBeginPos()));
                break;
            default:
                break;
        }

        /**模拟传输过程中断，场景测试可以注释掉
         *
         */
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " bugstack虫洞栈客户端传输文件信息[主动断开链接，模拟断点续传]");
//        ctx.flush();
//        ctx.close();
//        System.exit(-1);

    }

    /**
     * 抓住异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
        System.out.println("异常信息：\r\n" + cause.getMessage());
    }
}
