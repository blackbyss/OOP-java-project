import java.util.ArrayList;
import java.util.List;

public class Owner extends Person {
    private List<Double> account;
    public Owner(String eesnimi, String perenimi, String sugu, int vanus, String email, long telefon, String aadress, String maakond, long indeks) {
        super(eesnimi, perenimi, sugu, vanus, email, telefon, aadress, maakond, indeks);
        account = new ArrayList<Double>();
    }

    public void addToAccount(double sum){
        account.add(sum);
    }
}
