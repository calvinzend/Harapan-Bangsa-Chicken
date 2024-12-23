package harapanbangsachicken.view;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import harapanbangsachicken.model.classes.Promo;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeletePromo extends JFrame{
    private JPanel frame;
    private JLabel logoLabel, header, promoLabel;
    private JTextField promoValue;
    private JButton backButton, deleteButton;
    private GridBagConstraints gbc;
    private Border roundedBorder = BorderFactory.createLineBorder(Color.YELLOW, 2, true);

    public DeletePromo(ArrayList<Promo> promo) {
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

        header = new JLabel("List Promo", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.PLAIN, 28));
        header.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 5;
        frame.add(header, gbc);

        for (Promo prm : promo) {
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBackground(Color.RED);
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

            JLabel idPromo = new JLabel(String.valueOf(prm.getPromo_id()));
            idPromo.setFont(new Font("Arial", Font.PLAIN, 16));
            idPromo.setForeground(Color.YELLOW);
            idPromo.setBackground(Color.RED);

            JLabel namaPromo = new JLabel(prm.getNamaPromo());
            namaPromo.setFont(new Font("Arial", Font.PLAIN, 16));
            namaPromo.setForeground(Color.YELLOW);
            namaPromo.setBackground(Color.RED);

            panel.add(idPromo);
            panel.add(namaPromo);
        }

        promoLabel = new JLabel("Masukkan ID Promo: ");
        promoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        promoLabel.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 5;
        frame.add(logoLabel, gbc);

        promoValue = new JTextField(10);
        promoValue.setBorder(roundedBorder);
        promoValue.setPreferredSize(new Dimension(300, 40));
        promoValue.setFont(new Font("Arial", Font.PLAIN, 16));
        promoValue.setBackground(Color.WHITE);
        promoValue.setForeground(Color.BLACK);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 5;
        frame.add(logoLabel, gbc);

        deleteButton = new JButton("Hapus Promo");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 16));
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        frame.add(deleteButton, gbc);

        // Button untuk kembali ke menu admin
        backButton = new JButton("Kembali");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.YELLOW);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        frame.add(backButton, gbc);

        add(frame);

        setVisible(true);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int promo_id = Integer.valueOf(promoValue.getText());

                Promo.deletePromo(promo_id);

                dispose();
                new MenuAdmin();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PromoView();
            }
        });
    }
}