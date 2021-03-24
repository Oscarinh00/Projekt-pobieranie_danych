import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static String dbhost = "jdbc:mysql://localhost:3306/klawiatury";
    private static String username = "root";
    private static String password = "";
    private static Connection conn;

    public static Connection createNewDBconnection() {
        try  {
            conn = DriverManager.getConnection(
                    dbhost, username, password);
           // System.out.println("Połączono. Wszystko OK.");
        } catch (SQLException e) {
           // System.out.println("Nie można utworzyć połączenia z bazą danych!");
            e.printStackTrace();
        } finally {
            return conn;
        }
    }
}