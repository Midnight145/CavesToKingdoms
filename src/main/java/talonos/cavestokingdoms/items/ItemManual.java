package talonos.cavestokingdoms.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mantle.Mantle;
import mantle.books.BookData;
import mantle.client.gui.GuiManual;
import talonos.cavestokingdoms.CavesToKingdoms;
import talonos.cavestokingdoms.lib.DEFS;

public class ItemManual extends ItemOreManual {

    String name = "basicManual";

    private static final int NUMBER_OF_MANUALS = 15;

    public ItemManual() {
        super();
        this.setUnlocalizedName(DEFS.MODID + ":" + this.name);
        GameRegistry.registerItem(this, this.name);
        this.setCreativeTab(CreativeTabs.tabMaterials);
        this.setTextureName(DEFS.MODID + ":" + this.name);
        this.setHasSubtypes(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        super.onItemRightClick(stack, world, player);
        player.openGui(Mantle.instance, mantle.client.MProxyClient.manualGuiID, world, 0, 0, 0);
        FMLClientHandler.instance()
            .displayGuiScreen(player, new GuiManual(stack, this.getData(stack)));
        return stack;
    }

    private BookData getData(ItemStack stack) {
        return switch (stack.getItemDamage()) {
            case 0 -> CavesToKingdoms.manualInfo.mats0;
            case 1 -> // noinspection DuplicateBranchesInSwitch
                CavesToKingdoms.manualInfo.mats1;
            case 2 -> CavesToKingdoms.manualInfo.mats2;
            case 3 -> CavesToKingdoms.manualInfo.mats3;
            case 4 -> CavesToKingdoms.manualInfo.mats4;
            case 5 -> CavesToKingdoms.manualInfo.mats5;
            case 6 -> CavesToKingdoms.manualInfo.ben3;
            case 7 -> CavesToKingdoms.manualInfo.ben4;
            case 8 -> CavesToKingdoms.manualInfo.ben1;
            case 9 -> CavesToKingdoms.manualInfo.ben2;
            case 10 -> CavesToKingdoms.manualInfo.taint1;
            case 11 -> CavesToKingdoms.manualInfo.taint2;
            case 12 -> CavesToKingdoms.manualInfo.sarah1;
            case 13 -> CavesToKingdoms.manualInfo.sarah2;
            case 14 -> CavesToKingdoms.manualInfo.dark;
            default -> CavesToKingdoms.manualInfo.mats1;
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        switch (stack.getItemDamage()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                list.add("§o" + StatCollector.translateToLocal("blightfallmanual.geology"));
                break;
            case 6:
            case 7:
            case 8:
            case 9:
                list.add("§o" + StatCollector.translateToLocal("blightfallmanual.ben"));
                break;
            case 10:
            case 11:
                list.add("§o" + StatCollector.translateToLocal("blightfallmanual.taint"));
                break;
            case 12:
            case 13:
                list.add("§o" + StatCollector.translateToLocal("blightfallmanual.sarah"));
                break;
            case 14:
                list.add("§o" + StatCollector.translateToLocal("blightfallmanual.dark"));
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubItems(Item b, CreativeTabs tab, List list) {
        for (int damage = 0; damage < NUMBER_OF_MANUALS; damage++) {
            list.add(new ItemStack(b, 1, damage));
        }
    }

    IIcon[] icons;

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return this.icons[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.icons = new IIcon[NUMBER_OF_MANUALS];
        for (int i = 0; i < NUMBER_OF_MANUALS; i++) {
            this.icons[i] = iconRegister.registerIcon("cavestokingdoms:manual" + i);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getItemDamage();
        return this.getUnlocalizedName() + "." + meta;
    }
}
