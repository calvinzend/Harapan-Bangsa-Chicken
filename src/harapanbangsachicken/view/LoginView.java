package harapanbangsachicken.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JPanel frame;
    private JPanel input;
    private JLabel email, password, logoLabel;
    private JTextField userValue;
    private JPasswordField passwordValue;
    private JButton login, register;

    public LoginView() {
        super("Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame = new JPanel(new GridBagLayout());
        frame.setBackground(Color.RED);

        input = new JPanel(new GridLayout(3, 2, 10, 10));
        input.setOpaque(false);

        ImageIcon logoIcon = new ImageIcon("src/harapanbangsachicken/view/gambar/mcd.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        logoLabel = new JLabel(logoIcon);

        email = new JLabel("Email        :");
        email.setFont(new Font("Arial", Font.PLAIN, 16));
        email.setForeground(Color.YELLOW);

        password = new JLabel("Password  :");
        password.setFont(new Font("Arial", Font.PLAIN, 16));
        password.setForeground(Color.YELLOW);

        userValue = new JTextField();
        userValue.setPreferredSize(new Dimension(300, 40));
        userValue.setFont(new Font("Arial", Font.PLAIN, 16));
        userValue.setBackground(Color.RED);
        userValue.setForeground(Color.YELLOW);

        passwordValue = new JPasswordField();
        passwordValue.setPreferredSize(new Dimension(300, 40));
        passwordValue.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordValue.setBackground(Color.RED);
        passwordValue.setForeground(Color.YELLOW);

        login = new JButton("LOGIN");
        login.setFont(new Font("Arial", Font.BOLD, 16));
        login.setBackground(Color.RED);
        login.setForeground(Color.YELLOW);

        register = new JButton("REGISTER");
        register.setFont(new Font("Arial", Font.BOLD, 16));
        register.setBackground(Color.RED);
        register.setForeground(Color.YELLOW);

        input.add(email);
        input.add(userValue);
        input.add(password);
        input.add(passwordValue);
        input.add(login);
        input.add(register);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(logoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1; 
        gbc.gridwidth = 1;  
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(input, gbc);

        add(frame);

        setVisible(true);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = getEmail();
                System.out.println("Email : " + email + "\nBerhasil login");
            }
        });
    }

    public String getEmail() {
        return userValue.getText();
    }

    public String getPassword() {
        return new String(passwordValue.getPassword());
    }

    public JButton getRegisterButton() {
        return register;
    }

    public JButton getLoginButton() {
        return login;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
