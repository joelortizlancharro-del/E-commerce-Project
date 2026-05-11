package utils;
import java.sql.*;

public class ConnexioBD{
    static String URL;
    static final String USER = "root";
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
    
    public ResultSet selectArticlesById(int id) {
        ResultSet rs=null;
            try{
                Statement stmt = conn.createStatement();
                String sql = "SELECT * FROM articles WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                rs = stmt.executeQuery(sql);
            }catch(Exception e){
                e.printStackTrace();
            }
        return rs;
    }

    public ResultSet insertArticle(int id, String nom, String familia, int tallaColl_cintura, int ampladaPit_llargadaCamal, double preu_base, int iva, int stock){
        ResultSet rs=null;
        try{
                Statement stmt = conn.createStatement();
                String sql = "INSERT INTO articles (id, nom, familia, ?, ?, preu_base, iva, stock) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
                PreparedStatement ps = conn.prepareStatement(sql);
                if(familia.equals("camisa")){
                    ps.setString(1, "talla_coll");
                    ps.setString(2, "amplada_pit");
                }else {
                    ps.setString(1, "talla_cintura");
                    ps.setString(2, "llargada_camal");
                }
                ps.setInt(3, id);
                ps.setString(4, nom);
                ps.setString(5, familia);
                ps.setInt(6, tallaColl_cintura);
                ps.setInt(7, ampladaPit_llargadaCamal);
                ps.setDouble(8, preu_base);
                ps.setInt(9, iva);
                ps.setInt(10, stock);
                rs = stmt.executeQuery(sql);
            }catch(Exception e){
                e.printStackTrace();
            }
        return rs;
    }

    public ResultSet deleteArticle(int id) {
        ResultSet rs=null;
        try{
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM articles WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = stmt.executeQuery(sql);
         }catch(Exception e){
                e.printStackTrace();
            }
        return rs;
    }

}