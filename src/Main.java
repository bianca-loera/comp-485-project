import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // Ensure DB table exists
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:network_profiles.db")) {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS profiles (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "network_name TEXT," +
                "ip_address TEXT," +
                "subnet_mask TEXT," +
                "gateway TEXT," +
                "dns TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Launch app
        new MainMenu();
    }
}
