package com.ticket.functionalities;

import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDPixelMap;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class pdfloomine {
    public static void pildigaPdf(long piletikood) throws IOException, COSVisitorException {

        PDDocument pilet = null;
        try {
            pilet = new PDDocument();//loob dokumendi
            PDPage lk = new PDPage();//loob lehekülje
            pilet.addPage(lk);//lisab dokumendile lehekkülje
            PDXObjectImage ximage = null;
            BufferedImage awtImage = ImageIO.read(new File("pilet"+piletikood+".jpg"));//QR  kood mida soovitakse lisada
            ximage = new PDPixelMap(pilet, awtImage);//lisab failile QR koodi
            PDPageContentStream contentStream = new PDPageContentStream(pilet, lk);//lisab leheküljele QR koodi
            contentStream.drawXObject(ximage, 450, 600, ximage.getWidth() , ximage.getHeight());//qr koodi koordinaadid
            contentStream.close();
            pilet.save("pilet"+piletikood+".pdf");
        } finally {
            if (pilet != null) {
                pilet.close();
            }
        }
    }

    public static void tekst(long piletikood) throws Exception {
        String algnePilet = "pilet"+piletikood+".pdf"; // Fail millesse soovin teksti kirjutada
        String tekstigaPilet = piletikood+".pdf"; // Uus fail, millesse tuleb lisatud tekst

        OutputStream fos = new FileOutputStream(new File(tekstigaPilet));

        PdfReader pdfReader = new PdfReader(algnePilet);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, fos);

        PdfContentByte pdfContentByte = pdfStamper.getOverContent(1);//lehekülje nr millele lisatakse muutused

        // Teksti lisamine
        pdfContentByte.beginText();
        pdfContentByte.setFontAndSize(BaseFont.createFont
                (BaseFont.TIMES_BOLD, //Font
                        BaseFont.CP1257,
                        BaseFont.EMBEDDED), 20); // Font-i suurus
        pdfContentByte.setTextMatrix(35, 700); // teksti asukoha x ja y koordinaadid

        pdfContentByte.showText("Sõidupilet"); // Soovitud teksti lisamine
        pdfContentByte.setFontAndSize(BaseFont.createFont
                (BaseFont.TIMES_ROMAN, //Font
                        BaseFont.CP1257,
                        BaseFont.EMBEDDED), 12); // Font-i suurus
        pdfContentByte.setTextMatrix(35, 675);
        pdfContentByte.showText("Kuupäev: " + "kuupäev");//TODO Pärast vaja muuta, sisestatud/tegelikeks andmeteks
        pdfContentByte.setTextMatrix(35, 650);
        pdfContentByte.showText("Ostja eesnimi: " + "eesnimi");
        pdfContentByte.setTextMatrix(35, 625);
        pdfContentByte.showText("Ostja perekonnanimi: " + "perekonnanimi");
        pdfContentByte.setTextMatrix(35, 600);
        pdfContentByte.showText("Lähtekoht: " + "lähtekoht" + " -> Sihtkoht: " + "sihtkoht");

        pdfContentByte.endText();

        pdfStamper.close();
    }
}