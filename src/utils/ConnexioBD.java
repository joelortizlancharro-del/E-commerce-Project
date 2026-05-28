package utils;
import java.sql.*;
//import io.github.cdimascio.dotenv.Dotenv;

public class ConnexioBD{
    
    static String URL;
    static final String USER = "root";/*System.getenv("DB_USER");*/
    static final String PASSWORD = "";/*System.getenv("DB_PASSWORD"); */
    public static Connection conn;
    //
    public ConnexioBD(String nomBD){
        URL= "jdbc:mysql://127.0.0.1:3306/" + nomBD + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    }

    public  boolean establirConexio(){
        boolean result = true;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        
        return result;
    }
    
    /*public ResultSet selectArticles() {
        ResultSet rs=null;
        try{
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM articles");
        }catch(Exception e){
            e.printStackTrace();
        }
        return rs;
    }*/

    public ResultSet selectArticles() {
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM articles");
            if (rs.next()) {
                while (rs.next()) {
                    System.out.println("Nom: " + rs.getString("nom"));
                    if (rs.getInt("familia") == 1) {
                        System.out.println("Familia: Camisa");
                        System.out.println("Talla Coll: " + rs.getInt("talla_coll"));
                        System.out.println("Amplada Pit: " + rs.getInt("amplada_pit"));
                    } else {
                        System.out.println("Familia: Pantalo");
                        System.out.println("Talla Cintura: " + rs.getInt("talla_cintura"));
                        System.out.println("Llargada Camal: " + rs.getInt("llargada_camal"));
                    }
                    System.out.println("Preu Base: " + rs.getBigDecimal("preu_base"));
                    System.out.println("Preu Base: " + rs.getInt("iva"));
                    System.out.println("Stock: " + rs.getString("stock"));
                }

            } else {
                System.out.println("No existeixen articles");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet selectArticlesById(int id) {
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM articles WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public void insertArticle(int id, String nom, int familia,
                            int tallaColl_cintura,
                            int ampladaPit_llargadaCamal,
                            double preu_base, int iva, int stock){

        try{

            String sql;

            if(familia == 1){
                sql = "INSERT INTO articles " +
                    "(id, nom, familia, talla_coll, amplada_pit, preu_base, iva, stock) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            } else {
                sql = "INSERT INTO articles " +
                    "(id, nom, familia, talla_cintura, llargada_camal, preu_base, iva, stock) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            }

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, nom);
            ps.setInt(3, familia);
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

    public void updateArticle(int id, String nom, int familia,
                            int tallaColl_cintura,
                            int ampladaPit_llargadaCamal,
                            double preu_base, int iva, int stock){

        try{

            String sql;

            if(familia == 1){
                sql = "UPDATE articles SET " +
                    "nom = ?, familia = ?, talla_coll = ?, amplada_pit = ?, preu_base = ?, iva = ?, stock = ? " +
                    "WHERE id = ?";
            } else {
                sql = "UPDATE articles SET " +
                    "nom = ?, familia = ?, talla_coll = ?, amplada_pit = ?, preu_base = ?, iva = ?, stock = ? " +
                    "WHERE id = ?";
            }

            PreparedStatement ps = conn.prepareStatement(sql);

            
            ps.setString(1, nom);
            ps.setInt(2, familia);
            ps.setInt(3, tallaColl_cintura);
            ps.setInt(4, ampladaPit_llargadaCamal);
            ps.setDouble(5, preu_base);
            ps.setInt(6, iva);
            ps.setInt(7, stock);
            ps.setInt(8, id);

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
    }
}