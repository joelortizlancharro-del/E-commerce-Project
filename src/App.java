import java.sql.*;
import utils.ConnexioBD;

public class App {
    static Connection conn;
    static ConnexioBD conexio=null;
    public static void main(String[] args) throws Exception {
        App p = new App();
        p.principal();

    }
    public void principal() {
        conexio = new ConnexioBD("tpv_botiga");
        boolean conected = conexio.establirConexio();
        if(conected) {
            System.out.println("conectat");
        }else {
            System.out.println("error conexio");
        }
    }
}
