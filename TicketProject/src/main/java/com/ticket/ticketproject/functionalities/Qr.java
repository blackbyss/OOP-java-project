package com.ticket.ticketproject.functionalities;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Qr {
    static long piletiKood = Math.abs(new Random().nextLong());

    public static void qrGenereerija(long piletiKood) throws IOException {
        String pilet = "Pileti info";//Pileti info
        System.out.println(piletiKood);
        ByteArrayOutputStream out = QRCode.from(pilet).to(ImageType.JPG).stream();
        File fail = new File("pilet" + piletiKood + ".jpg");//Fail kuhu QR kood salvestatakse
        FileOutputStream fos = new FileOutputStream(fail);

        fos.write(out.toByteArray());
        fos.flush();
    }
} 