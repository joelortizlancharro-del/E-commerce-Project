package utils;
import java.sql.*;

public class ConnexioBD{
    static String URL;
    static final String USER = "root34";
    static final String PASSWORD = ""; 
    public static Connection conn;
    //
    public ConnexioBD(String nomBD){
        URL= "jdbc:mysql://127.0.0.1:3306/" + nomBD + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    }

    public  void establirConexio(){
        try {
             conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }

    public ResultSet consultaUsuaris(){
        ResultSet rs=null;
        try{
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM usuaris");
        }catch(Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public int inserirUsuri(String usuari, int contrassenya,String correu){
        String sql = "INSERT INTO usuaris (usuari, contrassenya, correu) VALUES (?, ?, ?)";
        int estat=0;
        try{ 
            PreparedStatement ps = conn.prepareStatement(sql);
        
            ps.setString(1, usuari);
            ps.setInt(2, contrassenya);
            ps.setString(3, correu);

            ps.executeUpdate();
            estat=1;
        }catch(Exception e){
            e.printStackTrace();
            estat=0;
        }
        return estat;
    }
    


}