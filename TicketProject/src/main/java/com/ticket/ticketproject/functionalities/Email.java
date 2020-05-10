package com.ticket.ticketproject.functionalities;

import com.ticket.ticketproject.dataStorage.FormData;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Email extends FormData {
    public void email(String to) throws IOException {
        String from = "piletikuller@gmail.com";//Saatja meil

        File folder = new File(System.getProperty("user.dir") + "\\piletid");
        File[] listOfFiles = folder.listFiles();
        String file;
        String fileName;
        if (listOfFiles.length == 1) {
            file = folder + "\\" + listOfFiles[0].getName();
            fileName = listOfFiles[0].getName();
        } else {
            ZipDirectory zipDirectory = new ZipDirectory();
            zipDirectory.generateFileList(new File(System.getProperty("user.dir") + "\\piletid"));
            zipDirectory.zipIt(System.getProperty("user.dir") + "\\piletid.zip");
            file = System.getProperty("user.dir") + "\\piletid.zip";
            fileName = "piletid.zip";

        }
        final String username = from;//Saatja meil
        final String password = "saadauusp1let";//Saatja parool

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            message.setSubject("Pilet");//Teema
            BodyPart messageBodyPart = new MimeBodyPart();//Loob sisu

            messageBodyPart.setText("Täname, et valisite meie piletiteenuse!\n" +//kirja sisu
                    "\n" +
                    "Sisenemisõiguse tõendamiseks esitage pilet  klienditeenindajale paberkujul või elektroonselt piletil olevat QR-koodi näidates.\n" +
                    "Kaardile ostetud pileti korral esitage rongis klienditeenindajale sõidukaart.\n" +
                    "\n" +
                    "Sooduspileti puhul palume esitada ka soodustust tõendav dokument.\n" +
                    "\n" +
                    "Küsimuste korral pöörduge meie klienditoe poole: 69696969 (24h) või info@pilet.ee.\n" +
                    "\n" +
                    "Head elamust!");

            Multipart multipart = new MimeMultipart();

            multipart.addBodyPart(messageBodyPart);

            // Faili lisamine
            messageBodyPart = new MimeBodyPart();
            // String file = "pom.xml";
            DataSource src = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(src));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);//Saadab meili

            System.out.println("Meil saadetud");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}