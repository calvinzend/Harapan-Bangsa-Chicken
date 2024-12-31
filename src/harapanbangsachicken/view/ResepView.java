package harapanbangsachicken.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResepView extends JFrame{
    private JPanel frame;
    private JLabel logoLabel, header;
    private JButton updateResep, insertResep, showResep, backButton;
    private GridBagConstraints gbc;

    public ResepView() {
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

        // Button untuk mengubah resep secara spesifik
        updateResep = new JButton("Update Resep");
        updateResep.setFont(new Font("Arial", Font.BOLD, 16));
        updateResep.setBackground(Color.RED);
        updateResep.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frame.add(updateResep, gbc);

        // Button untuk menambahkan resep
        insertResep = new JButton("Tambah Resep");
        insertResep.setFont(new Font("Arial", Font.BOLD, 16));
        insertResep.setBackground(Color.RED);
        insertResep.setForeground(Color.YELLOW);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frame.add(insertResep, gbc);

        // Button untuk menampilkan resep secara spesifik
        showResep = new JButton("Show Resep");
        showResep.setFont(new Font("Arial", Font.BOLD, 16));
        showResep.setBackground(Color.RED);
        showResep.setForeground(Color.YELLOW);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frame.add(showResep, gbc);

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

        updateResep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new UpdateResep();
            }

        });

        insertResep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new InsertResep();
            }

        });

        showResep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                
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
