package talonos.cavestokingdoms.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;

import talonos.cavestokingdoms.client.pages.orediscovery.OreDiscoveryRegistry;
import talonos.cavestokingdoms.network.CavesToKingdomsNetwork;
import talonos.cavestokingdoms.network.packets.WipeDiscoveryProgressPacket;

public class WipeDiscoveryProgress extends CommandBase {

    @Override
    public String getCommandName() {
        return "oreDiscoveryWipe";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "/oreDiscoveryWipe";
    }

    @Override
    public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
        if (p_71515_1_ instanceof EntityPlayer) {
            OreDiscoveryRegistry.getInstance()
                .clearDiscoveries(((EntityPlayer) p_71515_1_).getEntityData());
            p_71515_1_.addChatMessage(new ChatComponentTranslation("gui.oreDiscovery.wiped"));
            if (p_71515_1_ instanceof EntityPlayerMP)
                CavesToKingdomsNetwork.sendToPlayer(new WipeDiscoveryProgressPacket(), (EntityPlayerMP) p_71515_1_);
        } else {
            p_71515_1_.addChatMessage(new ChatComponentTranslation("gui.oreDiscovery.notPlayer"));
        }
    }
}
