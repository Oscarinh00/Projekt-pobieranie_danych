import java.sql.Date;
import java.time.LocalDate;

public class Dane {
    private String nazwa;
    private String cena;
    private String opis;
    private LocalDate data;

    public Dane(String nazwa, String cena, String opis, LocalDate data){
        this.nazwa = nazwa;
        this.cena = cena;
        this.opis = opis;
        this.data = data;
    }

    public Date getData() { return Date.valueOf(data); }

    public String getNazwa() {
        return nazwa;
    }

    public String getCena() {
        return cena;
    }

    public String getOpis() {
        return opis;
    }

    @Override
    public String toString() {
        return "Dane{" +
                "nazwa='" + nazwa + '\'' +
                ", cena='" + cena + '\'' +
                ", opis='" + opis + '\'' +
                ", data=" + data +
                '}';
    }
}