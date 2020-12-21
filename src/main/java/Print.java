import java.io.FileWriter;
import java.io.IOException;

public class Print{
    public static void dotxt() {
        try {
            FileWriter plikTxt = new FileWriter("dane.txt", true);
            for (int i = 0; i < 10; i++) {
                plikTxt.write(String.valueOf(Main.klawiatury.get(i)));
                plikTxt.write("\r\n");
            }
            plikTxt.close();
        } catch (IOException e) {
            System.out.print("Błąd!");
            return;
        }

    }
}