import java.sql.SQLException;
import java.sql.*;
import utils.ConnexioBD;

public class VendesClientDAO {
    
    public void consultaVendesClient(String dni) {
        String query = "SELECT clients.dni, clients.nom, COUNT(tiquets.id) as num_tiquets, SUM(tiquets.total_final) as total_gastat "
                +
                "FROM clients JOIN tiquets ON clients.dni = tiquets.dni_client " +
                "WHERE clients.dni = ? GROUP BY clients.dni, clients.nom";
        try {
            PreparedStatement ps = ConnexioBD.conn.prepareStatement(query);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("DNI: " + rs.getString("dni"));
                System.out.println("Nom: " + rs.getString("nom"));
                System.out.println("Nombre de tiquets: " + rs.getInt("num_tiquets"));
                System.out.println("Total gastat: " + rs.getDouble("total_gastat"));
            } else {
                System.out.println(
                        "No s'ha trobat cap client amb aquest DNI, comprova que existeix o que ha dut a terme alguna venta");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
