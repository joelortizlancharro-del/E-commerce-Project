import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.ConnexioBD;

public class ClientDAO {
    public void afegirClient(Clients client) {
        Connection conn = ConnexioBD.conn;
        String query = "INSERT INTO clients (dni, nom, email, telefon) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, client.getDni());
            ps.setString(2, client.getNom());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getTelefon());
            ps.executeUpdate();
            System.out.println("Client afegit correctament");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void esborrarClient(String dni) {
        if (dni.equals("000")) {
            System.out.println("No es pot esborrar un client genèric");
            return;
        }
        String query = "DELETE FROM clients Where dni =?";
        try {
            PreparedStatement ps = ConnexioBD.conn.prepareStatement(query);
            ps.setString(1, dni);
            ps.executeUpdate();
            System.out.println("Esborrat correctament");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificarClient(Clients client) {
        if (client.getDni().equals("000")) {
            System.out.println("No es pot modificar el client genèric");
            return;
        }
        String query = "UPDATE clients set nom=?, email=?, telefon=? WHERE dni=?";
        try {
            PreparedStatement ps = ConnexioBD.conn.prepareStatement(query);
            ps.setString(1, client.getNom());
            ps.setString(2, client.getEmail());
            ps.setString(3, client.getTelefon());
            ps.setString(4, client.getDni());
            int files = ps.executeUpdate();

            if (files == 0) {
                System.out.println("No existeix cap client amb aquest DNI");
            } else {
                System.out.println("Client modificat correctament");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void consultarClient(String dni) {
        String query = "SELECT nom, email, telefon FROM clients WHERE dni = ?";
        try {
            PreparedStatement ps = ConnexioBD.conn.prepareStatement(query);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("DNI: " + dni);
                System.out.println("Nom: " + rs.getString("nom"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Telèfon: " + rs.getString("telefon"));
            } else {
                System.out.println("No existeix cap client amb aquest DNI");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void llistarClients() {
        String query = "Select * from clients";
        try {
            PreparedStatement ps = ConnexioBD.conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("DNI: " + rs.getString("dni") +
                        " | Nom: " + rs.getString("nom") +
                        " | Email: " + rs.getString("email") +
                        " | Telèfon: " + rs.getString("telefon"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
