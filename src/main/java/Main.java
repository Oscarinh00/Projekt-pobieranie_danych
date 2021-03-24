import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    //public static ArrayList<ArrayList> klawiatury = new ArrayList<ArrayList>();
    public static ArrayList<Dane> klawiatury = new ArrayList<Dane>();
    private static Statement stmt;
    private static ResultSet results;

    public static void main(String[] args) throws SQLException {

        Document doc = null;
        try {
            doc = Jsoup.connect("https://pl.steelseries.com/gaming-keyboards").get();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Element element1 = doc.getAllElements().first();
        Elements elements1 = element1.getAllElements();
        //for (int i = 0; i < 10; i++) {
        //    ArrayList<String> prod = new ArrayList<String>();
        //    prod.add(element1.getElementsByClass("catalog-list-product__name h--200 OneLinkNoTx").get(i).text());
        //    prod.add(element1.getElementsByClass("catalog-list-product__msrp").get(i).text());
        //    prod.add(element1.getElementsByClass("catalog-list-product__description").get(i).text());
        //    prod.add(element1.getElementsByClass("catalog-list-product__related-text").get(i).text());
        //    klawiatury.add(prod);
        //}

        Connection conn = DBConnection.createNewDBconnection();

        try {
            stmt = conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("Co mam zrobić?");
        System.out.println("Opcja 1: Pobrać dane.");
        System.out.println("Opcja 2: Wyświetlić dane.");
        System.out.println("Proszę wpisać 1 lub 2");
        String funkcja  = scan.nextLine();
        int wartosc = Integer.parseInt(funkcja);

        if (wartosc == 1) {

            for (int i = 0; i < 10; i++) {
                LocalDate czas = LocalDate.now();
                Dane klawiatura = new Dane(element1.getElementsByClass("catalog-list-product__name h--200 OneLinkNoTx").get(i).text(),
                        element1.getElementsByClass("catalog-list-product__msrp").get(i).text(),
                        element1.getElementsByClass("catalog-list-product__description").get(i).text() +", " +
                        element1.getElementsByClass("catalog-list-product__related-text").get(i).text(), czas);
                klawiatury.add(klawiatura);

                String sqlInsert = "INSERT INTO produkty (nazwa, cena, opis, data_pobrania) VALUES (?,?,?,?)";
                PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert);
                stmtInsert.setString(1, klawiatury.get(i).getNazwa());
                stmtInsert.setString(2, klawiatury.get(i).getCena());
                stmtInsert.setString(3, klawiatury.get(i).getOpis());
                stmtInsert.setDate(4, klawiatury.get(i).getData());
                stmtInsert.execute();
            }

            LocalDateTime czas2 = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String formatted = formatter.format(czas2);
            System.out.println("Data pobrania danych: " + formatted);

            Print.dotxt();
        }
        else if ( wartosc == 2){
            try{
                String sqlSelect = "SELECT * FROM produkty";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sqlSelect);
                while (rs.next()) {
                    String nazwa = rs.getString("nazwa");
                    String cena = rs.getString("cena");
                    String opis = rs.getString("opis");
                    Date data = rs.getDate("data_pobrania");

                    System.out.format(" %s, %s, %s, %s\n", nazwa, cena, opis, data);
                }
                st.close();
            }
            catch (Exception e) {
                System.err.println("Błąd!");
            }
        }
    }
}