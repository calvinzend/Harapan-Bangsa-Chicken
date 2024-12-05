package harapanbangsachicken.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JPanel frame;
    private JPanel input;
    private JLabel email, password;
    private JTextField userValue;
    private JPasswordField passwordValue;
    private JButton login, register;

    public LoginView() {
        super("Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));

        frame = new JPanel(new GridBagLayout());
        input = new JPanel(new GridLayout(3, 2, 10, 10));

        email = new JLabel("Email        :");
        email.setFont(new Font("Arial", Font.PLAIN, 16));
        password = new JLabel("Password  :");
        password.setFont(new Font("Arial", Font.PLAIN, 16));

        userValue = new JTextField();
        userValue.setPreferredSize(new Dimension(300, 40)); 
        userValue.setFont(new Font("Arial", Font.PLAIN, 16)); 

        passwordValue = new JPasswordField();
        passwordValue.setPreferredSize(new Dimension(300, 40));
        passwordValue.setFont(new Font("Arial", Font.PLAIN, 16));

        login = new JButton("LOGIN");
        login.setFont(new Font("Arial", Font.BOLD, 16)); 
        login.setPreferredSize(new Dimension(80, 40));
        register = new JButton("REGISTER");
        register.setFont(new Font("Arial", Font.BOLD, 16));
        register.setPreferredSize(new Dimension(80, 40));

        input.add(email);
        input.add(userValue);
        input.add(password);
        input.add(passwordValue);
        input.add(login);
        input.add(register);

        frame.add(input);
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
