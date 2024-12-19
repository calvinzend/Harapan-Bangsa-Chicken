package harapanbangsachicken.view;

import java.awt.*;
import java.util.ArrayList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

import harapanbangsachicken.model.classes.Drink;
import harapanbangsachicken.model.classes.Keranjang;
import harapanbangsachicken.model.classes.UpdateKeranjang;

public class ShowKeranjang extends JFrame {
    private JButton backButton, showMenu, checkOut;
    static double totalBelanja = 0;

    public ShowKeranjang() {
        super("Show Keranjang");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));

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

        JLabel header = new JLabel("Daftar Menu", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.PLAIN, 28));
        header.setForeground(Color.YELLOW);
        panel2.add(header, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel(new GridLayout(2, 0, 10, 10));
        menuPanel.setBackground(Color.RED);

        for (Keranjang dataKeranjang : keranjang) {
            JPanel itemPanel = new JPanel();
            itemPanel.setBackground(Color.RED);
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));

            JLabel foodLabel = new JLabel(dataKeranjang.getMenu().getNama());
            foodLabel.setForeground(Color.YELLOW);
            foodLabel.setBackground(Color.WHITE);
            foodLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            if (dataKeranjang.getMenu() instanceof Drink) {
                Drink data = (Drink) dataKeranjang.getMenu();
                foodLabel.setText(data.getNama() + " - " + data.getSize());
            }

            JLabel foodImage = new JLabel(new ImageIcon(dataKeranjang.getMenu().getGambarPath()));
            foodImage.setAlignmentX(Component.CENTER_ALIGNMENT);
            foodImage.setPreferredSize(new Dimension(200, 200));

            JPanel quantityPanel = new JPanel();
            quantityPanel.setBackground(Color.RED);
            quantityPanel.setLayout(new FlowLayout());

            JButton minusButton = new JButton("-");
            minusButton.setBackground(Color.RED);
            minusButton.setForeground(Color.YELLOW);
            JTextField quantityField = new JTextField(3);
            quantityField.setText(String.valueOf(dataKeranjang.getJumlah()));
            quantityField.setEditable(false);
            quantityField.setHorizontalAlignment(JTextField.CENTER);
            JButton plusButton = new JButton("+");
            plusButton.setBackground(Color.RED);
            plusButton.setForeground(Color.YELLOW);

            double totalHarga = dataKeranjang.getJumlah() * dataKeranjang.getMenu().getHarga();
            JLabel foodPriceLabel = new JLabel("Total Rp " + String.valueOf(String.valueOf(totalHarga)));
            totalBelanja += totalHarga;
            totalLabel.setText("Total Belanja : Rp " + String.valueOf(totalBelanja));

            foodPriceLabel.setBackground(Color.WHITE);
            foodPriceLabel.setForeground(Color.YELLOW);
            foodPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            plusButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int newQuantity = Integer.parseInt(quantityField.getText()) + 1;
                    quantityField.setText(String.valueOf(newQuantity));
                    dataKeranjang.setJumlah(newQuantity);
                    UpdateKeranjang.getInstance().addKeranjang(dataKeranjang);
                    foodPriceLabel.setText("Total Rp " + (newQuantity * dataKeranjang.getMenu().getHarga()));
                    totalBelanja += 1 * dataKeranjang.getMenu().getHarga();
                    totalLabel.setText("Total Belanja : Rp " + String.valueOf(totalBelanja));
                }
            });
            minusButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int currentQuantity = Integer.parseInt(quantityField.getText());
                    totalBelanja -= 1 * dataKeranjang.getMenu().getHarga();
                    totalLabel.setText("Total Belanja : Rp " + String.valueOf(totalBelanja));
                    if (currentQuantity > 1) {
                        int newQuantity = currentQuantity - 1;
                        quantityField.setText(String.valueOf(newQuantity));
                        dataKeranjang.setJumlah(newQuantity);
                        UpdateKeranjang.getInstance().addKeranjang(dataKeranjang);
                        foodPriceLabel.setText("Total Rp " + (newQuantity * dataKeranjang.getMenu().getHarga()));
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

        backButton = new JButton("Kembali");
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
            new ShowMenuView();
            dispose();
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
}