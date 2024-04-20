package talonos.cavestokingdoms.proxy;

import net.minecraft.entity.player.EntityPlayer;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy {

    public void registerRenderers() {}

    public EntityPlayer getPlayerFromContext(MessageContext context) {
        return context.getServerHandler().playerEntity;
    }
}
