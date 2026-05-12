package utils;
import java.sql.*;
//import io.github.cdimascio.dotenv.Dotenv;

public class ConnexioBD{
    
    static String URL;
<<<<<<< HEAD
    static final String USER = "root";/*System.getenv("DB_USER");*/
    static final String PASSWORD = "Programicion24!";/*System.getenv("DB_PASSWORD"); */
=======
    static final String USER = "root";
    static final String PASSWORD = "";
>>>>>>> fix/BD
    public static Connection conn;
    //
    public ConnexioBD(String nomBD){
        URL= "jdbc:mysql://127.0.0.1:3306/" + nomBD + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    }

<<<<<<< HEAD
    public boolean establirConexio(){
=======
    public  boolean establirConexio(){
>>>>>>> fix/BD
        boolean result = true;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
<<<<<<< HEAD
        
=======
>>>>>>> fix/BD
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
    public void insertArticle(int id, String nom, String familia,
                            int tallaColl_cintura,
                            int ampladaPit_llargadaCamal,
                            double preu_base, int iva, int stock){

        try{

            String sql;

            if(familia.equals("camisa")){
                sql = "INSERT INTO articles " +
                    "(id, nom, familia, talla_coll, amplada_pit, preu_base, iva, stock) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            } else {
                sql = "INSERT INTO articles " +
                    "(id, nom, familia, talla_cintura, llargada_camal, preu_base, iva, stock) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            }

            PreparedStatement ps = conn.prepareStatement(sql);
<<<<<<< HEAD
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
                String sql = "UPDATE articles nom = ? , familia = ?, ? = ?, ? = ?, preu_base = ?, iva = ?, stock = ? WHERE id = ?;";
                
                PreparedStatement ps = conn.prepareStatement(sql);
                if(familia.equals("camisa")){
                    ps.setString(3, "talla_coll");
                    ps.setString(5, "amplada_pit");
                }else {
                    ps.setString(3, "talla_cintura");
                    ps.setString(5, "llargada_camal");
                }
                ps.setString(1, nom);
                ps.setString(2, familia);
                ps.setInt(4, tallaColl_cintura);
                ps.setInt(6, ampladaPit_llargadaCamal);
                ps.setDouble(7, preu_base);
                ps.setInt(8, iva);
                ps.setInt(9, stock);
                ps.setInt(10, id);
                rs = stmt.executeQuery(sql);
            }catch(Exception e){
                e.printStackTrace();
            }
        return rs;
=======

            ps.setInt(1, id);
            ps.setString(2, nom);
            ps.setString(3, familia);
            ps.setInt(4, tallaColl_cintura);
            ps.setInt(5, ampladaPit_llargadaCamal);
            ps.setDouble(6, preu_base);
            ps.setInt(7, iva);
            ps.setInt(8, stock);

            ps.executeUpdate();

            System.out.println("Article insertat correctament");

        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deleteArticle(int id) {

        try{

            String sql = "DELETE FROM articles WHERE id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Article eliminat");

        } catch(Exception e){
            e.printStackTrace();
        }
>>>>>>> fix/BD
    }
}