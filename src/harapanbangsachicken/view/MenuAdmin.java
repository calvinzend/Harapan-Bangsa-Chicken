package harapanbangsachicken.view;

import java.awt.*;

import javax.swing.*;

import harapanbangsachicken.controller.Login;
import harapanbangsachicken.model.classes.SingletonManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAdmin extends JFrame{
    private JButton viewTransaksi, viewTotalPendapatan, menu, resep, paket, logout;
    private JLabel logoLabel;
    private JPanel panel;

    public MenuAdmin(){
        super("Menu");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));

        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.RED);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // show image icon
        ImageIcon logoIcon = new ImageIcon("src/harapanbangsachicken/view/gambar/mcd.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        logoLabel = new JLabel(logoIcon, SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5; 
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(logoLabel, gbc);
        
        // Header
        JLabel judulAtas = new JLabel("SELAMAT DATANG ADMIN " + SingletonManager.getInstance().getUser().getNamaDepan().toUpperCase(), SwingConstants.CENTER);
        judulAtas.setFont(new Font("Arial", Font.PLAIN, 28));
        judulAtas.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 5; 
        panel.add(judulAtas, gbc);

        // Header 2
        JLabel judulBawah = new JLabel("Silakan Pilih Opsi Anda", SwingConstants.CENTER);
        judulBawah.setFont(new Font("Arial", Font.PLAIN, 20));
        judulBawah.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 5; 
        panel.add(judulBawah, gbc);

        // Menu 1 : Show Total Pendapatan
        viewTotalPendapatan = new JButton("Total Pendapatan");
        viewTotalPendapatan.setFont(new Font("Arial", Font.PLAIN, 16));
        viewTotalPendapatan.setBackground(Color.RED);
        viewTotalPendapatan.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        panel.add(viewTotalPendapatan, gbc);

        // Menu 2 : Show Transaksi
        viewTransaksi= new JButton("Lihat Transaksi");
        viewTransaksi.setFont(new Font("Arial", Font.PLAIN, 16));
        viewTransaksi.setBackground(Color.RED);
        viewTransaksi.setForeground(Color.YELLOW);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(viewTransaksi, gbc);

        // Menu 3 : Edit List Menu
        menu = new JButton("Edit Menu");
        menu.setFont(new Font("Arial", Font.PLAIN, 16));
        menu.setBackground(Color.RED);
        menu.setForeground(Color.YELLOW);

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(menu, gbc);

        // Menu 4 : Edit List Resep
        resep = new JButton("Edit Resep");
        resep.setFont(new Font("Arial", Font.PLAIN, 16));
        resep.setBackground(Color.RED);
        resep.setForeground(Color.YELLOW);

        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(resep, gbc);

        // Menu 5 : Edit List Paket
        paket = new JButton("Edit Paket");
        paket.setFont(new Font("Arial", Font.PLAIN, 16));
        paket.setBackground(Color.RED);
        paket.setForeground(Color.YELLOW);

        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(paket, gbc);

        // Log out Button
        logout = new JButton("Log out");
        logout.setFont(new Font("Arial", Font.PLAIN, 16));
        logout.setBackground(Color.RED);
        logout.setForeground(Color.YELLOW);

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(logout, gbc);
        
        
        add(panel);
        
        setVisible(true);
        
        viewTotalPendapatan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new TotalPendapatanView();
            }

        });

        viewTransaksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new TransaksiView();
            }

        });

        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new ListMenuAdminView();
            }

        });

        resep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new ResepView();
            }

        });

        paket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new PaketView();
            }

        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                SingletonManager.getInstance().clearUser();
                dispose();
                new Login(new LoginView());
            }

        });

    }
}
