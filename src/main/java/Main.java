import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static ArrayList<ArrayList> klawiatury = new ArrayList<ArrayList>();

    public static void main(String[] args) throws IOException {

        Document doc = null;
        try {
            doc = Jsoup.connect("https://pl.steelseries.com/gaming-keyboards").get();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Element element1 = doc.getAllElements().first();
        Elements elements1 = element1.getAllElements();
        for (int i = 0; i < 10; i++) {
            ArrayList<String> prod = new ArrayList<String>();
            prod.add(element1.getElementsByClass("catalog-list-product__name h--200 OneLinkNoTx").get(i).text());
            prod.add(element1.getElementsByClass("catalog-list-product__msrp").get(i).text());
            prod.add(element1.getElementsByClass("catalog-list-product__description").get(i).text());
            prod.add(element1.getElementsByClass("catalog-list-product__related-text").get(i).text());
            klawiatury.add(prod);
        }
        Print.dotxt();
    }
}