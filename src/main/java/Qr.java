import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Qr {

    public static void qrGenereerija(List<String> infoList){

        String pilet = infoList.toString();
        ByteArrayOutputStream out = QRCode.from(pilet).to(ImageType.PNG).stream();
        File fail = new File("pilet.png");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fail);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            fos.write(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}