import java.sql.*;
import utils.ConnexioBD;

public class App {
    static Connection conn;
    static ConnexioBD conexio=null;
    public static void main(String[] args) throws Exception {
        conexio = new ConnexioBD("tpv_botiga");
        conexio.establirConexio();
        
    }
}
