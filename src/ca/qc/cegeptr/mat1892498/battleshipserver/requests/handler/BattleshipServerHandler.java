package ca.qc.cegeptr.mat1892498.battleshipserver.requests.handler;

import ca.qc.cegeptr.mat1892498.battleshipserver.game.ActionHandler;
import ca.qc.cegeptr.mat1892498.battleshipserver.requests.Response;
import ca.qc.cegeptr.mat1892498.battleshipserver.game.users.User;
import ca.qc.cegeptr.mat1892498.battleshipserver.game.users.UserManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class BattleshipServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserManager.USERS.put(ctx.channel().id().asLongText(), new User(ctx.channel().id().asLongText(), ctx));
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        UserManager.USERS.remove(ctx.channel().id().asLongText());
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        Response requestData = (Response) msg;
        new ActionHandler(ctx, requestData);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("Cause: " + cause.getMessage());
    }

}