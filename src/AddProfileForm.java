import javax.swing.*;

public class AddProfileForm extends JFrame {
    public AddProfileForm() {
        setTitle("Add Network Profile");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Absolute positioning (for now)

        JLabel label = new JLabel("This is a test form!");
        label.setBounds(120, 100, 200, 30);
        add(label);

        setVisible(true);
    }

    public static void main(String[] args) {
        new AddProfileForm();
    }
}
