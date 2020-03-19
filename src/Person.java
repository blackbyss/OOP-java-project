public class Person {
    private String eesnimi;
    private String perenimi;
    private String sugu;
    private String email;
    private long telefon;
    private String aadress;
    private String maakond;
    private long indeks;
    boolean yes_mail;

    public Person(String eesnimi, String perenimi, String sugu, String email, long telefon, String aadress, String maakond, long indeks, boolean yes_mail) {
        this.eesnimi = eesnimi;
        this.perenimi = perenimi;
        this.sugu = sugu;
        this.email = email;
        this.telefon = telefon;
        this.aadress = aadress;
        this.maakond = maakond;
        this.indeks = indeks;
        this.yes_mail = yes_mail;
    }

    public String getEesnimi() {
        return eesnimi;
    }

    public String getPerenimi() {
        return perenimi;
    }

    public String getEmail() {
        return email;
    }

    public long getTelefon() {
        return telefon;
    }
}
