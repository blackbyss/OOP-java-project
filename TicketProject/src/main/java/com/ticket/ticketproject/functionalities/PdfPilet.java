package com.ticket.ticketproject.functionalities;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class PdfPilet {
    public static void pdf(long piletikood) throws Exception {

        File file = new File("pilet"+piletikood+".pdf");
        PDDocument doc = new PDDocument();

        PDPage page = new PDPage();
        doc.addPage(page);
        page = doc.getPage(0);

        PDImageXObject pdImage = PDImageXObject.createFromByteArray(doc, Qr.qrGenereerija(piletikood).toByteArray(),"pdftest.pdf");

        PDPageContentStream contents = new PDPageContentStream(doc, page);

        //Pildi ja teksti lisamine
        contents.setLeading(25f);
        contents.drawImage(pdImage, 450, 600);
        contents.moveTo(35,690);
        contents.lineTo(400,690);
        contents.stroke();
        contents.beginText();
        contents.newLineAtOffset(35, 700);
        contents.setFont(PDType1Font.TIMES_BOLD, 20);
        contents.showText("Sõidupilet");

        contents.newLine();
        contents.setFont(PDType1Font.TIMES_ROMAN, 14);
        contents.showText("Kuupäev");
        contents.newLine();
        contents.showText("Pileti ID");
        contents.newLine();
        contents.showText("Ürituse nimi");
        contents.newLine();
        contents.showText("Pileti tüüp");
        contents.newLine();
        contents.showText("Pileti hind");

        contents.newLineAtOffset(35, 700);
        contents.setFont(PDType1Font.TIMES_ROMAN, 14);
        contents.showText("Kuupäev");

        contents.endText();

        contents.close();

        doc.save("pilet"+piletikood+".pdf");//Salvestab dokumendi

        doc.close();//suleb dokumendi

    }
}