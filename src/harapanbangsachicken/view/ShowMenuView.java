package harapanbangsachicken.view;

import javax.swing.*;

import harapanbangsachicken.model.classes.Menu;
import harapanbangsachicken.model.classes.SingletonManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowMenuView extends JFrame {
    private JButton addToKeranjang, menuMakanan, menuMinuman, menuPaket, keranjang, back;
    private JPanel pesan;

    public ShowMenuView() {
        super("Menu");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));

        pesan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel judulAtas = new JLabel("Selamat Datang di Menu Pesanan", SwingConstants.CENTER);
        judulAtas.setFont(new Font("Arial", Font.PLAIN, 28));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        pesan.add(judulAtas, gbc);

        JLabel judulBawah = new JLabel("Silakan Pilih Opsi Anda", SwingConstants.CENTER);
        judulBawah.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 5;
        pesan.add(judulBawah, gbc);

        menuMakanan = new JButton("Makanan");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        pesan.add(menuMakanan, gbc);

        menuMinuman = new JButton("Minuman");
        gbc.gridx = 1;
        gbc.gridy = 2;
        pesan.add(menuMinuman, gbc);

        menuPaket = new JButton("Paket");
        gbc.gridx = 2;
        gbc.gridy = 2;
        pesan.add(menuPaket, gbc);

        back= new JButton("Kembali");
        gbc.gridx = 4;
        gbc.gridy = 2;
        pesan.add(back, gbc);

        add(pesan);

        menuMakanan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Menu> show = Menu.getData("Food");
  
                for (Menu menu : show) {
                    System.out.println(menu.toString() +"\n");
                }
                System.out.println();
                new ShowDaftarMenu(show);
                dispose();
            }
        });

        menuMinuman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Menu> show = Menu.getData("Drink");
  
                for (Menu menu : show) {
                    System.out.println(menu.toString() +"\n");
                }
                System.out.println();
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
    public static void main(String[] args) {
        new ShowMenuView();
    }
}
