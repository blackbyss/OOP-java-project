package com.ticket.ticketproject.Classes;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import java.io.*;

public class Qr {

    public static void qrGenereerija() throws Exception {

        String pilet = "Pileti info";//Pileti info
        ByteArrayOutputStream out = QRCode.from(pilet).to(ImageType.PNG).stream();//Loob visuaalse QR koodi, peks .JPG töötama aga mul millegi pärast ei töödanud
        File fail = new File("pilet.jpg");//Fail kuhu QR kood salvestatakse
        FileOutputStream fos = new FileOutputStream(fail);

        fos.write(out.toByteArray());
        fos.flush();
    }
}