package harapanbangsachicken.view;

import javax.swing.*;
import harapanbangsachicken.model.classes.Menu;
import harapanbangsachicken.model.classes.Paket;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowMenuView extends JFrame {
    private JButton menuMakanan, menuMinuman, menuPaket, keranjang, back;
    private JLabel logoLabel;
    private JPanel pesan;

    public ShowMenuView() {
        super("Menu");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        JLabel judulAtas = new JLabel("Welcome to the Order Menu", SwingConstants.CENTER);
        judulAtas.setFont(new Font("Arial", Font.PLAIN, 28));
        judulAtas.setFont(new Font("Arial", Font.PLAIN, 28));
        judulAtas.setForeground(Color.YELLOW);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 5;
        pesan.add(judulAtas, gbc);

        JLabel judulBawah = new JLabel("Choose Your Option", SwingConstants.CENTER);
        judulBawah.setFont(new Font("Arial", Font.PLAIN, 18));
        judulBawah.setFont(new Font("Arial", Font.PLAIN, 20));
        judulBawah.setForeground(Color.YELLOW);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 5;
        pesan.add(judulBawah, gbc);

        menuMakanan = new JButton("Food");
        menuMakanan.setFont(new Font("Arial", Font.PLAIN, 16));
        menuMakanan.setBackground(Color.RED);
        menuMakanan.setForeground(Color.YELLOW);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        pesan.add(menuMakanan, gbc);

        menuMinuman = new JButton("Drink");
        menuMinuman.setFont(new Font("Arial", Font.PLAIN, 16));
        menuMinuman.setBackground(Color.RED);
        menuMinuman.setForeground(Color.YELLOW);
        gbc.gridx = 1;
        gbc.gridy = 3;
        pesan.add(menuMinuman, gbc);

        menuPaket = new JButton("Package");
        menuPaket.setFont(new Font("Arial", Font.PLAIN, 16));
        menuPaket.setBackground(Color.RED);
        menuPaket.setForeground(Color.YELLOW);
        gbc.gridx = 2;
        gbc.gridy = 3;
        pesan.add(menuPaket, gbc);

        keranjang = new JButton("Check Basket");
        keranjang.setFont(new Font("Arial", Font.PLAIN, 16));
        keranjang.setBackground(Color.RED);
        keranjang.setForeground(Color.YELLOW);
        gbc.gridx = 3;
        gbc.gridy = 3;
        pesan.add(keranjang, gbc);

        back= new JButton("Back");
        back.setFont(new Font("Arial", Font.PLAIN, 16));
        back.setBackground(Color.RED);
        back.setForeground(Color.YELLOW);
        gbc.gridx = 4;
        gbc.gridy = 3;
        pesan.add(back, gbc);

        add(pesan);

        menuMakanan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Menu> show = Menu.getData("Food");
                new ShowDaftarMenu(show);
                dispose();
            }
        });

        menuMinuman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Menu> show = Menu.getData("Drink");
                new ShowDaftarMenu(show);
                dispose();
            }
        });

        menuPaket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Paket> show = Paket.getData();
                new ShowDaftarPaket(show);
                dispose();                
            }
        });

        keranjang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowKeranjang();
                dispose();
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuView();
                dispose();
            }
        });


        setVisible(true);
    }
}