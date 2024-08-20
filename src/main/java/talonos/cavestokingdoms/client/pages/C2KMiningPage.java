package talonos.cavestokingdoms.client.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cpw.mods.fml.common.registry.GameRegistry;
import iguanaman.iguanatweakstconstruct.util.HarvestLevels;
import talonos.cavestokingdoms.client.pages.orediscovery.OreDiscoveryPage;

public class C2KMiningPage extends OreDiscoveryPage {

    // The title of the page
    String title;

    // Itemstacks representing the tools we draw
    ItemStack ore;

    int minheight = 0;
    int maxheight = 64;

    String location;

    // What harvest level unlocks this? (Same as is needed to mine it.)
    int requiredLevel;

    // Can it mine? If so, how well?
    int minesAsLevel = -1;

    // Description of the material
    String description;

    // The resource to display:
    ResourceLocation background;

    // Where is it found?
    String whereFound = "Unknown.";

    // What picture from the list is displayed?
    int locationImg = 0;
    int indexNum = 0;

    // If it is locked, what unlocks it?
    List<String> examples = new ArrayList<>();

    @Override
    public void readPageFromXML(Element element) {
        NodeList nodes = element.getElementsByTagName("title");
        title = nodes.item(0)
            .getTextContent();
        nodes = element.getElementsByTagName("text");
        description = nodes.item(0)
            .getTextContent();

        nodes = element.getElementsByTagName("min");
        minheight = Integer.parseInt(
            nodes.item(0)
                .getTextContent());
        nodes = element.getElementsByTagName("max");
        maxheight = Integer.parseInt(
            nodes.item(0)
                .getTextContent());

        nodes = element.getElementsByTagName("location");
        if (nodes.item(0) != null && nodes.item(0)
            .getTextContent() != null) {
            location = nodes.item(0)
                .getTextContent();
        }

        nodes = element.getElementsByTagName("minesAsLevel");
        if (nodes.item(0) != null) {
            try {
                minesAsLevel = Integer.parseInt(
                    nodes.item(0)
                        .getTextContent());
            } catch (NumberFormatException e) {
                Logger.getAnonymousLogger()
                    .log(Level.WARNING, "Can't parse required level for " + title + "!");
            }
        }

        // Get the ore
        nodes = element.getElementsByTagName("ore");
        if (nodes.item(0) != null) {
            Node oreNode = nodes.item(0);
            String total = oreNode.getTextContent();
            String mod = total.substring(0, total.indexOf(':'));
            String itemName = total.substring(total.indexOf(':') + 1);
            int secondColonPosition = itemName.indexOf(':');
            int meta = 0;
            if (secondColonPosition != -1) {
                meta = Integer.parseInt(itemName.substring(itemName.indexOf(':') + 1));
                itemName = itemName.substring(0, itemName.indexOf(':'));
            }
            Item iconItem = GameRegistry.findItem(mod, itemName);
            if (iconItem != null) {
                ore = new ItemStack(GameRegistry.findItem(mod, itemName), 1, meta);
                requiredLevel = GameRegistry.findBlock(mod, itemName)
                    .getHarvestLevel(meta);
                if (requiredLevel == -1) {
                    requiredLevel = 0;
                }
                populateExamplesList();
            }
        }

        // Get the correct resource location:
        loadStringsAndImageLoc(location);

        int pictureNum = locationImg / 9;
        indexNum = locationImg % 9;

        location = "cavestokingdoms:textures/gui/locations" + pictureNum + ".png";
        background = new ResourceLocation(location);
    }

    @Override
    public void renderContentLayer(int localWidth, int localHeight, boolean isTranslatable) {
        if (isDiscovered(requiredLevel)) {
            drawNormal(localWidth, localHeight);
        } else {
            drawLocked(localWidth, localHeight);
        }
    }

    private void loadStringsAndImageLoc(String location) {
        switch (location) {
            case "all":
            case ("chunkerror"):
                locationImg = 0;
                break;
            case "nether":
                locationImg = 1;
                break;
            case "eerie":
                locationImg = 2;
                break;
            case "cache":
                locationImg = 3;
                break;
            case "alldesert":
                locationImg = 4;
                break;
            case "swamps":
                locationImg = 5;
                break;
            case "desert":
                locationImg = 15;
                break;
            case "plains":
                locationImg = 7;
                break;
            case "mountains":
                locationImg = 8;
                break;
            case "magic":
                locationImg = 9;
                break;
            case "cold":
                locationImg = 10;
                break;
            case "ocean":
                locationImg = 11;
                break;
            case "mushroom":
                locationImg = 12;
                break;
            case "forests":
                locationImg = 13;
                break;
            default:
                locationImg = 14;
                break;
        }

        whereFound = StatCollector.translateToLocal("manual.cavestokingdoms.location." + location.toLowerCase());
    }

