import java.sql.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Step 1: Connect to SQLite DB and create a table
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:network_profiles.db")) {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS profiles (id INTEGER PRIMARY KEY AUTOINCREMENT, network_name TEXT)");
            stmt.execute("INSERT INTO profiles (network_name) VALUES ('Test Network')");
            System.out.println("Database success! Test data inserted.");
        } catch (SQLException e) {
            System.out.println("Database failed.");
            e.printStackTrace();
        }

        // Step 2: Simple Swing GUI
        JFrame frame = new JFrame("Network Migration App");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("Click Me!");
        frame.add(button);
        frame.setVisible(true);
    }
}
