import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.ConnexioBD;

public class vendesPerArticle {

    public void mostrarPerArticle(int id) {
        String query = "Select articles.id, articles.nom, SUM(linies_factura.quantitat) as quantitat_venguts " +
        "FROM articles JOIN linies_factura ON articles.id = linies_factura.id_article " +
        "WHERE articles.id = ? GROUP BY articles.id, articles.nom";
        try {
            PreparedStatement ps = ConnexioBD.conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("ID " + rs.getInt("id"));
                System.out.println("Nom " + rs.getString("nom"));
                System.out.println("Quantitat venguda " + rs.getInt("quantitat_venguts"));
            } else {
                System.out.println("No s'ha trobat cap article amb aquest codi o no té vendes.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}