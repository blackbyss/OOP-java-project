package com.ticket.ticketproject.functionalities;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Qr {

    public static ByteArrayOutputStream qrGenereerija(String hash) throws IOException {
        String pilet = "Pileti hash: " + hash;//Pileti info
        ByteArrayOutputStream out = QRCode.from(pilet).to(ImageType.JPG).stream();
        return out;
    }
} 