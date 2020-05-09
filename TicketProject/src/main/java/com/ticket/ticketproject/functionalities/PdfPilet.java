package com.ticket.ticketproject.functionalities;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class PdfPilet {
    public String pdf(long piletikood, String[] info) throws Exception {
        String path = System.getProperty("user.dir")+"\\piletid";
        String fileName = "pilet"+piletikood+".pdf";
        String filePath = path+"\\"+fileName;
        File file = new File(filePath);
        PDDocument doc = new PDDocument();

        PDPage page = new PDPage();
        doc.addPage(page);
        page = doc.getPage(0);

        PDImageXObject pdImage = PDImageXObject.createFromByteArray(doc, Qr.qrGenereerija(HashGen.enkrüpteerimine(String.valueOf(piletikood))).toByteArray(),"pdftest.pdf");

        PDPageContentStream contents = new PDPageContentStream(doc, page);

        //Pildi ja teksti lisamine
        contents.setLeading(25f);
        contents.drawImage(pdImage, 450, 570);
        contents.moveTo(35,690);
        contents.lineTo(400,690);
        contents.stroke();
        contents.beginText();
        contents.newLineAtOffset(35, 700);
        contents.setFont(PDType1Font.TIMES_BOLD, 20);
        contents.showText("Pilet: "+piletikood);

        contents.newLine();
        contents.setFont(PDType1Font.TIMES_ROMAN, 14);
        // Kuupäev
        contents.showText(info[0]);
        contents.newLine();
        // Pileti ID
        contents.showText(info[1]);
        contents.newLine();
        // ürituse nimi
        contents.showText(info[2]);
        contents.newLine();
        // pileti tüüp
        contents.showText(info[3]);
        contents.newLine();
        // pileti hind
        contents.showText(info[4]);

        contents.newLineAtOffset(35, 700);
        contents.setFont(PDType1Font.TIMES_ROMAN, 14);
        contents.showText("Kuupäev");

        contents.endText();

        contents.close();

        doc.save(filePath);//Salvestab dokumendi

        doc.close();//suleb dokumendi
        return filePath;
    }
}