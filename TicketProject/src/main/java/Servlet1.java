import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Servlet1 extends HttpServlet {
    Person person;
    public void service (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String eesnimi = req.getParameter("eesnimi");
        String perenimi = req.getParameter("perenimi");
        String sugu = req.getParameter("sugu");
        String email = req.getParameter("email");
        String telefon = req.getParameter("telefon");
        String aadress = req.getParameter("aadress");
        String asukoht = req.getParameter("maakond");
        String postiindeks = req.getParameter("indeks");
        String kasEmail = req.getParameter("yes-email");

        boolean kasMeil=false;
        if(kasEmail.equals("1")){
            kasMeil = true;
        }
        Person person = new Person(eesnimi,perenimi,"tuleb HTMLi muuta",20,email,Long.parseLong(telefon),aadress,asukoht,Long.parseLong(postiindeks));

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String title = "Using GET Method to Read Form Data";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";

        out.println(docType +
                "<html>"+
                "<head>"+
                "<title>Thank you!</title>"+

                "<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css'>"+
                "<link rel='stylesheet' href='css\\confirmation.css'>"+
                "</head>"+
                "<body>"+
                "<div class='container'>"+
                "<h1>Aitäh, et kasutasite meie piletiteenust!</h1>"+
                "<h3>Kiri saadeti aadressile:  "+person.getEmail()+"</h3>"+

                "<a href='index.html'>Tagasi</a>"+
                "</div>"+
                "</body>"+
                "</html>"
        );
    }

}
