package harapanbangsachicken.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import harapanbangsachicken.controller.Login;

public class RegisterView extends JFrame {
    private JTextField namaDepanValue, namaBelakangValue, emailValue, noTelpValue, alamatValue ,genderValue, saldoValue;
    private JPasswordField passwordValue;
    private JButton loginButton, registerButton;

    public RegisterView() {
        super("Login & Register");

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Nama Depan:"));
        namaDepanValue = new JTextField(10);
        inputPanel.add(namaDepanValue);

        inputPanel.add(new JLabel("Nama Belakang:"));
        namaBelakangValue = new JTextField(10);
        inputPanel.add(namaBelakangValue);

        inputPanel.add(new JLabel("Email:"));
        emailValue = new JTextField(10);
        inputPanel.add(emailValue);

        inputPanel.add(new JLabel("Password:"));
        passwordValue = new JPasswordField(10);
        inputPanel.add(passwordValue);

        inputPanel.add(new JLabel("No. Telepon:"));
        noTelpValue = new JTextField(10);
        inputPanel.add(noTelpValue);

        inputPanel.add(new JLabel("Alamat:"));
        alamatValue = new JTextField(10);
        inputPanel.add(alamatValue);

        inputPanel.add(new JLabel("Gender:"));
        genderValue = new JTextField(10);
        inputPanel.add(genderValue);

        inputPanel.add(new JLabel("Saldo Awal:"));
        saldoValue = new JTextField(10);
        inputPanel.add(saldoValue);

        loginButton = new JButton("LOGIN");
        registerButton = new JButton("REGISTER");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            dispose();
              new Login(new LoginView()); 
            }
        });
    }

    public String getUsername() {
        return emailValue.getText();
    }

    public String getPassword() {
        return new String(passwordValue.getPassword());
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public String getNamaDepan() {
        return namaDepanValue.getText();
    }

    public String getNamaBelakang() {
        return namaBelakangValue.getText();
    }

    public String getEmail() {
        return emailValue.getText();
    }

    public String getNoTelp() {
        return noTelpValue.getText();
    }
    public String getAlamat() {
        return alamatValue.getText();
    }
    public String getGender() {
        return genderValue.getText();
    }
    public String getSaldo() {
        return saldoValue.getText();
    }


    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
