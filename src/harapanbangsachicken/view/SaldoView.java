package harapanbangsachicken.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

import harapanbangsachicken.model.classes.Customer;
import harapanbangsachicken.model.classes.SingletonManager;

public class SaldoView extends JFrame {
    private JPanel frame;
    private JButton backButton, updateSaldoButton, addSaldoButton, viewSaldoButton;
    private JTextField saldoField;

    public SaldoView() {
        super("Balance Management");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a frame with GridBagLayout
        frame = new JPanel();
        frame.setLayout(new GridBagLayout());
        frame.setBackground(Color.RED);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // McDonald's logo image
        ImageIcon mcImageIcon = new ImageIcon("src/harapanbangsachicken/view/gambar/mcd.png"); 
        Image scaledImage = mcImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        mcImageIcon = new ImageIcon(scaledImage);
        JLabel mcImageLabel = new JLabel(mcImageIcon);
        mcImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(mcImageLabel, gbc);

        JLabel titleLabel = new JLabel("Balance Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.YELLOW);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        frame.add(titleLabel, gbc);


        JPanel saldoPanel = new JPanel();
        saldoPanel.setBackground(Color.RED);
        saldoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  

        saldoField = new JTextField(10);
        saldoField.setFont(new Font("Arial", Font.PLAIN, 16));
        JLabel textSaldo =new JLabel("Balance: ");
        textSaldo.setFont(new Font("Arial", Font.PLAIN, 20));
        textSaldo.setForeground(Color.YELLOW);
        saldoPanel.add(textSaldo);
        saldoPanel.add(saldoField);

        viewSaldoButton = new JButton("View Balance");
        viewSaldoButton.setFont(new Font("Arial", Font.BOLD, 18));
        viewSaldoButton.setBackground(Color.RED);
        viewSaldoButton.setForeground(Color.YELLOW);
        saldoPanel.add(viewSaldoButton);

        addSaldoButton = new JButton("Add Balance");
        addSaldoButton.setFont(new Font("Arial", Font.BOLD, 18));
        addSaldoButton.setBackground(Color.RED);
        addSaldoButton.setForeground(Color.YELLOW);
        saldoPanel.add(addSaldoButton);

        updateSaldoButton = new JButton("Update Balance");    
        updateSaldoButton.setFont(new Font("Arial", Font.BOLD, 18));
        updateSaldoButton.setBackground(Color.RED);
        updateSaldoButton.setForeground(Color.YELLOW);
        saldoPanel.add(updateSaldoButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        frame.add(saldoPanel, gbc);


        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.YELLOW);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuView();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.RED);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center the Back button
        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        frame.add(buttonPanel, gbc);

        Customer customer = (Customer) SingletonManager.getInstance().getUser();

        // Action listeners for saldo buttons
        viewSaldoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double saldo = customer.getSaldo();
                NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
                saldoField.setText("Rp " + numberFormat.format(saldo));
            }
        });

        addSaldoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(saldoField.getText());
                    if (amount > 0) {
                        customer.addSaldo(amount);
                        JOptionPane.showMessageDialog(null, "Saldo berhasil ditambahkan.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Masukkan jumlah saldo yang valid.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Masukkan jumlah saldo yang valid.");
                }
            }
        });

        updateSaldoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(saldoField.getText());
                    if (amount >= 0) {
                        customer.updateSaldo(amount);
                        JOptionPane.showMessageDialog(null, "Saldo berhasil diperbarui.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Masukkan jumlah saldo yang valid.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Masukkan jumlah saldo yang valid.");
                }
            }
        });

        add(frame);
        setVisible(true);
    }
}
