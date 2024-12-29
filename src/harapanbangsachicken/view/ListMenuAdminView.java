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

public class ListMenuAdminView extends JFrame{
    private JPanel frame;
    private JLabel logoLabel, header;
    private JButton updateMenu, insertMenu, deleteMenu, backButton;
    private GridBagConstraints gbc;

    public ListMenuAdminView() {
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

        header = new JLabel("Choose Your Option", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.PLAIN, 28));
        header.setForeground(Color.YELLOW);

        gbc.gridy = 1;
        gbc.gridwidth = 5;
        frame.add(header, gbc);

        // Button untuk mengubah menu secara spesifik
        updateMenu = new JButton("Update Menu");
        updateMenu.setFont(new Font("Arial", Font.BOLD, 16));
        updateMenu.setBackground(Color.RED);
        updateMenu.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frame.add(updateMenu, gbc);

        // Button untuk menambahkan menu
        insertMenu = new JButton("Insert Menu");
        insertMenu.setFont(new Font("Arial", Font.BOLD, 16));
        insertMenu.setBackground(Color.RED);
        insertMenu.setForeground(Color.YELLOW);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frame.add(insertMenu, gbc);

        // Button untuk menghapus menu secara spesifik
        deleteMenu = new JButton("Delete Menu");
        deleteMenu.setFont(new Font("Arial", Font.BOLD, 16));
        deleteMenu.setBackground(Color.RED);
        deleteMenu.setForeground(Color.YELLOW);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frame.add(deleteMenu, gbc);

        // Button untuk kembali ke menu admin
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        frame.add(backButton, gbc);

        add(frame);

        setVisible(true);

        updateMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new UpdateMenu();
            }

        });

        insertMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new InsertMenu();
            }

        });

        deleteMenu.addActionListener(new ActionListener() {
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
