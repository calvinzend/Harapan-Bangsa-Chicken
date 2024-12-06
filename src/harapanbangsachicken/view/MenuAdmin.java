package harapanbangsachicken.view;

import java.awt.*;

import javax.swing.*;

public class MenuAdmin extends JFrame{
    private JButton viewTransaksi, viewTotalPendapatan, menu, resep, paket;
    private JPanel panel;
    private JLabel logoLabel;

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

        
        ImageIcon logoIcon = new ImageIcon("src/harapanbangsachicken/view/gambar/mcd.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        logoLabel = new JLabel(logoIcon);

        JLabel judulAtas = new JLabel("Pilihan Menu", SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5; 
        panel.add(judulAtas, gbc);

        viewTotalPendapatan = new JButton("Total Pendapatan");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(viewTotalPendapatan, gbc);

        viewTransaksi= new JButton("Checkout");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(viewTransaksi, gbc);

        menu = new JButton("Menu");
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(menu, gbc);

        resep = new JButton("Resep");
        gbc.gridx = 3;
        gbc.gridy = 1;
        panel.add(resep, gbc);

        paket = new JButton("Paket");
        gbc.gridx = 4;
        gbc.gridy = 1;
        panel.add(paket, gbc);

        JLabel judulBawah = new JLabel("Silakan Pilih Opsi Anda", SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 5; 
        panel.add(judulBawah, gbc);

        add(panel);

        setVisible(true);
    }
}
