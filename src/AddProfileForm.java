import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class AddProfileForm extends JFrame {
    JTextField nameField, ipField, subnetField, gatewayField, dnsField;

    public AddProfileForm() {
        setTitle("Add Network Profile");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(207, 10, 44)); // CSUN Red

        JLabel title = new JLabel("Add Profile");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setBounds(130, 10, 200, 30);
        add(title);

        JLabel nameLbl = new JLabel("Network Name:");
        nameLbl.setForeground(Color.WHITE);
        nameLbl.setBounds(30, 50, 100, 20);
        add(nameLbl);

        nameField = new JTextField();
        nameField.setBounds(150, 50, 200, 20);
        add(nameField);

        JLabel ipLbl = new JLabel("IP Address:");
        ipLbl.setForeground(Color.WHITE);
        ipLbl.setBounds(30, 80, 100, 20);
        add(ipLbl);

        ipField = new JTextField();
        ipField.setBounds(150, 80, 200, 20);
        add(ipField);

        JLabel subnetLbl = new JLabel("Subnet Mask:");
        subnetLbl.setForeground(Color.WHITE);
        subnetLbl.setBounds(30, 110, 100, 20);
        add(subnetLbl);

        subnetField = new JTextField();
        subnetField.setBounds(150, 110, 200, 20);
        add(subnetField);

        JLabel gatewayLbl = new JLabel("Gateway:");
        gatewayLbl.setForeground(Color.WHITE);
        gatewayLbl.setBounds(30, 140, 100, 20);
        add(gatewayLbl);

        gatewayField = new JTextField();
        gatewayField.setBounds(150, 140, 200, 20);
        add(gatewayField);

        JLabel dnsLbl = new JLabel("DNS:");
        dnsLbl.setForeground(Color.WHITE);
        dnsLbl.setBounds(30, 170, 100, 20);
        add(dnsLbl);

        dnsField = new JTextField();
        dnsField.setBounds(150, 170, 200, 20);
        add(dnsField);

        JButton saveBtn = new JButton("Save");
        saveBtn.setBounds(150, 210, 90, 30);
        saveBtn.setBackground(Color.WHITE);
        saveBtn.setForeground(new Color(207, 10, 44));
        add(saveBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(260, 210, 90, 30);
        backBtn.setBackground(Color.WHITE);
        backBtn.setForeground(new Color(207, 10, 44));
        add(backBtn);

        saveBtn.addActionListener(e -> saveProfile());
        backBtn.addActionListener(e -> {
            new MainMenu();
            dispose();
        });

        setVisible(true);
    }

    private void saveProfile() {
        String sql = "INSERT INTO profiles (network_name, ip_address, subnet_mask, gateway, dns) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:network_profiles.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nameField.getText());
            pstmt.setString(2, ipField.getText());
            pstmt.setString(3, subnetField.getText());
            pstmt.setString(4, gatewayField.getText());
            pstmt.setString(5, dnsField.getText());

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Profile saved!");
            new MainMenu();
            dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saving profile.");
            e.printStackTrace();
        }
    }
}


