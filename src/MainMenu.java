import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Network Migration - Main Menu");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(207, 10, 44)); // CSUN Red
        setIconImage(new ImageIcon("csun-logo.png").getImage()); // Optional icon

        JLabel title = new JLabel("Network Migration");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setBounds(75, 20, 250, 30);
        add(title);

        JButton addBtn = new JButton("Add Profile");
        addBtn.setBounds(90, 70, 150, 30);
        styleButton(addBtn, true);
        add(addBtn);

        JButton switchBtn = new JButton("Switch Profile");
        switchBtn.setBounds(90, 120, 150, 30);
        styleButton(switchBtn, true);
        add(switchBtn);

        addBtn.addActionListener(e -> {
            dispose();
            new AddProfileForm();
        });

        switchBtn.addActionListener(e -> {
            dispose();
            new SwitchProfileForm();
        });

        // Keyboard shortcuts
        getRootPane().registerKeyboardAction(e -> addBtn.doClick(),
            KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK),
            JComponent.WHEN_IN_FOCUSED_WINDOW);

        getRootPane().registerKeyboardAction(e -> switchBtn.doClick(),
            KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK),
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

    public static void main(String[] args) {
        new MainMenu();
    }
}
