package talonos.cavestokingdoms.command;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;

import tconstruct.library.TConstructRegistry;
import tconstruct.library.tools.CustomMaterial;
import tconstruct.library.weaponry.ArrowShaftMaterial;

public class ListShaftMaterials extends CommandBase {

    private final List<String> aliases = new ArrayList<>(2);

    public ListShaftMaterials() {
        aliases.add("blightfallListShaftMaterials");
        aliases.add("blightfallLSM");
    }

    @Override
    public String getCommandName() {
        return "blightfallListShaftMaterials";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "blightfallListShaftMaterials";
    }

    @Override
    public List<String> getCommandAliases() {
        return aliases;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        sender.addChatMessage(
            new ChatComponentText(StatCollector.translateToLocal("command.listshaftmaterials.success")));
        for (CustomMaterial mat : TConstructRegistry.customMaterials) {
            if (mat instanceof ArrowShaftMaterial m) {
                String toPrint = m.input + ", " + m.durabilityModifier + ", " + m.fragility + ", " + m.weight;
                System.out.println(toPrint);
                sender.addChatMessage(new ChatComponentText(toPrint));
            }
        }
    }
}
