/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author 07rrk
 */
public class DatabaseHelper {
    private static final String URL = "jdbc:sqlite:contacts.db"; // Nama file database

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }
public void addContact(String name, String phone, String category) {
    String sql = "INSERT INTO contacts(name, phone, category) VALUES(?, ?, ?)";
    try (Connection conn = DatabaseHelper.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, name);
        pstmt.setString(2, phone);
        pstmt.setString(3, category);
        pstmt.executeUpdate();
    } catch (SQLException e) {
    }
 public List<Contact> getAllContacts() {
    List<Contact> contacts = new ArrayList<>();
    String sql = "SELECT * FROM contacts";
    try (Connection conn = DatabaseHelper.connect();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            contacts.add(new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("phone"), rs.getString("category")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return contacts;
}
public void updateContact(int id, String name, String phone, String category) {
    String sql = "UPDATE contacts SET name = ?, phone = ?, category = ? WHERE id = ?";
    try (Connection conn = DatabaseHelper.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, name);
        pstmt.setString(2, phone);
        pstmt.setString(3, category);
        pstmt.setInt(4, id);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public void deleteContact(int id) {
    String sql = "DELETE FROM contacts WHERE id = ?";
    try (Connection conn = DatabaseHelper.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}

