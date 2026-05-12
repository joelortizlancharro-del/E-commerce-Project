package utils;
import java.sql.*;
//import io.github.cdimascio.dotenv.Dotenv;

public class ConnexioBD{
    
    static String URL;
    static final String USER = "root";/*System.getenv("DB_USER");*/
    static final String PASSWORD = "Programicion24!";/*System.getenv("DB_PASSWORD"); */
    public static Connection conn;
    //
    public ConnexioBD(String nomBD){
        URL= "jdbc:mysql://127.0.0.1:3306/" + nomBD + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    }

    public boolean establirConexio(){
        boolean result = true;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        
        return result;
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
                
                String sql = "";
                    
                if(familia.equals("camisa")){
                    sql = "INSERT INTO articles (id, nom, familia, talla_coll, amplada_pit, preu_base, iva, stock) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
                }else {
                    sql = "INSERT INTO articles (id, nom, familia, talla_cintura, llargada_camal, preu_base, iva, stock) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
                }
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.setString(2, nom);
                ps.setString(3, familia);
                ps.setInt(4, tallaColl_cintura);
                ps.setInt(5, ampladaPit_llargadaCamal);
                ps.setDouble(6, preu_base);
                ps.setInt(7, iva);
                ps.setInt(8, stock);
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
    public ResultSet updateArticle(int id, String nom, String familia, int tallaColl_cintura, int ampladaPit_llargadaCamal, double preu_base, int iva, int stock){
        ResultSet rs=null;
        try{
                Statement stmt = conn.createStatement();
                String sql = "";
                
                PreparedStatement ps = conn.prepareStatement(sql);
                if(familia.equals("camisa")){
                    sql = "UPDATE articles nom = ? , familia = ?, talla_coll = ?, amplada_pit = ?, preu_base = ?, iva = ?, stock = ? WHERE id = ?;";
                }else {
                    sql = "UPDATE articles nom = ? , familia = ?, talla_cintura = ?, llargada_camal = ?, preu_base = ?, iva = ?, stock = ? WHERE id = ?;";
                }
                ps.setString(1, nom);
                ps.setString(2, familia);
                ps.setInt(3, tallaColl_cintura);
                ps.setInt(4, ampladaPit_llargadaCamal);
                ps.setDouble(5, preu_base);
                ps.setInt(6, iva);
                ps.setInt(7, stock);
                ps.setInt(8, id);
                rs = stmt.executeQuery(sql);
            }catch(Exception e){
                e.printStackTrace();
            }
        return rs;
    }
}