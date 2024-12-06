package harapanbangsachicken.view;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import harapanbangsachicken.controller.Login;

public class RegisterView extends JFrame {
    private JPanel frame, input;
    private JLabel logoLabel, namaDepan, namaBelakang, email, password, noTelp, alamat, gender, saldoAwal;
    private JTextField namaDepanValue, namaBelakangValue, emailValue, noTelpValue, alamatValue, saldoValue;
    private JRadioButton genderMaleValue, genderFemaleValue;
    private JPasswordField passwordValue;
    private JButton loginButton, registerButton;
    private Border roundedBorder = BorderFactory.createLineBorder(Color.YELLOW, 2, true);
    private Border roundedBorderButton = BorderFactory.createLineBorder(Color.BLACK, 2, true);

    public RegisterView() {
        super("Login & Register");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));

        frame = new JPanel(new GridBagLayout());
        frame.setBackground(Color.RED);

        // untuk label dan input
        input = new JPanel(new GridLayout(11, 2, 15, 10));
        input.setOpaque(false);

        // set logo MCD
        ImageIcon logoIcon = new ImageIcon("src/harapanbangsachicken/view/gambar/mcd.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        logoLabel = new JLabel(logoIcon);

        // label nama depan
        input.add(namaDepan = new JLabel("Nama Depan     :"));
        namaDepan.setFont(new Font("Arial", Font.PLAIN, 16));
        namaDepan.setForeground(Color.YELLOW);

        // input nama depan
        namaDepanValue = new JTextField(10);
        namaDepanValue.setBorder(roundedBorder);
        namaDepanValue.setPreferredSize(new Dimension(300, 40));
        namaDepanValue.setFont(new Font("Arial", Font.PLAIN, 16));
        namaDepanValue.setBackground(Color.WHITE);
        namaDepanValue.setForeground(Color.BLACK);
        input.add(namaDepanValue);

        // label nama belakang
        input.add(namaBelakang = new JLabel("Nama Belakang :"));
        namaBelakang.setFont(new Font("Arial", Font.PLAIN, 16));
        namaBelakang.setForeground(Color.YELLOW);

        // input nama belakang
        namaBelakangValue = new JTextField(10);
        namaBelakangValue.setBorder(roundedBorder);
        namaBelakangValue.setPreferredSize(new Dimension(300, 40));
        namaBelakangValue.setFont(new Font("Arial", Font.PLAIN, 16));
        namaBelakangValue.setBackground(Color.WHITE);
        namaBelakangValue.setForeground(Color.BLACK);
        input.add(namaBelakangValue);

        // label email
        input.add(email = new JLabel("Email                  :"));
        email.setFont(new Font("Arial", Font.PLAIN, 16));
        email.setForeground(Color.YELLOW);

        // input email
        emailValue = new JTextField(10);
        emailValue.setBorder(roundedBorder);
        emailValue.setPreferredSize(new Dimension(300, 40));
        emailValue.setFont(new Font("Arial", Font.PLAIN, 16));
        emailValue.setBackground(Color.WHITE);
        emailValue.setForeground(Color.BLACK);
        input.add(emailValue);

        // label password
        input.add(password = new JLabel("Password          :"));
        password.setFont(new Font("Arial", Font.PLAIN, 16));
        password.setForeground(Color.YELLOW);

        // input password
        passwordValue = new JPasswordField(10);
        passwordValue.setBorder(roundedBorder);
        passwordValue.setPreferredSize(new Dimension(300, 40));
        passwordValue.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordValue.setBackground(Color.WHITE);
        passwordValue.setForeground(Color.BLACK);
        input.add(passwordValue);

        // label nomor telepon
        input.add(noTelp = new JLabel("No. Telepon      :"));
        noTelp.setFont(new Font("Arial", Font.PLAIN, 16));
        noTelp.setForeground(Color.YELLOW);

        // input nomor telepon
        noTelpValue = new JTextField(10);
        noTelpValue.setBorder(roundedBorder);
        noTelpValue.setPreferredSize(new Dimension(300, 40));
        noTelpValue.setFont(new Font("Arial", Font.PLAIN, 16));
        noTelpValue.setBackground(Color.WHITE);
        noTelpValue.setForeground(Color.BLACK);
        input.add(noTelpValue);

        // label alamat
        input.add(alamat = new JLabel("Alamat               :"));
        alamat.setFont(new Font("Arial", Font.PLAIN, 16));
        alamat.setForeground(Color.YELLOW);

        // input alamat
        alamatValue = new JTextField(10);
        alamatValue.setBorder(roundedBorder);
        alamatValue.setPreferredSize(new Dimension(300, 40));
        alamatValue.setFont(new Font("Arial", Font.PLAIN, 16));
        alamatValue.setBackground(Color.WHITE);
        alamatValue.setForeground(Color.BLACK);
        input.add(alamatValue);

        // label gender
        input.add(gender = new JLabel("Gender              :"));
        gender.setFont(new Font("Arial", Font.PLAIN, 16));
        gender.setForeground(Color.YELLOW);

        // input gender
        genderMaleValue = new JRadioButton("Pria");
        genderMaleValue.setBorderPainted(false);
        genderMaleValue.setContentAreaFilled(false);
        genderMaleValue.setFocusPainted(false);
        genderMaleValue.setPreferredSize(new Dimension(300, 40));
        genderMaleValue.setFont(new Font("Arial", Font.PLAIN, 16));
        genderMaleValue.setBackground(Color.RED);
        genderMaleValue.setForeground(Color.YELLOW);

        genderFemaleValue = new JRadioButton("Wanita");
        genderFemaleValue.setBorderPainted(false);
        genderFemaleValue.setContentAreaFilled(false);
        genderFemaleValue.setFocusPainted(false);
        genderFemaleValue.setPreferredSize(new Dimension(300, 40));
        genderFemaleValue.setFont(new Font("Arial", Font.PLAIN, 16));
        genderFemaleValue.setBackground(Color.RED);
        genderFemaleValue.setForeground(Color.YELLOW);

        input.add(genderMaleValue);
        input.add(Box.createHorizontalStrut(10));
        input.add(genderFemaleValue);

        genderMaleValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (genderMaleValue.isSelected()) {
                    genderFemaleValue.setEnabled(false);
                } else {
                    genderFemaleValue.setEnabled(true);
                }
            }
        });

        genderFemaleValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (genderFemaleValue.isSelected()) {
                    genderMaleValue.setEnabled(false);
                } else {
                    genderMaleValue.setEnabled(true);
                }
            }
        });

        // label saldo
        input.add(saldoAwal = new JLabel("Saldo Awal        :"));
        saldoAwal.setFont(new Font("Arial", Font.PLAIN, 16));
        saldoAwal.setForeground(Color.YELLOW);

        // input saldo
        saldoValue = new JTextField(10);
        saldoValue.setBorder(roundedBorder);
        saldoValue.setPreferredSize(new Dimension(300, 40));
        saldoValue.setFont(new Font("Arial", Font.PLAIN, 16));
        saldoValue.setBackground(Color.WHITE);
        saldoValue.setForeground(Color.BLACK);
        input.add(saldoValue);

        // login button
        loginButton = new JButton("LOGIN");
        loginButton.setBorder(roundedBorderButton);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(Color.RED);
        loginButton.setForeground(Color.YELLOW);

        // register button
        registerButton = new JButton("REGISTER");
        registerButton.setBorder(roundedBorderButton);
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBackground(Color.RED);
        registerButton.setForeground(Color.YELLOW);

        input.add(loginButton);
        input.add(Box.createVerticalStrut(10));
        input.add(registerButton);

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

    public JRadioButton getGenderMaleValue() {
        return genderMaleValue;
    }

    public void setGenderMaleValue(JRadioButton genderMaleValue) {
        this.genderMaleValue = genderMaleValue;
    }

    public JRadioButton getGenderFemaleValue() {
        return genderFemaleValue;
    }

    public void setGenderFemaleValue(JRadioButton genderFemaleValue) {
        this.genderFemaleValue = genderFemaleValue;
    }

    public String getSaldo() {
        return saldoValue.getText();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        new RegisterView();
    }
}
