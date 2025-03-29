import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class AddProfileForm extends JFrame {
    JTextField nameField, ipField, subnetField, gatewayField, dnsField;
    JButton saveBtn, backBtn;

    public AddProfileForm() {
        setTitle("Add Network Profile");
        setSize(400, 370);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null); // Center on screen
        getContentPane().setBackground(new Color(207, 10, 44));
        setIconImage(new ImageIcon("csun-logo.png").getImage()); // Optional icon

        JLabel title = new JLabel("Add Profile");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setBounds(130, 10, 200, 30);
        add(title);

        Font labelFont = new Font("Arial", Font.PLAIN, 14);

        JLabel nameLbl = new JLabel("Network Name:");
        nameLbl.setForeground(Color.WHITE);
        nameLbl.setFont(labelFont);
        nameLbl.setBounds(30, 50, 120, 20);
        add(nameLbl);

        nameField = new JTextField();
        nameField.setBounds(150, 50, 200, 20);
        add(nameField);

        JLabel ipLbl = new JLabel("IP Address:");
        ipLbl.setForeground(Color.WHITE);
        ipLbl.setFont(labelFont);
        ipLbl.setBounds(30, 80, 120, 20);
        add(ipLbl);

        ipField = new JTextField();
        ipField.setBounds(150, 80, 200, 20);
        add(ipField);

        JLabel subnetLbl = new JLabel("Subnet Mask:");
        subnetLbl.setForeground(Color.WHITE);
        subnetLbl.setFont(labelFont);
        subnetLbl.setBounds(30, 110, 120, 20);
        add(subnetLbl);

        subnetField = new JTextField();
        subnetField.setBounds(150, 110, 200, 20);
        add(subnetField);

        JLabel gatewayLbl = new JLabel("Gateway:");
        gatewayLbl.setForeground(Color.WHITE);
        gatewayLbl.setFont(labelFont);
        gatewayLbl.setBounds(30, 140, 120, 20);
        add(gatewayLbl);

        gatewayField = new JTextField();
        gatewayField.setBounds(150, 140, 200, 20);
        add(gatewayField);

        JLabel dnsLbl = new JLabel("DNS:");
        dnsLbl.setForeground(Color.WHITE);
        dnsLbl.setFont(labelFont);
        dnsLbl.setBounds(30, 170, 120, 20);
        add(dnsLbl);

        dnsField = new JTextField();
        dnsField.setBounds(150, 170, 200, 20);
        add(dnsField);

        saveBtn = new JButton("Save");
        saveBtn.setBounds(150, 220, 90, 30);
        styleButton(saveBtn, true);
        add(saveBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(260, 220, 90, 30);
        styleButton(backBtn, false);
        add(backBtn);

        saveBtn.addActionListener(e -> saveProfile());
        backBtn.addActionListener(e -> {
            new MainMenu();
            dispose();
        });

        getRootPane().setDefaultButton(saveBtn);
        getRootPane().registerKeyboardAction(e -> backBtn.doClick(),
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_IN_FOCUSED_WINDOW);

        setVisible(true);
    }

    private void styleButton(JButton btn, boolean isWhite) {
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        btn.setContentAreaFilled(true);
        btn.setOpaque(true);
        btn.setBackground(isWhite ? Color.WHITE : new Color(207, 10, 44));
        btn.setForeground(isWhite ? new Color(207, 10, 44) : Color.WHITE);

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(255, 255, 255, 230));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(isWhite ? Color.WHITE : new Color(207, 10, 44));
            }
        });
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
            JOptionPane.showMessageDialog(this, "🎉 Profile saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            new MainMenu();
            dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "❌ Error saving profile.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
