package talonos.cavestokingdoms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import talonos.cavestokingdoms.blocks.CtKBlock;
import talonos.cavestokingdoms.client.pages.orediscovery.OreDiscoveryRegistry;
import talonos.cavestokingdoms.command.DiscoverAll;
import talonos.cavestokingdoms.command.ListShaftMaterials;
import talonos.cavestokingdoms.command.ScanBlocks;
import talonos.cavestokingdoms.command.WipeDiscoveryProgress;
import talonos.cavestokingdoms.lib.DEFS;
import talonos.cavestokingdoms.network.CavesToKingdomsNetwork;
import talonos.cavestokingdoms.proxy.CommonProxy;

@Mod(modid = DEFS.MODID, name = DEFS.MODNAME, version = DEFS.VERSION, dependencies = DEFS.DEPS)
public class CavesToKingdoms {

    @SidedProxy(clientSide = DEFS.CLIENTPROXYLOCATION, serverSide = DEFS.COMMONPROXYLOCATION)
    public static CommonProxy proxy;

    public static CavesToKingdoms instance;

    public static ManualInfo manualInfo;

    public static Logger logger = LogManager.getLogger(DEFS.MODID);

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        CtKBlock.init();
        CtKItems.init();
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        CavesToKingdomsNetwork.init();

        manualInfo = new ManualInfo();
    }

    @Mod.EventHandler
    public static void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new ListShaftMaterials());
        event.registerServerCommand(new DiscoverAll());
        event.registerServerCommand(new WipeDiscoveryProgress());
        event.registerServerCommand(new ScanBlocks());
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        proxy.registerRenderers();
        OreDiscoveryRegistry.getInstance(); // Constructs and therefore loads all discovery registry stuff.
    }
}
