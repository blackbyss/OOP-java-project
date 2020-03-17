import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet1 extends HttpServlet {
    public void service (HttpServletRequest req, HttpServletResponse res) throws IOException {
        String eesnimi = req.getParameter("eesnimi");
        String perenimi = req.getParameter("perenimi");
        String sugu = req.getParameter("sugu");
        String email = req.getParameter("email");
        String telefon = req.getParameter("telefon");
        String aadress = req.getParameter("aadress");
        String asukoht = req.getParameter("maakond");
        String postiindeks = req.getParameter("indeks");
        String kasEmail = req.getParameter("yes-email");
        System.out.println(eesnimi);
        System.out.println(sugu);
        res.getWriter().println(eesnimi);
    }
}
