package harapanbangsachicken.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame{
    private JPanel frame; 
    private JPanel input; 
    private JLabel email, password;
    private  JTextField userValue;
    private JPasswordField passwordValue;
    private JButton login, register;
    

    public LoginView() {
        super("Login");
        setSize(800, 700);
        setLocationRelativeTo(null);

        frame = new JPanel(new GridBagLayout());
        input = new JPanel(new GridLayout(3,2));
        email = new JLabel("Email:");
        userValue = new JTextField(10);
        password = new JLabel("Password:");
        passwordValue = new JPasswordField(10);
        login = new JButton("LOGIN");
        register = new JButton("REGISTER");
  
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
                System.out.println("Email : "+ email + "\nBerhasil login");
            }
        });

    }

    public String getEmail(){
        return userValue.getText();
    }

    public String getPassword(){
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
