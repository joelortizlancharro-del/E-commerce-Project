package utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.sql.*;

public class Venta {
    Connection conn = ConnexioBD.conn;

    public void insertVenta(String dni, ArrayList<Integer> idArticles, ArrayList<Integer> quantitats) {
        String sqlTiquet = "INSERT INTO tiquets (data_compra, dni_client) VALUES (CURDATE(), ?)";
        // Asumo que tu tabla de líneas se llama 'detalls_tiquet' según tu código anterior
        String sqlLinia = "INSERT INTO linies_factura (id_tiquet, id_article, quantitat) VALUES (?, ?, ?)";
        
        // SQL para obtener la suma de los DECIMALES calculados por tu TRIGGER
        String sqlSelectTotals = "SELECT SUM(preu_base) AS total_base, SUM(preu_final) AS total_final " +
                                 "FROM linies_factura WHERE id_tiquet = ?";
        
        // SQL para actualizar el tiquet con los totales definitivos
        String sqlUpdateTiquet = "UPDATE tiquets SET total_base = ?, total_iva = ?, total_final = ? WHERE id = ?";

        try (PreparedStatement psTiquet = conn.prepareStatement(sqlTiquet, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psLinia = conn.prepareStatement(sqlLinia);
             PreparedStatement psSelect = conn.prepareStatement(sqlSelectTotals);
             PreparedStatement psUpdate = conn.prepareStatement(sqlUpdateTiquet)) {

            // 1. Insertar la cabecera del Tiquet
            psTiquet.setString(1, dni);
            psTiquet.executeUpdate();
            System.out.println("Tiquet insertat correctament");

            int idTiquet = -1;
            try (ResultSet rs = psTiquet.getGeneratedKeys()) {
                if (rs.next()) {
                    idTiquet = rs.getInt(1); 
                }
            }
            
            if (idTiquet == -1) {
                throw new SQLException("No s'ha pogut obtenir l'ID del tiquet generat.");
            }
            
            // 2. Insertar las líneas (El TRIGGER de la BD actuará aquí fila por fila)
            for (int i = 0; i < idArticles.size(); i++) {
                psLinia.setInt(1, idTiquet);
                psLinia.setInt(2, idArticles.get(i));
                psLinia.setInt(3, quantitats.get(i)); // CORREGIDO: antes tenías .get(1) fijo

                psLinia.executeUpdate();
                System.out.println("Article " + idArticles.get(i) + " insertat al tiquet " + idTiquet);
            }

            // 3. ACCEDER Y MANIPULAR EL RESULTSET CON LOS VALORES DECIMAL
            psSelect.setInt(1, idTiquet);
            try (ResultSet rsTotals = psSelect.executeQuery()) {
                if (rsTotals.next()) {
                    // Extraemos los valores usando getBigDecimal para mantener la precisión decimal
                    BigDecimal totalBase = rsTotals.getBigDecimal("total_base");
                    BigDecimal totalFinal = rsTotals.getBigDecimal("total_final");

                    // Validamos que no sean nulos (por si el tiquet se quedó sin líneas de forma extraña)
                    if (totalBase == null) totalBase = BigDecimal.ZERO;
                    if (totalFinal == null) totalFinal = BigDecimal.ZERO;
                    BigDecimal total_iva = totalFinal.subtract(totalBase);
                    // 4. EJECUTAR EL UPDATE FINAL EN EL TIQUET
                    psUpdate.setBigDecimal(1, totalBase);
                    psUpdate.setBigDecimal(2, total_iva);
                    psUpdate.setBigDecimal(3, totalFinal);
                    psUpdate.setInt(4, idTiquet);
                    
                    psUpdate.executeUpdate();
                    System.out.println(">> Totals del tiquet " + idTiquet + " actualitzats. " +
                                       "Base: " + totalBase + "€ | Total: " + totalFinal + "€");
                }
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