    private void drawNormal(int localWidth, int localHeight) {
        String minesAsLevel = StatCollector.translateToLocal("manual.cavestokingdoms.minesaslevel");
        String requiredToMine = StatCollector.translateToLocal("manual.cavestokingdoms.requiredtomine");
        String biome = StatCollector.translateToLocal("manual.cavestokingdoms.biome");
        String height = StatCollector.translateToLocal("manual.cavestokingdoms.height");
        String between = StatCollector.translateToLocal("manual.cavestokingdoms.between");
        String and = StatCollector.translateToLocal("manual.cavestokingdoms.and");

        manual.fonts.drawString("§n" + title, localWidth + 70, localHeight + 4, 0);

        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.enableGUIStandardItemLighting();

        manual.renderitem.renderItemAndEffectIntoGUI(
            manual.fonts,
            manual.getMC().renderEngine,
            ore,
            localWidth + 4,
            localHeight + 4);

        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);

        manual.renderitem.zLevel = 100;

        if (this.minesAsLevel != -1) {
            manual.fonts.drawString(
                minesAsLevel + ": "
                    + this.minesAsLevel
                    + " ("
                    + HarvestLevels.getHarvestLevelName(this.minesAsLevel)
                    + ")",
                localWidth,
                localHeight + 25,
                0);
        }
        manual.fonts.drawSplitString(
            requiredToMine + ": " + requiredLevel + " (" + HarvestLevels.getHarvestLevelName(requiredLevel) + ")",
            localWidth,
            localHeight + 35,
            100,
            0);
        manual.fonts.drawSplitString("§l" + biome + ": §r" + whereFound, localWidth, localHeight + 60, 106, 0);
        manual.fonts.drawSplitString(
            "§l" + height + ": §r" + between + " " + minheight + " " + and + " " + maxheight + ".",
            localWidth,
            localHeight + 105,
            100,
            0);
        manual.fonts.drawSplitString(description, localWidth, localHeight + 130, 178, 0);

        manual.renderitem.zLevel = 0;
    }

    private void drawLocked(int localWidth, int localHeight) {
        String entryOn = StatCollector.translateToLocal("manual.cavestokingdoms.entryon");
        String isLocked = StatCollector.translateToLocal("manual.cavestokingdoms.islocked");
        String toUnlock = StatCollector.translateToLocal("manual.cavestokingdoms.tounlockobtain");
        String suchAs = StatCollector.translateToLocal("manual.cavestokingdoms.suchas");
        String required = StatCollector.translateToLocal("discover.cavestokingdoms.harvestlevel" + requiredLevel);

        manual.fonts.drawString("§n" + entryOn + " " + title + " " + isLocked, localWidth + 28, localHeight + 4, 0);
        manual.fonts.drawString(toUnlock + " ", localWidth + 50, localHeight + 18, 0);
        manual.fonts.drawString(
            required + " " + suchAs,
            localWidth + 70 - (int) (required.length() * 1.8),
            localHeight + 26,
            0);
        int yoffset = 0;
        for (String s : examples) {
            yoffset += 12;
            manual.fonts.drawString(" - " + s, localWidth + 44, localHeight + 36 + yoffset, 0);
        }
    }

    public void renderBackgroundLayer(int localWidth, int localHeight) {
        if (isDiscovered(requiredLevel)) {
            int xIndex = indexNum % 3;
            int yIndex = indexNum / 3;
            if (background != null) {
                manual.getMC()
                    .getTextureManager()
                    .bindTexture(background);
            }
            manual.drawTexturedModalRect(localWidth + 110, localHeight + 15, xIndex * 70, yIndex * 85, 70, 85);
        }
    }

    public void populateExamplesList() {
        switch (this.requiredLevel) {
            case 1:
                examples.add("Flint");
                examples.add("Ghostwood");
                examples.add("Plastic");
                examples.add("Cardboard");
                break;
            case 2:
                examples.add("Prometheum");
                examples.add("Copper");
                examples.add("Deep Iron");
                examples.add("Darkwood");
                examples.add("Certus Quartz");
                break;
            case 3:
                examples.add("Oureclase");
                examples.add("Bronze");
                examples.add("Iron Pick Head");
                examples.add("Fusewood");
                examples.add("Thaumium");
                examples.add("NetherQuartz");
                break;
            case 4:
                examples.add("Steel");
                examples.add("Damascus Steel");
                examples.add("Hepatizon");
                examples.add("Invar");
                examples.add("Manasteel");
                examples.add("Bloodwood");
                break;
            case 5:
                examples.add("Platinum");
                examples.add("Ardite");
                examples.add("Astral Silver");
                examples.add("Void Metal");
                examples.add("Elementium");
                break;
            case 6:
                examples.add("Mithril");
                examples.add("Cobalt");
                examples.add("Carmot");
                break;
            case 7:
                examples.add("Orichalcum");
                examples.add("Manyullyn");
                examples.add("Pokefennium");
                break;
            case 8:
                examples.add("Adamantine");
                examples.add("Sanguinite");
                examples.add("Desichalkos");
                break;
        }
    }

    private boolean isDiscovered(int level) {
        if (level == 0) {
            return true;
        }
        return isDiscovered("discover.cavestokingdoms.harvestlevel" + level);
    }
}
