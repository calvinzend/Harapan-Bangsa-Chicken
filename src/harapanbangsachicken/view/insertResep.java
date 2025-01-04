package harapanbangsachicken.view;

import javax.swing.*;
import javax.swing.border.Border;

import harapanbangsachicken.model.classes.Ingredient;
import harapanbangsachicken.model.classes.Resep;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class InsertResep extends JFrame {
    private JPanel frame, input;
    private JLabel logoLabel, ingNameLabel, ingIdLabel, quantityLabel, satuanLabel;
    private JTextField ingIdField, quantityField, satuanField;
    private JComboBox<String> ingCombo;
    private JButton submitButton, backButton;
    private Border roundedBorder = BorderFactory.createLineBorder(Color.YELLOW, 2, true);

    public InsertResep(final int idMenu) {
        super("New Resep");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));

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

        HashMap<Integer, String> ingredientOpt = new HashMap<>();

        ArrayList<Ingredient> ingredientList = Ingredient.getData();

        for (Ingredient i : ingredientList) {
            ingredientOpt.put(i.getIng_id(), i.getIngredientName());
        }

        String[] option = ingredientOpt.values().toArray(new String[0]);

        // label nama
        ingNameLabel = new JLabel("Ingredient :");
        ingNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        ingNameLabel.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        input.add(ingNameLabel, gbc);

        // Combo nama
        ingCombo = new JComboBox<>(option);
        ingCombo.setBorder(roundedBorder);
        ingCombo.setPreferredSize(new Dimension(300, 40));
        ingCombo.setFont(new Font("Arial", Font.PLAIN, 16));
        ingCombo.setBackground(Color.WHITE);
        ingCombo.setForeground(Color.BLACK);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        input.add(ingCombo, gbc);

        // Ingredient id
        ingIdLabel = new JLabel("Ingredient Id :");
        ingIdLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        ingIdLabel.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        input.add(ingIdLabel, gbc);

        // input ingredient id
        ingIdField = new JTextField();
        ingIdField.setBorder(roundedBorder);
        ingIdField.setPreferredSize(new Dimension(300, 40));
        ingIdField.setFont(new Font("Arial", Font.PLAIN, 16));
        ingIdField.setBackground(Color.WHITE);
        ingIdField.setForeground(Color.BLACK);
        ingIdField.setEditable(false);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        input.add(ingIdField, gbc);

        // label Quantity
        quantityLabel = new JLabel("Quantity :");
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        quantityLabel.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        input.add(quantityLabel, gbc);

        // input Quantity
        quantityField = new JTextField();
        quantityField.setBorder(roundedBorder);
        quantityField.setPreferredSize(new Dimension(300, 40));
        quantityField.setFont(new Font("Arial", Font.PLAIN, 16));
        quantityField.setBackground(Color.WHITE);
        quantityField.setForeground(Color.BLACK);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        input.add(quantityField, gbc);

        // Label Satuan
        satuanLabel = new JLabel("Satuan :");
        satuanLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        satuanLabel.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        input.add(satuanLabel, gbc);

        // TextField to display the selected file path
        satuanField = new JTextField();
        satuanField.setBorder(roundedBorder);
        satuanField.setPreferredSize(new Dimension(300, 40));
        satuanField.setFont(new Font("Arial", Font.PLAIN, 16));
        satuanField.setBackground(Color.WHITE);
        satuanField.setForeground(Color.BLACK);
        satuanField.setEditable(false);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        input.add(satuanField, gbc);

        // // Button to open file chooser
        // selectPictureButton = new JButton("Select New Picture");
        // selectPictureButton.setFont(new Font("Arial", Font.PLAIN, 14));
        // selectPictureButton.setBackground(Color.RED);
        // selectPictureButton.setForeground(Color.YELLOW);

        // gbc.gridx = 1;
        // gbc.gridy = 3;
        // gbc.gridwidth = 1;
        // input.add(picturePathField, gbc);

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

        ingCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected_ingredient = String.valueOf(ingCombo.getSelectedItem());

                int newIngId = getKeyByValue(ingredientOpt, selected_ingredient);
                String satuan = null;
                for (Ingredient i : ingredientList) {
                    if (i.getIng_id() == newIngId) {
                        satuan = i.getSatuan();
                        break;
                    }
                }

                ingIdField.setText(String.valueOf(newIngId));
                satuanField.setText(satuan);
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newIngId = Integer.parseInt(ingIdField.getText());
                String newIngName = String.valueOf(ingCombo.getSelectedItem());
                double newQuantity = Double.parseDouble(quantityField.getText());
                String newSatuan = satuanField.getText();

                
                if(newIngId != 0 || newIngName != null || newQuantity != 0 || newSatuan != null){
                    Resep newResep = new Resep(Ingredient.getData(newIngId), newQuantity, newSatuan); 
                    if (Resep.insertData(idMenu, newResep)) {
                        showMessage("Insert Resep Berhasil!");
                        dispose();
                        new ListResepMenuView(idMenu);
                    } else {
                        showMessage("Insert Resep gagal!");
                    }
                }else{
                    showMessage("Mohon ISI semua field!");
                }

            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ListResepMenuView(idMenu);
            }
        });
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public int getKeyByValue(HashMap<Integer, String> map, String value) {
        for (Integer key : map.keySet()) {
            if (map.get(key) == value) {
                return key;
            }
        }
        return -1;
    }
}