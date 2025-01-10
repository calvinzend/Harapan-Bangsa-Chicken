package harapanbangsachicken.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.*;

import harapanbangsachicken.controller.StockController;
import harapanbangsachicken.model.classes.Drink;
import harapanbangsachicken.model.classes.Food;
import harapanbangsachicken.model.classes.Keranjang;
import harapanbangsachicken.model.classes.UpdateKeranjang;

public class ShowKeranjang extends JFrame {
    private JButton backButton, showMenu, checkOut;
    private double totalBelanja = 0;

    public ShowKeranjang() {
        super("Show Keranjang");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel totalLabel = new JLabel();
        totalLabel.setForeground(Color.YELLOW);
        totalLabel.setFont(new Font("Arial", Font.ITALIC, 30));

        ArrayList<Keranjang> keranjang = UpdateKeranjang.getInstance().getKeranjang();
        if (keranjang == null) {
            keranjang = new ArrayList<>();
        }

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.RED);

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setOpaque(false);

        JLabel header = new JLabel("Menu List", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.PLAIN, 28));
        header.setForeground(Color.YELLOW);
        panel2.add(header, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel(new GridLayout(2, 0, 10, 10));
        menuPanel.setBackground(Color.RED);

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);

        for (Keranjang dataKeranjang : keranjang) {
            JPanel itemPanel = new JPanel();
            itemPanel.setBackground(Color.RED);
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));

            String nama = "";
            String img = "";
            double totalHarga = 0;
            int quantity = dataKeranjang.getJumlah();

            if (dataKeranjang.getMenu() != null) {
                if (dataKeranjang.getMenu() instanceof Drink) {
                    Drink data = (Drink) dataKeranjang.getMenu();
                    nama = data.getNama() + " - " + data.getSize();
                } else {
                    nama = dataKeranjang.getMenu().getNama();
                }
                img = dataKeranjang.getMenu().getGambarPath();
                totalHarga = dataKeranjang.getJumlah() * dataKeranjang.getMenu().getHarga();
            } else {
                nama = dataKeranjang.getPaket().getNamaPaket();
                img = dataKeranjang.getPaket().getPicture_path();
                totalHarga = dataKeranjang.getJumlah() * dataKeranjang.getPaket().getHarga();
            }

            JLabel foodLabel = new JLabel(nama);
            foodLabel.setForeground(Color.YELLOW);
            foodLabel.setBackground(Color.WHITE);
            foodLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel foodImage = new JLabel(new ImageIcon(img));
            foodImage.setAlignmentX(Component.CENTER_ALIGNMENT);
            foodImage.setPreferredSize(new Dimension(200, 200));

            JPanel quantityPanel = new JPanel();
            quantityPanel.setBackground(Color.RED);
            quantityPanel.setLayout(new FlowLayout());

            JButton minusButton = new JButton("-");
            minusButton.setBackground(Color.RED);
            minusButton.setForeground(Color.YELLOW);
            JTextField quantityField = new JTextField(3);
            quantityField.setText(String.valueOf(quantity));
            quantityField.setEditable(false);
            quantityField.setHorizontalAlignment(JTextField.CENTER);
            JButton plusButton = new JButton("+");
            plusButton.setBackground(Color.RED);
            plusButton.setForeground(Color.YELLOW);

            JLabel foodPriceLabel = new JLabel("Total Rp " + numberFormat.format(totalHarga));
            totalBelanja += totalHarga;
            totalLabel.setText("Total Belanja : Rp " + numberFormat.format(totalBelanja));

            foodPriceLabel.setBackground(Color.WHITE);
            foodPriceLabel.setForeground(Color.YELLOW);
            foodPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            StockController stockController = new StockController();

            plusButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (stockController.checkStockKeranjang(dataKeranjang)) {
                        int newQuantity = Integer.parseInt(quantityField.getText()) + 1;
                        quantityField.setText(String.valueOf(newQuantity));
                        dataKeranjang.setJumlah(newQuantity);
                        UpdateKeranjang.getInstance().addKeranjang(dataKeranjang);
                        double newTotal = 0;
                        if (dataKeranjang.getMenu() != null) {
                            newTotal = newQuantity * dataKeranjang.getMenu().getHarga();
                            totalBelanja += 1 * dataKeranjang.getMenu().getHarga();
                        } else {
                            newTotal = newQuantity * dataKeranjang.getPaket().getHarga();
                            totalBelanja += 1 * dataKeranjang.getPaket().getHarga();
                        }
                        foodPriceLabel.setText("Total Rp " + newTotal);
                        totalLabel.setText("Total Expenditure : Rp " + String.valueOf(totalBelanja));
                    } else {
                        showMessage("Insufficient stock!");
                    }

                }
            });
            minusButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int currentQuantity = Integer.parseInt(quantityField.getText());

                    if (dataKeranjang.getMenu() != null) {
                        totalBelanja -= 1 * dataKeranjang.getMenu().getHarga();
                    } else {
                        totalBelanja -= 1 * dataKeranjang.getPaket().getHarga();
                    }
                    totalLabel.setText("Total Expenditure : Rp " + String.valueOf(totalBelanja));

                    if (currentQuantity > 1) {
                        int newQuantity = currentQuantity - 1;
                        quantityField.setText(String.valueOf(newQuantity));
                        dataKeranjang.setJumlah(newQuantity);
                        UpdateKeranjang.getInstance().addKeranjang(dataKeranjang);

                        double newTotal = 0;
                        if (dataKeranjang.getMenu() != null) {
                            newTotal = newQuantity * dataKeranjang.getMenu().getHarga();
                        } else {
                            newTotal = newQuantity * dataKeranjang.getPaket().getHarga();
                        }
                        foodPriceLabel.setText("Total Rp " + newTotal);
                    } else {
                        UpdateKeranjang.getInstance().getKeranjang().remove(dataKeranjang);
                        menuPanel.remove(itemPanel);
                        menuPanel.revalidate();
                        menuPanel.repaint();
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

            menuPanel.add(itemPanel);
        }

        JPanel totalPanel = new JPanel();
        totalPanel.setBackground(Color.red);
        totalPanel.add(totalLabel, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(menuPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        panel2.add(scrollPane, BorderLayout.CENTER);
        panel2.add(totalPanel, BorderLayout.SOUTH);

        mainPanel.add(panel2, BorderLayout.CENTER);

        showMenu = new JButton("Show Menu");
        showMenu.setFont(new Font("Arial", Font.PLAIN, 16));
        showMenu.setBackground(Color.RED);
        showMenu.setForeground(Color.YELLOW);

        checkOut = new JButton("Check Out");
        checkOut.setFont(new Font("Arial", Font.PLAIN, 16));
        checkOut.setBackground(Color.RED);
        checkOut.setForeground(Color.YELLOW);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.YELLOW);

        showMenu.addActionListener(e -> {
            new ShowMenuView();
            dispose();
        });

        checkOut.addActionListener(e -> {
            // new checkOutMenuView();
            dispose();
        });

        backButton.addActionListener(e -> {
            new MenuView();
            dispose();
        });
        // checkOut.addActionListener(e -> {
        // new Checkout();
        // dispose();
        // });
        checkOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Keranjang> keranjang = UpdateKeranjang.getInstance().getKeranjang();
                StockController stockController = new StockController();
                ArrayList<Keranjang> invalidItem = stockController.checkStockFinal(keranjang);
                if (invalidItem.size() != 0) {
                    String msg = "";
                    int i = 1;
                    for (Keranjang invalid : invalidItem) {
                        String nama = "";
                        if (invalid.getMenu() != null) {
                            if (invalid.getMenu() instanceof Food) {
                                nama = invalid.getMenu().getNama();
                            } else {
                                Drink menu = (Drink) invalid.getMenu();
                                nama = menu.getNama() + "-" + menu.getSize();
                            }
                        } else {
                            nama = invalid.getPaket().getNamaPaket();
                        }
                        msg += "\n" + i + "." + nama + " x" + invalid.getJumlah();
                        i++;
                    }
                    boolean remove = removeInvalidItem(msg);
                    if (remove) {
                        for (Keranjang invalid : invalidItem) {
                            if (invalid.getMenu() != null) {
                                Iterator<Keranjang> iterator = keranjang.iterator();
                                while (iterator.hasNext()) {
                                    Keranjang k = iterator.next();
                                    if (invalid.getMenu().getNama().equals(k.getMenu().getNama()) && invalid.getMenu().getHarga() == k.getMenu().getHarga()) {
                                        iterator.remove();
                                    }
                                }
                            } else {
                                Iterator<Keranjang> iterator = keranjang.iterator();
                                while (iterator.hasNext()) {
                                    Keranjang k = iterator.next();
                                    if (invalid.getPaket().getNamaPaket().equals(k.getPaket().getNamaPaket()) && invalid.getPaket().getHarga() == k.getPaket().getHarga()) {
                                        iterator.remove();
                                    }
                                }
                            }
                        }
                        new Checkout();
                        dispose();
                    } else {
                        new ShowKeranjang();
                        dispose();
                    }
                } else {
                    new Checkout();
                    dispose();
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.RED);
        buttonPanel.add(showMenu);
        buttonPanel.add(checkOut);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public boolean removeInvalidItem(String massage) {
        int result = JOptionPane.showConfirmDialog(this,
                "Sorry, this item is not available \n" + massage + "\nRemove it?", "Stock not available!",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }
}