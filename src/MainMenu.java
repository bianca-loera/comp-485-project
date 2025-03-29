import java.awt.*;
import javax.swing.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Network Migration - Main Menu");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(207, 10, 44)); // CSUN Red

        JLabel title = new JLabel("Network Migration");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setBounds(75, 20, 250, 30);
        add(title);

        JButton addBtn = new JButton("Add Profile");
        addBtn.setBounds(90, 70, 150, 30);
        addBtn.setBackground(Color.WHITE);
        addBtn.setForeground(new Color(207, 10, 44));
        add(addBtn);

        JButton switchBtn = new JButton("Switch Profile");
        switchBtn.setBounds(90, 120, 150, 30);
        switchBtn.setBackground(Color.WHITE);
        switchBtn.setForeground(new Color(207, 10, 44));
        add(switchBtn);

        addBtn.addActionListener(e -> {
            dispose();
            new AddProfileForm();
        });

        switchBtn.addActionListener(e -> {
            dispose();
            new SwitchProfileForm();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}

