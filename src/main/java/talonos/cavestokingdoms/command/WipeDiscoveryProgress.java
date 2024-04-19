package talonos.cavestokingdoms.command;

import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;

import talonos.cavestokingdoms.client.pages.orediscovery.OreDiscoveryRegistry;
import talonos.cavestokingdoms.network.CavesToKingdomsNetwork;
import talonos.cavestokingdoms.network.packets.WipeDiscoveryProgressPacket;

public class WipeDiscoveryProgress implements ICommand {

    @Override
    public String getCommandName() {
        return "oreDiscoveryWipe";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/oreDiscoveryWipe";
    }

    @Override
    public List<String> getCommandAliases() {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (sender instanceof EntityPlayer) {
            OreDiscoveryRegistry.getInstance()
                .clearDiscoveries(((EntityPlayer) sender).getEntityData());
            sender.addChatMessage(new ChatComponentTranslation("gui.oreDiscovery.wiped"));
            if (sender instanceof EntityPlayerMP)
                CavesToKingdomsNetwork.sendToPlayer(new WipeDiscoveryProgressPacket(), (EntityPlayerMP) sender);
        } else {
            sender.addChatMessage(new ChatComponentTranslation("gui.oreDiscovery.notPlayer"));
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return false;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
