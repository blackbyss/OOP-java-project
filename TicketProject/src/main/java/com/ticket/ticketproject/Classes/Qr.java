package com.ticket.ticketproject.Classes;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Qr {

    public static ByteArrayOutputStream qrGenereerija(long piletiKood) throws IOException {
        String pilet = "Pileti info";//Pileti info
        ByteArrayOutputStream out = QRCode.from(pilet).to(ImageType.JPG).stream();
        return out;
    }
} 