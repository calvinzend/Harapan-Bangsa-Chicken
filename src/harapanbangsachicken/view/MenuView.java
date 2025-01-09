package harapanbangsachicken.view;

import javax.swing.*;

import harapanbangsachicken.controller.Login;
import harapanbangsachicken.model.classes.SingletonManager;
import harapanbangsachicken.model.classes.UpdateKeranjang;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JFrame {
    private JButton keranjang, checkout, showMenu, checkProfil, reward, checkTransaksi, saldo, level, lastTransaksi ,logout;
    private JLabel logoLabel;
    private JPanel pesan;

    public MenuView() {
        super("Menu");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));

        pesan = new JPanel(new GridBagLayout());
        pesan.setBackground(Color.RED);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        ImageIcon logoIcon = new ImageIcon("src/harapanbangsachicken/view/gambar/mcd.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        logoLabel = new JLabel(logoIcon, SwingConstants.CENTER);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5; 
        gbc.anchor = GridBagConstraints.CENTER;
        pesan.add(logoLabel, gbc);

        JLabel judulAtas = new JLabel("SELAMAT DATANG "+ SingletonManager.getInstance().getUser().getNamaDepan().toUpperCase(), SwingConstants.CENTER);
        judulAtas.setFont(new Font("Arial", Font.PLAIN, 28));
        judulAtas.setForeground(Color.YELLOW);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 5; 
        pesan.add(judulAtas, gbc);
        
        JLabel judulBawah = new JLabel("Silakan Pilih Opsi Anda", SwingConstants.CENTER);
        judulBawah.setFont(new Font("Arial", Font.PLAIN, 20));
        judulBawah.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 5; 
        pesan.add(judulBawah, gbc);

        showMenu = new JButton("Show Menu");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        showMenu.setFont(new Font("Arial", Font.PLAIN, 16));
        showMenu.setBackground(Color.RED);
        showMenu.setForeground(Color.YELLOW);

        pesan.add(showMenu, gbc);

        keranjang = new JButton("Keranjang");
        gbc.gridx = 1;
        gbc.gridy = 3;
        pesan.add(keranjang, gbc);
        keranjang.setFont(new Font("Arial", Font.PLAIN, 16));
        keranjang.setBackground(Color.RED);
        keranjang.setForeground(Color.YELLOW);
        
        checkout = new JButton("Checkout");
        gbc.gridx = 2;
        gbc.gridy = 3;
        pesan.add(checkout, gbc);
        checkout.setFont(new Font("Arial", Font.PLAIN, 16));
        checkout.setBackground(Color.RED);
        checkout.setForeground(Color.YELLOW);

        checkProfil = new JButton("Check Profil");
        gbc.gridx = 3;
        gbc.gridy = 3;
        pesan.add(checkProfil, gbc);
        checkProfil.setFont(new Font("Arial", Font.PLAIN, 16));
        checkProfil.setBackground(Color.RED);
        checkProfil.setForeground(Color.YELLOW);

        reward = new JButton("Reward");
        gbc.gridx = 4;
        gbc.gridy = 3;
        pesan.add(reward, gbc);
        reward.setFont(new Font("Arial", Font.PLAIN, 16));
        reward.setBackground(Color.RED);
        reward.setForeground(Color.YELLOW);

        checkTransaksi = new JButton("Check Transaksi");
        gbc.gridx = 0;
        gbc.gridy = 4;
        pesan.add(checkTransaksi, gbc);
        checkTransaksi.setFont(new Font("Arial", Font.PLAIN, 16));
        checkTransaksi.setBackground(Color.RED);
        checkTransaksi.setForeground(Color.YELLOW);

        saldo = new JButton("Saldo");
        gbc.gridx = 1;
        gbc.gridy = 4;
        pesan.add(saldo, gbc);
        saldo.setFont(new Font("Arial", Font.PLAIN, 16));
        saldo.setBackground(Color.RED);
        saldo.setForeground(Color.YELLOW);

        level = new JButton("Level");
        gbc.gridx = 2;
        gbc.gridy = 4;
        pesan.add(level, gbc);
        level.setFont(new Font("Arial", Font.PLAIN, 16));
        level.setBackground(Color.RED);
        level.setForeground(Color.YELLOW);

        lastTransaksi = new JButton("Last Transaksi");
        gbc.gridx = 3;
        gbc.gridy = 4;
        pesan.add(lastTransaksi, gbc);
        lastTransaksi.setFont(new Font("Arial", Font.PLAIN, 16));
        lastTransaksi.setBackground(Color.RED);
        lastTransaksi.setForeground(Color.YELLOW);

        logout = new JButton("Log out");
        gbc.gridx = 4;
        gbc.gridy = 4;
        pesan.add(logout, gbc);
        logout.setFont(new Font("Arial", Font.PLAIN, 16));
        logout.setBackground(Color.RED);
        logout.setForeground(Color.YELLOW);

        

        add(pesan);

        checkProfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println(SingletonManager.getInstance().getUser().showProfile());
                dispose();
                new ProfileView();
            }
        });

        showMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new ShowMenuView();
            }
        });

        checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new Checkout();
            }
        });

        keranjang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new ShowKeranjang();
            }

        });
 
        reward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new RewardMenu();
            }

        });
 
        checkTransaksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new TransaksiView();
                dispose();
            }

        });

        saldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new SaldoView();
                dispose();
            }

        });

        level.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new LevelMenu();
                dispose();
            }

        });

        lastTransaksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new InfoPembayaran();
                dispose();
            }

        });
        
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                SingletonManager.getInstance().clearUser();
                UpdateKeranjang.getInstance().clearKeranjang();
                dispose();
                new Login(new LoginView());
            }

        });
        

        setVisible(true);
    }

    public static void main(String[] args) {
        new MenuView(); 
    }
}
