package harapanbangsachicken.view;

import javax.swing.*;

import harapanbangsachicken.model.classes.Menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowMenuView extends JFrame {
    private JButton addToKeranjang, menuMakanan, menuMinuman, menuPaket, keranjang;
    private JPanel pesan;

    public ShowMenuView() {
        super("Menu");
        setSize(800, 700);
        setLocationRelativeTo(null);

        pesan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel judulAtas = new JLabel("Pilihan Menu", SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        pesan.add(judulAtas, gbc);

        // Tombol Show Menu
        menuMakanan = new JButton("Makanan");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        pesan.add(menuMakanan, gbc);

        menuMinuman = new JButton("Minuman");
        gbc.gridx = 1;
        gbc.gridy = 1;
        pesan.add(menuMinuman, gbc);

        menuPaket = new JButton("Paket");
        gbc.gridx = 2;
        gbc.gridy = 1;
        pesan.add(menuPaket, gbc);

        JLabel judulBawah = new JLabel("Silakan Pilih Opsi Anda", SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 5;
        pesan.add(judulBawah, gbc);

        add(pesan);

        menuMakanan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Menu> show = Menu.getData("Food");
  
                for (Menu menu : show) {
                    System.out.println(menu.toString() +"\n");
                }
                System.out.println();
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

        setVisible(true);
    }
}
