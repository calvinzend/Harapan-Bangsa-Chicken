package harapanbangsachicken.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import harapanbangsachicken.model.classes.Promo;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PromoView extends JFrame{
    private JPanel frame;
    private JLabel logoLabel, header;
    private JButton updatePromo, insertPromo, deletePromo, backButton;
    private GridBagConstraints gbc;

    public PromoView() {
        super("Menu Admin");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));

        frame = new JPanel(new GridBagLayout());
        frame.setBackground(Color.RED);

        // show image icon
        ImageIcon logoIcon = new ImageIcon("src/harapanbangsachicken/view/gambar/mcd.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        logoLabel = new JLabel(logoIcon, SwingConstants.CENTER);

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        frame.add(logoLabel, gbc);

        header = new JLabel("Silahkan Pilih Opsi Anda", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.PLAIN, 28));
        header.setForeground(Color.YELLOW);

        gbc.gridy = 1;
        gbc.gridwidth = 5;
        frame.add(header, gbc);

        // Button untuk mengubah promo secara spesifik
        updatePromo = new JButton("Update Promo");
        updatePromo.setFont(new Font("Arial", Font.BOLD, 16));
        updatePromo.setBackground(Color.RED);
        updatePromo.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frame.add(updatePromo, gbc);

        // Button untuk menambahkan promo
        insertPromo = new JButton("Tambah Promo");
        insertPromo.setFont(new Font("Arial", Font.BOLD, 16));
        insertPromo.setBackground(Color.RED);
        insertPromo.setForeground(Color.YELLOW);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frame.add(insertPromo, gbc);

        // Button untuk menghapus promo secara spesifik
        deletePromo = new JButton("Hapus Promo");
        deletePromo.setFont(new Font("Arial", Font.BOLD, 16));
        deletePromo.setBackground(Color.RED);
        deletePromo.setForeground(Color.YELLOW);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frame.add(deletePromo, gbc);

        // Button untuk kembali ke menu admin
        backButton = new JButton("Kembali");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        frame.add(backButton, gbc);

        add(frame);

        setVisible(true);

        updatePromo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new UpdatePromo();
            }

        });

        insertPromo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new InsertPromo();
            }

        });

        deletePromo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ArrayList<Promo> show = Promo.getData();
                dispose();
                new DeletePromo(show);
            }

        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuAdmin();
            }
        });
    }
}
