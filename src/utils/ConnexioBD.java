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
    
    public ResultSet selectArticles() {
        ResultSet rs=null;
        try{
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM articles");
        }catch(Exception e){
            e.printStackTrace();
        }
        return rs;
    }
    


}