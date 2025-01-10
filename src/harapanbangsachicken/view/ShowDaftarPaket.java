package harapanbangsachicken.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;

import harapanbangsachicken.controller.StockController;
import harapanbangsachicken.model.classes.Keranjang;
import harapanbangsachicken.model.classes.Paket;
import harapanbangsachicken.model.classes.UpdateKeranjang;

public class ShowDaftarPaket extends JFrame {

    private JButton backButton, keranjangButton;

    public ShowDaftarPaket(ArrayList<Paket> paket) {
        super("Show Paket");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.RED);

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setOpaque(false);

        JLabel header = new JLabel("Daftar Paket", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.PLAIN, 28));
        header.setForeground(Color.YELLOW);
        panel2.add(header, BorderLayout.NORTH);

        JPanel paketPanel = new JPanel(new GridLayout(2, 0, 10, 10));
        paketPanel.setBackground(Color.RED);

        for (Paket paketMenu : paket) {
            int keranjangQuantity = 0;
            for (Keranjang k : UpdateKeranjang.getInstance().getKeranjang()) {
                if (k.getMenu() == null) {
                    if (k.getPaket() != null) {
                        if (k.getPaket().getPaket_id() == paketMenu.getPaket_id()) {
                            keranjangQuantity = k.getJumlah();
                        }
                    }
                }
            }
            JPanel itemPanel = new JPanel();
            itemPanel.setBackground(Color.RED);
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));

            JLabel foodLabel = new JLabel(paketMenu.getNamaPaket());
            foodLabel.setForeground(Color.YELLOW);
            foodLabel.setBackground(Color.WHITE);
            foodLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel foodImage = new JLabel(new ImageIcon(paketMenu.getPicture_path()));
            foodImage.setAlignmentX(Component.CENTER_ALIGNMENT);
            foodImage.setPreferredSize(new Dimension(200, 200));

            JLabel foodPriceLabel = new JLabel("Rp " + paketMenu.getHarga());
            foodPriceLabel.setBackground(Color.WHITE);
            foodPriceLabel.setForeground(Color.YELLOW);
            foodPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel quantityPanel = new JPanel();
            quantityPanel.setBackground(Color.RED);
            quantityPanel.setLayout(new FlowLayout());

            JButton minusButton = new JButton("-");
            minusButton.setBackground(Color.RED);
            minusButton.setForeground(Color.YELLOW);
            JTextField quantityField = new JTextField(3);
            quantityField.setText(String.valueOf(keranjangQuantity));
            quantityField.setEditable(false);
            quantityField.setHorizontalAlignment(JTextField.CENTER);
            JButton plusButton = new JButton("+");
            plusButton.setBackground(Color.RED);
            plusButton.setForeground(Color.YELLOW);

            StockController stockController = new StockController();

            plusButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int currentFieldQuantity = Integer.parseInt(quantityField.getText());
                    if (stockController.checkStockPaket(paketMenu, currentFieldQuantity + 1)) {
                        int newQuantity = currentFieldQuantity + 1;
                        quantityField.setText(String.valueOf(newQuantity));

                        for (Keranjang k : UpdateKeranjang.getInstance().getKeranjang()) {
                            if (k.getMenu() == null) {
                                if (k.getPaket() != null) {
                                    if (k.getPaket().getPaket_id() == paketMenu.getPaket_id()) {
                                        k.setJumlah(newQuantity);
                                        return;
                                    }
                                }
                            }
                        }

                        UpdateKeranjang.getInstance().getKeranjang().add(new Keranjang(paketMenu, newQuantity));

                    } else {
                        showMessage("Stock tidak cukup!");
                    }
                }
            });

            minusButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int currentQuantity = Integer.parseInt(quantityField.getText());
                    int newQuantity = 0;

                    if (currentQuantity > 0) {
                        newQuantity = currentQuantity - 1;
                        quantityField.setText(String.valueOf(newQuantity));
                        for (Keranjang k : UpdateKeranjang.getInstance().getKeranjang()) {
                            if (k.getMenu() == null) {
                                if (k.getPaket() != null) {
                                    if (k.getPaket().getPaket_id() == paketMenu.getPaket_id()) {
                                        if (newQuantity > 0) {
                                            k.setJumlah(newQuantity);
                                        } else {
                                            UpdateKeranjang.getInstance().getKeranjang().remove(k);
                                        }
                                        return;
                                    }
                                }
                            }

                        }
                    }
                }
            });

            quantityPanel.add(minusButton);
            quantityPanel.add(quantityField);
            quantityPanel.add(plusButton);

            itemPanel.add(foodLabel);
            itemPanel.add(foodImage);
            itemPanel.add(foodPriceLabel);
            itemPanel.add(quantityPanel);

            paketPanel.add(itemPanel);
        }

        JScrollPane scrollPane = new JScrollPane(paketPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
        panel2.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(panel2, BorderLayout.CENTER);

        keranjangButton = new JButton("Keranjang");
        keranjangButton.setFont(new Font("Arial", Font.PLAIN, 16));
        keranjangButton.setBackground(Color.RED);
        keranjangButton.setForeground(Color.YELLOW);

        backButton = new JButton("Kembali");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.YELLOW);

        keranjangButton.addActionListener(e -> {
            new ShowKeranjang();
            dispose();
        });

        backButton.addActionListener(e -> {
            new ShowMenuView();
            dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.RED);
        buttonPanel.add(keranjangButton);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}