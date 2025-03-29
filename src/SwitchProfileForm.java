import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class SwitchProfileForm extends JFrame {
    JComboBox<String> profileDropdown = new JComboBox<>();
    JTextArea output = new JTextArea();

    public SwitchProfileForm() {
        setTitle("Switch Profile");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(207, 10, 44)); // CSUN Red

        JLabel title = new JLabel("Switch Network Profile");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(Color.WHITE);
        title.setBounds(100, 10, 250, 30);
        add(title);

        JLabel selectLbl = new JLabel("Select a Profile:");
        selectLbl.setForeground(Color.WHITE);
        selectLbl.setBounds(30, 50, 120, 20);
        add(selectLbl);

        profileDropdown.setBounds(150, 50, 200, 20);
        add(profileDropdown);

        JButton switchBtn = new JButton("Apply");
        switchBtn.setBounds(150, 90, 100, 30);
        switchBtn.setBackground(Color.WHITE);
        switchBtn.setForeground(new Color(207, 10, 44));
        add(switchBtn);

        output.setBounds(30, 140, 320, 100);
        output.setEditable(false);
        output.setBackground(Color.WHITE);
        output.setForeground(Color.BLACK);
        add(output);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(30, 260, 100, 30);
        backBtn.setBackground(Color.WHITE);
        backBtn.setForeground(new Color(207, 10, 44));
        add(backBtn);

        switchBtn.addActionListener(e -> loadProfile());
        backBtn.addActionListener(e -> {
            new MainMenu();
            dispose();
        });

        loadProfiles();
        setVisible(true);
    }

    private void loadProfiles() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:network_profiles.db");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT network_name FROM profiles")) {

            while (rs.next()) {
                profileDropdown.addItem(rs.getString("network_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadProfile() {
        String selected = (String) profileDropdown.getSelectedItem();
        String sql = "SELECT * FROM profiles WHERE network_name = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:network_profiles.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, selected);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                output.setText("Switched to Profile:\n" +
                    "Network: " + rs.getString("network_name") + "\n" +
                    "IP: " + rs.getString("ip_address") + "\n" +
                    "Subnet: " + rs.getString("subnet_mask") + "\n" +
                    "Gateway: " + rs.getString("gateway") + "\n" +
                    "DNS: " + rs.getString("dns"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

