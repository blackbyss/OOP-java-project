package com.ticket.ticketproject.functionalities;

import java.awt.*;
import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class PdfPilet {
    public String pdf(long piletikood, String[] info, int ticketCount) throws Exception {
        String fileName = "pilet" + piletikood + ".pdf";
        File file = new File(fileName);
        PDDocument doc = new PDDocument();
        for (int i = 0; i < ticketCount; i++) {

            PDPage page = new PDPage();
            doc.addPage(page);
            page = doc.getPage(i);

            PDImageXObject pdImage = PDImageXObject.createFromByteArray(doc, Qr.qrGenereerija(HashGen.enkrüpteerimine(String.valueOf(piletikood+i))).toByteArray(), "pdftest.pdf");

            PDPageContentStream contents = new PDPageContentStream(doc, page);

            //Pildi ja teksti lisamine
            contents.setLeading(28f);
            contents.fillRect(30, 525, 100, 30);
            contents.moveTo(440, 690);
            contents.lineTo(440, 525);
            contents.drawImage(pdImage, 450, 570);
            contents.setStrokingColor(199, 210, 0);
            contents.addRect(30, 525, 540, 165);
            contents.stroke();

            contents.beginText();
            contents.newLineAtOffset(435, 700);
            contents.setFont(PDType1Font.COURIER_BOLD_OBLIQUE, 20);
            contents.setStrokingColor(66, 185, 245);
            contents.showText("Piletikuller");
            contents.endText();
            contents.beginText();
            contents.newLineAtOffset(35, 700);
            contents.setFont(PDType1Font.TIMES_BOLD, 20);
            contents.showText("Pilet: " + (piletikood+i));

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
            contents.newLine();
            contents.setFont(PDType1Font.HELVETICA_BOLD, 20);
            contents.setNonStrokingColor(169, 178, 45);
            contents.showText("E-PILET");

            contents.newLineAtOffset(35, 700);
            contents.setFont(PDType1Font.TIMES_ROMAN, 14);
            contents.showText("Kuupäev");
            contents.endText();


            contents.close();
        }
        doc.save("pilet" + piletikood + ".pdf");//Salvestab dokumendi

        doc.close();//suleb dokumendi
        return fileName;
    }
}