package talonos.cavestokingdoms;

import net.minecraft.init.Blocks;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import exterminatorJeff.undergroundBiomes.api.UBAPIHook;

public class UBCIntegration {

    public static void init(FMLPreInitializationEvent e) {

        try {
            CavesToKingdoms.logger.info("UBC Integration: Attempting to UBIfy Things");
            UBAPIHook.ubAPIHook.ubOreTexturizer.requestUBOreSetup(
                Blocks.quartz_ore,
                0,
                "cavestokingdoms:misc/quartz_ore",
                "ubc.cavestokingdoms.quartz");

        } catch (Exception ex) {
            CavesToKingdoms.logger.error("Problem with UBIfying Things: {}", ex);
        }
    }
}
