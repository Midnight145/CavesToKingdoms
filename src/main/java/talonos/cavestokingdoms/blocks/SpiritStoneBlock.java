package talonos.cavestokingdoms.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import talonos.cavestokingdoms.lib.DEFS;

public class SpiritStoneBlock extends CtKBlock {

    public SpiritStoneBlock() {
        this.setBlockName(DEFS.MODID + "_" + DEFS.SpiritStoneBlockName);
        GameRegistry.registerBlock(this, this.getUnlocalizedName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(DEFS.MODID + ":" + DEFS.SpiritStoneBlockName);
    }
}
