package harapanbangsachicken.view;

import javax.swing.*;

import harapanbangsachicken.model.classes.SingletonManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JFrame {
    private JButton keranjang, checkout, showMenu, checkProfil, reward, checkTransaksi,logout;
    private JPanel pesan;

    public MenuView() {
        super("Menu");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));

        pesan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel judulAtas = new JLabel("SELAMAT DATANG "+ SingletonManager.getInstance().getUser().getNamaDepan().toUpperCase(), SwingConstants.CENTER);
        judulAtas.setFont(new Font("Arial", Font.PLAIN, 28));

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5; 
        pesan.add(judulAtas, gbc);
        
        JLabel judulBawah = new JLabel("Silakan Pilih Opsi Anda", SwingConstants.CENTER);
        judulBawah.setFont(new Font("Arial", Font.PLAIN, 20));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 5; 
        pesan.add(judulBawah, gbc);

        showMenu = new JButton("Show Menu");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        pesan.add(showMenu, gbc);

        checkout = new JButton("Checkout");
        gbc.gridx = 1;
        gbc.gridy = 2;
        pesan.add(checkout, gbc);

        keranjang = new JButton("Keranjang");
        gbc.gridx = 2;
        gbc.gridy = 2;
        pesan.add(keranjang, gbc);

        checkProfil = new JButton("Check Profil");
        gbc.gridx = 3;
        gbc.gridy = 2;
        pesan.add(checkProfil, gbc);

        reward = new JButton("Reward");
        gbc.gridx = 4;
        gbc.gridy = 2;
        pesan.add(reward, gbc);

        checkTransaksi = new JButton("Check Transaksi");
        gbc.gridx = 0;
        gbc.gridy = 3;
        pesan.add(checkTransaksi, gbc);

        logout = new JButton("Log out");
        gbc.gridx = 1;
        gbc.gridy = 3;
        pesan.add(logout, gbc);

        

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
        

        setVisible(true);
    }

    public static void main(String[] args) {
        new MenuView(); 
    }
}
