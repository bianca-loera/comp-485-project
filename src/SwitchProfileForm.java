import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class SwitchProfileForm extends JFrame {
    JComboBox<String> profileDropdown = new JComboBox<>();
    JTextArea output = new JTextArea();
    JButton switchBtn, backBtn;

    public SwitchProfileForm() {
        setTitle("Switch Profile");
        setSize(400, 370);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(207, 10, 44));
        setIconImage(new ImageIcon("csun-logo.png").getImage()); // Optional icon

        JLabel title = new JLabel("Switch Network Profile");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setBounds(90, 10, 250, 30);
        add(title);

        JLabel selectLbl = new JLabel("Select a Profile:");
        selectLbl.setForeground(Color.WHITE);
        selectLbl.setFont(new Font("Arial", Font.PLAIN, 14));
        selectLbl.setBounds(30, 50, 120, 20);
        add(selectLbl);

        profileDropdown.setBounds(150, 50, 200, 25);
        add(profileDropdown);

        switchBtn = new JButton("Apply");
        switchBtn.setBounds(150, 90, 100, 30);
        styleButton(switchBtn, true);
        add(switchBtn);

        output.setBounds(30, 140, 320, 100);
        output.setEditable(false);
        output.setFont(new Font("Monospaced", Font.PLAIN, 13));
        output.setBackground(Color.WHITE);
        output.setForeground(Color.BLACK);
        add(output);

        backBtn = new JButton("Back");
        backBtn.setBounds(30, 260, 100, 30);
        styleButton(backBtn, false);
        add(backBtn);

        switchBtn.addActionListener(e -> loadProfile());
        backBtn.addActionListener(e -> {
            new MainMenu();
            dispose();
        });

        getRootPane().setDefaultButton(switchBtn);
        getRootPane().registerKeyboardAction(e -> backBtn.doClick(),
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_IN_FOCUSED_WINDOW);

        loadProfiles();
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
                output.setText("💬 Switched to Profile:\n" +
                    "Network: " + rs.getString("network_name") + "\n" +
                    "IP:       " + rs.getString("ip_address") + "\n" +
                    "Subnet:   " + rs.getString("subnet_mask") + "\n" +
                    "Gateway:  " + rs.getString("gateway") + "\n" +
                    "DNS:      " + rs.getString("dns"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading profile.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
