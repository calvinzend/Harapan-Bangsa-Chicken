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

public class RewardView extends JFrame{
    private JPanel frame;
    private JLabel logoLabel, header;
    private JButton updateReward, insertReward, backButton;
    private GridBagConstraints gbc;

    public RewardView() {
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

        // Button untuk mengubah reward secara spesifik
        updateReward = new JButton("Update Reward");
        updateReward.setFont(new Font("Arial", Font.BOLD, 16));
        updateReward.setBackground(Color.RED);
        updateReward.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frame.add(updateReward, gbc);

        // Button untuk menambahkan reward
        insertReward = new JButton("Tambah Reward");
        insertReward.setFont(new Font("Arial", Font.BOLD, 16));
        insertReward.setBackground(Color.RED);
        insertReward.setForeground(Color.YELLOW);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frame.add(insertReward, gbc);

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

        updateReward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new UpdateReward();
            }

        });

        insertReward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new InsertReward();
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
