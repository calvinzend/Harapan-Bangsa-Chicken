package harapanbangsachicken.view;

import javax.swing.*;
import javax.swing.border.Border;

import harapanbangsachicken.controller.PaketEditAdminController;
import harapanbangsachicken.model.classes.Drink;
import harapanbangsachicken.model.classes.Menu;
import harapanbangsachicken.model.classes.Paket;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class InsertPaketMenu extends JFrame {
    private JPanel frame, input;
    private JLabel logoLabel, namaLabel, menuIdLabel, sizeLabel, pictureLabel;
    private JTextField menuIdField, sizeField, picturePathField;
    private JComboBox<String> namaMenuCombo;
    private JButton submitButton, backButton;
    private Border roundedBorder = BorderFactory.createLineBorder(Color.YELLOW, 2, true);

    public InsertPaketMenu(int id_paket) {
        super("Insert Paket Menu");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame = new JPanel(new GridBagLayout());
        frame.setBackground(Color.RED);

        // untuk label dan input
        input = new JPanel(new GridBagLayout());
        input.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // show image icon
        ImageIcon logoIcon = new ImageIcon("src/harapanbangsachicken/view/gambar/mcd.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        logoLabel = new JLabel(logoIcon, SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        input.add(logoLabel, gbc);

        HashMap<Integer, String> menuOpt = new HashMap<>();

        ArrayList<Menu> menuList = Menu.getData();

        for (Menu m : menuList) {
            menuOpt.put(m.getMenu_id(), m.getNama());
        }

        String[] option = menuOpt.values().toArray(new String[0]);

        // label nama
        namaLabel = new JLabel("Nama :");
        namaLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        namaLabel.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        input.add(namaLabel, gbc);

        // Combo box namaMenu
        namaMenuCombo = new JComboBox<>(option);
        namaMenuCombo.setBorder(roundedBorder);
        namaMenuCombo.setPreferredSize(new Dimension(300, 40));
        namaMenuCombo.setFont(new Font("Arial", Font.PLAIN, 16));
        namaMenuCombo.setBackground(Color.WHITE);
        namaMenuCombo.setForeground(Color.BLACK);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        input.add(namaMenuCombo, gbc);

        // label ID
        menuIdLabel = new JLabel("Menu Id :");
        menuIdLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        menuIdLabel.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        input.add(menuIdLabel, gbc);

        // input ID
        menuIdField = new JTextField();
        menuIdField.setBorder(roundedBorder);
        menuIdField.setPreferredSize(new Dimension(300, 40));
        menuIdField.setFont(new Font("Arial", Font.PLAIN, 16));
        menuIdField.setBackground(Color.WHITE);
        menuIdField.setForeground(Color.BLACK);
        menuIdField.setEditable(false);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        input.add(menuIdField, gbc);

        // label Size Minuman
        sizeLabel = new JLabel("Size :");
        sizeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        sizeLabel.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        input.add(sizeLabel, gbc);

        // input Size Minuman
        sizeField = new JTextField();
        sizeField.setBorder(roundedBorder);
        sizeField.setPreferredSize(new Dimension(300, 40));
        sizeField.setFont(new Font("Arial", Font.PLAIN, 16));
        sizeField.setBackground(Color.WHITE);
        sizeField.setForeground(Color.BLACK);
        sizeField.setEditable(false);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        input.add(sizeField, gbc);

        // Label for Picture
        pictureLabel = new JLabel("Picture Path:");
        pictureLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        pictureLabel.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        input.add(pictureLabel, gbc);

        // TextField to display the selected file path
        picturePathField = new JTextField();
        picturePathField.setBorder(roundedBorder);
        picturePathField.setPreferredSize(new Dimension(300, 40));
        picturePathField.setFont(new Font("Arial", Font.PLAIN, 16));
        picturePathField.setBackground(Color.WHITE);
        picturePathField.setForeground(Color.BLACK);
        picturePathField.setEditable(false);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        input.add(picturePathField, gbc);

        // Submit button
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBackground(Color.RED);
        submitButton.setForeground(Color.YELLOW);

        // back button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.YELLOW);

        // Submit and Back Buttons
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        input.add(submitButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        input.add(backButton, gbc);

        frame.add(input);

        add(frame);

        setVisible(true);

        namaMenuCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected_Menu = String.valueOf(namaMenuCombo.getSelectedItem());

                Integer newId = new PaketEditAdminController().getKeyByValue(menuOpt, selected_Menu);
                Menu menu = Menu.getDataById(newId);

                menuIdField.setText(String.valueOf(newId));
                if (menu instanceof Drink) {
                    Drink drink = (Drink) menu;
                    sizeField.setText(String.valueOf(drink.getSize()));
                }
                picturePathField.setText(menu.getGambarPath());
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer menuId = Integer.parseInt(menuIdField.getText());

                if (menuId > 0) {
                    if (new PaketEditAdminController().insertMenuToPaket(id_paket, menuId)) {
                        showMessage("Menu Berhasil dimasukkan ke Paket");
                        new ShowPaketMenu(id_paket);
                        dispose();
                    } else {
                        showMessage("Error!, menu gagal ditambahkan ke paket");
                    }
                } else {
                    showMessage("Mohon Isi semua Field!");
                }

            }
        });

        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Menu> show = Paket.getPaketMenu(id_paket);
                new PaketMenuEdit(show, id_paket);
                dispose();
            }

        });
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}