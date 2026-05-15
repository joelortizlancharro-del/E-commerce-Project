package utils;

import java.util.ArrayList;
import java.sql.*;

public class Venta {
    Connection conn = ConnexioBD.conn;

    public void insertVenta(String dni, ArrayList<Integer> idArticles, ArrayList<Integer> quantitats) {
        String sqlTiquet = "INSERT INTO tiquets (data_compra, dni_client) VALUES (CURDATE(), ?)";
        String sqlLinia = "INSERT INTO detalls_tiquet (id_tiquet, id_article, quantitat) VALUES (?, ?, ?)";

        try (PreparedStatement psTiquet = conn.prepareStatement(sqlTiquet, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement psLinia = conn.prepareStatement(sqlLinia)) {

            psTiquet.setString(1, dni);
            psTiquet.executeUpdate();
            System.out.println("Tiquet insertat correctament");

            int idTiquet = -1;
            try (ResultSet rs = psTiquet.getGeneratedKeys()) {
                if (rs.next()) {
                    idTiquet = rs.getInt(1); // Recupera el primer valor (el ID autoincremental)
                }
            }
            if (idTiquet == -1) {
                throw new SQLException("No s'ha pogut obtenir l'ID del tiquet generat.");
            }
            for (int i = 0; i < idArticles.size(); i++) {
                psLinia.setInt(1, idTiquet);
                psLinia.setInt(2, idArticles.get(i));
                psLinia.setInt(3, quantitats.get(1));

                psLinia.executeUpdate();
                System.out.println("Article " + idArticles.get(i) + " insertat al tiquet " + idTiquet);
            }
        } catch (SQLException e) {
            System.err.println("Error en la base de dades: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
