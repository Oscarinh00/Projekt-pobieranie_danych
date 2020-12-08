import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://pl.steelseries.com/gaming-keyboards").get();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        Element element1 = doc.getAllElements().first();
        // System.out.println(element1.toString());

        Elements elements1 = element1.getAllElements();
       // System.out.println(elements1.get(2));
       // System.out.println(element1.getElementById("myBtn"));
       // System.out.println(element1.getElementById("myBtn").text());
       // System.out.println("#########################");
       // System.out.println(element1.select("catalog-list-product__name h--200 OneLinkNoTx").get(10).text());

       // System.out.println(element1.getElementsByClass("catalog-list-product__content").first());
       // System.out.println(element1.getElementsByClass("catalog-list-product__name h--200 OneLinkNoTx").get(2).text());
       // System.out.println(element1.select("td").attr("class", "base_txt").first());
        ArrayList<ArrayList> klawiatury = new ArrayList<ArrayList>();
        for(int i=0; i < 10; i++) {
            ArrayList<String> beka = new ArrayList<String>();
            beka.add(element1.getElementsByClass("catalog-list-product__name h--200 OneLinkNoTx").get(i).text());
            beka.add(element1.getElementsByClass("catalog-list-product__msrp").get(i).text());
            beka.add(element1.getElementsByClass("catalog-list-product__description").get(i).text());
            beka.add(element1.getElementsByClass("catalog-list-product__related-text").get(i).text());
            klawiatury.add(beka);
        }
        System.out.println(klawiatury);


      //  Document doc2 = null;
      //  try {
      //      doc2 = Jsoup.connect("https://pl.steelseries.com/gaming-headsets").get();
      //  }
      //  catch(IOException e){
      //      e.printStackTrace();
      //  }
      //  Element element2 = doc.getAllElements().first();

      //  ArrayList<ArrayList> sluchawki = new ArrayList<ArrayList>();
      //  for(int i=0; i < 10; i++) {
      //      ArrayList<String> beka = new ArrayList<String>();
      //      beka.add(element2.getElementsByClass("catalog-list-product__name h--200 OneLinkNoTx").get(i).text());
      //      beka.add(element2.getElementsByClass("catalog-list-product__msrp").get(i).text());
      //      beka.add(element2.getElementsByClass("catalog-list-product__description").get(i).text());
      //      beka.add(element2.getElementsByClass("catalog-list-product__related-text").get(i).text());
      //      sluchawki.add(beka);
      //  }
      //  System.out.println(sluchawki);

    }
}
