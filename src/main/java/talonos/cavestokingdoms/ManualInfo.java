package talonos.cavestokingdoms;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.minecraft.util.ResourceLocation;

import org.w3c.dom.Document;

import mantle.books.BookData;
import mantle.books.BookDataStore;
import talonos.cavestokingdoms.lib.DEFS;

public class ManualInfo {

    public BookData mats0 = new BookData();
    public BookData mats1 = new BookData();
    public BookData mats2 = new BookData();
    public BookData mats3 = new BookData();
    public BookData mats4 = new BookData();
    public BookData mats5 = new BookData();
    public BookData ben1 = new BookData();
    public BookData ben2 = new BookData();
    public BookData ben3 = new BookData();
    public BookData ben4 = new BookData();
    public BookData taint1 = new BookData();
    public BookData taint2 = new BookData();
    public BookData sarah1 = new BookData();
    public BookData sarah2 = new BookData();
    public BookData dark = new BookData();

    private Document mats0Doc;
    private Document mats1Doc;
    private Document mats2Doc;
    private Document mats3Doc;
    private Document mats4Doc;
    private Document mats5doc;
    private Document ben1doc;
    private Document ben2doc;
    private Document ben3doc;
    private Document ben4doc;
    private Document sarah1Doc;
    private Document sarah2Doc;
    private Document darkDoc;

    public ManualInfo() {
        this.readManuals();
        Document d = this.mats0Doc;
        this.initManual(
            this.mats0,
            "basicManual.0",
                d,
            "tinker:tinkerbook_diary");
        d = this.mats1Doc;
        this.initManual(
            this.mats1,
            "basicManual.1",
                d,
            "tinker:tinkerbook_diary");
        d = this.mats2Doc;
        this.initManual(
            this.mats2,
            "basicManual.2",
                d,
            "tinker:tinkerbook_diary");
        d = this.mats3Doc;
        this.initManual(
            this.mats3,
            "basicManual.3",
                d,
            "tinker:tinkerbook_diary");
        d = this.mats4Doc;
        this.initManual(
            this.mats4,
            "basicManual.4",
                d,
            "tinker:tinkerbook_diary");
        d = this.mats5doc;
        this.initManual(
            this.mats5,
            "basicManual.5",
                d,
            "tinker:tinkerbook_diary");
        d = this.ben1doc;
        this.initManual(
            this.ben1,
            "basicManual.6",
                d,
            "tinker:tinkerbook_diary");
        d = this.ben2doc;
        this.initManual(
            this.ben2,
            "basicManual.7",
                d,
            "tinker:tinkerbook_diary");
        d = this.ben3doc;
        this.initManual(
            this.ben3,
            "basicManual.8",
                d,
            "tinker:tinkerbook_diary");
        d = this.ben4doc;
        this.initManual(
            this.ben4,
            "basicManual.9",
                d,
            "tinker:tinkerbook_diary");
        d = this.sarah1Doc;
        this.initManual(
            this.sarah1,
            "basicManual.12",
                d,
            "tinker:tinkerbook_diary");
        d = this.sarah2Doc;
        this.initManual(
            this.sarah2,
            "basicManual.13",
                d,
            "tinker:tinkerbook_diary");
        d = this.darkDoc;
        this.initManual(
            this.dark,
            "basicManual.14",
                d,
            "tinker:tinkerbook_diary");
    }

    public void initManual(BookData data, String unlocName, Document xmlDoc, String itemImage) {
        data.unlocalizedName = unlocName;
        data.toolTip = unlocName;
        data.modID = "item." + DEFS.MODID;
        data.itemImage = new ResourceLocation(data.modID, itemImage);
        data.doc = xmlDoc;
        BookDataStore.addBook(data);
    }

    public void readManuals() {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        this.mats0Doc = this.readManual("/assets/cavestokingdoms/manuals/materials_0.xml", dbFactory);
        this.mats1Doc = this.readManual("/assets/cavestokingdoms/manuals/materials_1.xml", dbFactory);
        this.mats2Doc = this.readManual("/assets/cavestokingdoms/manuals/materials_2.xml", dbFactory);
        this.mats3Doc = this.readManual("/assets/cavestokingdoms/manuals/materials_3.xml", dbFactory);
        this.mats4Doc = this.readManual("/assets/cavestokingdoms/manuals/materials_4.xml", dbFactory);
        this.mats5doc = this.readManual("/assets/cavestokingdoms/manuals/materials_5.xml", dbFactory);
        this.ben1doc = this.readManual("/assets/cavestokingdoms/manuals/xillith_1.xml", dbFactory);
        this.ben2doc = this.readManual("/assets/cavestokingdoms/manuals/xillith_2.xml", dbFactory);
        this.ben3doc = this.readManual("/assets/cavestokingdoms/manuals/xillith_3.xml", dbFactory);
        this.ben4doc = this.readManual("/assets/cavestokingdoms/manuals/xillith_4.xml", dbFactory);
        this.sarah1Doc = this.readManual("/assets/cavestokingdoms/manuals/sarah_1.xml", dbFactory);
        this.sarah2Doc = this.readManual("/assets/cavestokingdoms/manuals/sarah_2.xml", dbFactory);
        this.darkDoc = this.readManual("/assets/cavestokingdoms/manuals/dark.xml", dbFactory);
    }

    Document readManual(String location, DocumentBuilderFactory dbFactory) {
        try {
            InputStream stream = CavesToKingdoms.class.getResourceAsStream(location);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stream);
            doc.getDocumentElement()
                .normalize();

            return doc;
        } catch (Exception e) {
            CavesToKingdoms.logger.error("Error reading manual: {}", e);
            return null;
        }
    }

}
