package harapanbangsachicken.view;

import javax.swing.*;
import javax.swing.border.Border;

import harapanbangsachicken.controller.MenuEditAdminController;
import harapanbangsachicken.model.classes.Drink;
import harapanbangsachicken.model.classes.Menu;
import harapanbangsachicken.model.enums.Size;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpdateMenu extends JFrame {
    private JPanel frame, input;
    private JLabel logoLabel, namaLabel, hargaLabel, sizeLabel, pictureLabel;
    private JTextField namaField, hargaField, sizeField, picturePathField;
    private JButton submitButton, backButton, selectPictureButton;
    private Border roundedBorder = BorderFactory.createLineBorder(Color.YELLOW, 2, true);

    public UpdateMenu(int idMenu) {
        super("Update Menu");
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

        // set logo MCD
        ImageIcon logoIcon = new ImageIcon("src/harapanbangsachicken/view/gambar/mcd.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        logoLabel = new JLabel(logoIcon);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        input.add(logoLabel, gbc);

        Menu menu = Menu.getDataById(idMenu);

        // label nama
        namaLabel = new JLabel("Name :");
        namaLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        namaLabel.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        input.add(namaLabel, gbc);

        // field nama
        namaField = new JTextField(menu.getNama());
        namaField.setBorder(roundedBorder);
        namaField.setPreferredSize(new Dimension(300, 40));
        namaField.setFont(new Font("Arial", Font.PLAIN, 16));
        namaField.setBackground(Color.WHITE);
        namaField.setForeground(Color.BLACK);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        input.add(namaField, gbc);

        // label harga
        hargaLabel = new JLabel("Price :");
        hargaLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        hargaLabel.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        input.add(hargaLabel, gbc);

        // input harga
        hargaField = new JTextField(String.valueOf(menu.getHarga()));
        hargaField.setBorder(roundedBorder);
        hargaField.setPreferredSize(new Dimension(300, 40));
        hargaField.setFont(new Font("Arial", Font.PLAIN, 16));
        hargaField.setBackground(Color.WHITE);
        hargaField.setForeground(Color.BLACK);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        input.add(hargaField, gbc);

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

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        input.add(sizeField, gbc);

        if (menu instanceof Drink) {
            Drink d = (Drink) menu;
            sizeField.setText(String.valueOf(d.getSize()));
        } else {
            sizeLabel.setEnabled(false);
            sizeField.setEnabled(false);
        }

        // Label for Picture
        pictureLabel = new JLabel("Picture Path:");
        pictureLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        pictureLabel.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        input.add(pictureLabel, gbc);

        // TextField to display the selected file path
        picturePathField = new JTextField(menu.getGambarPath());
        picturePathField.setBorder(roundedBorder);
        picturePathField.setPreferredSize(new Dimension(300, 40));
        picturePathField.setFont(new Font("Arial", Font.PLAIN, 16));
        picturePathField.setBackground(Color.WHITE);
        picturePathField.setForeground(Color.BLACK);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        input.add(picturePathField, gbc);

        // Button to open file chooser
        selectPictureButton = new JButton("Select New Picture");
        selectPictureButton.setFont(new Font("Arial", Font.PLAIN, 14));
        selectPictureButton.setBackground(Color.RED);
        selectPictureButton.setForeground(Color.YELLOW);

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        input.add(selectPictureButton, gbc);

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

        selectPictureButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a Picture");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                picturePathField.setText(selectedFilePath);
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = namaField.getText();
                int harga = Integer.parseInt(hargaField.getText());
                String gambarPath = picturePathField.getText();
                String size = sizeField.getText();

                boolean isFilled = false;
                Menu newMenu = null;

                MenuEditAdminController menuController = new MenuEditAdminController();

                if (menu instanceof Drink) {
                    isFilled = menuController.fieldNotEmpty(name, harga, gambarPath, size);
                } else {
                    isFilled = menuController.fieldNotEmpty(name, harga, gambarPath);
                }

                if (isFilled) {
                    if (menu instanceof Drink) {
                        newMenu = new Drink(idMenu, name, harga, gambarPath, Size.valueOf(size));
                    } else {
                        newMenu = new Menu(idMenu, name, harga, gambarPath);
                    }
                    String msg = menuController.updateMenu(newMenu);
                    showMessage(msg);
                    if (msg != "Update Menu gagal!") {
                        ArrayList<Menu> menuList = Menu.getData();
                        new ListMenuAdminView(menuList);
                        dispose();
                    }
                } else {
                    showMessage("Mohon isi semua field");
                }

            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Menu> show = Menu.getData();
                dispose();
                new ListMenuAdminView(show);
            }
        });
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}