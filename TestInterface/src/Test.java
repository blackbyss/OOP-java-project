import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Client c = new Client("Mati","Kala", "M",22,"mingiemail@gmail.com",56882923,"Kõrsiku tee 22","Sakala",72009,false,500.20);
        Owner o = new Owner("Kati","Tala", "N",27,"mingiTeineemail@gmail.com",56882923,"Aardla 11","Sakala",81003);

        Event kontsert = new Event("Saku suurhall",5000,o);
        EventTicket pilet = new EventTicket("Raju Reede",75.50,"seisukoht",kontsert);

        c.buy(pilet);

        List<String> cList=c.getHistory();
        List<String> oList=o.getHistory();

        for (String s : oList) {
            System.out.println(s);
        }
    }
}
