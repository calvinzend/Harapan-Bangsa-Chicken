package harapanbangsachicken.view;

import javax.swing.*;

import harapanbangsachicken.model.classes.Drink;
import harapanbangsachicken.model.classes.Keranjang;
import harapanbangsachicken.model.classes.UpdateKeranjang;
import harapanbangsachicken.controller.CheckoutController;
import java.awt.*;
import java.util.ArrayList;

public class Checkout extends JFrame {
    private double totalBelanja;
    public Checkout() {
        super("Checkout");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        double totalHarga = 0;

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.RED);

        JLabel headerLabel = new JLabel("Checkout", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerLabel.setForeground(Color.YELLOW);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.RED);
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        mainPanel.add(headerPanel);

        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        cartPanel.setBackground(Color.RED);
        cartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ArrayList<Keranjang> keranjang = UpdateKeranjang.getInstance().getKeranjang();
        if (keranjang == null) {
            keranjang = new ArrayList<>();
        }

        for (Keranjang item : keranjang) {
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setBackground(Color.RED);
            itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
            ImageIcon originalIcon = new ImageIcon(item.getMenu().getGambarPath());
            Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            JLabel imageLabel = new JLabel(scaledIcon);
            imageLabel.setPreferredSize(new Dimension(50, 50));
            JPanel imagePanel = new JPanel();
            imagePanel.setBackground(Color.RED);
            imageLabel.setPreferredSize(new Dimension(50, 50));
            imagePanel.add(imageLabel);
            itemPanel.add(imagePanel, BorderLayout.WEST);
        
            JPanel detailsPanel = new JPanel();
            detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
            detailsPanel.setBackground(Color.RED);

            JLabel nameLabel = new JLabel(item.getMenu().getNama());
            nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
            nameLabel.setForeground(Color.YELLOW);
            detailsPanel.add(nameLabel);
            
            if (item.getMenu() instanceof Drink) {
                Drink data = (Drink) item.getMenu();
                nameLabel.setText(data.getNama() + " - " + data.getSize());
            }
        
            JLabel priceLabel = new JLabel("Harga: Rp " + item.getMenu().getHarga());
            priceLabel.setForeground(Color.YELLOW);
            detailsPanel.add(priceLabel);
        
            JLabel quantityLabel = new JLabel("Jumlah: " + item.getJumlah());
            quantityLabel.setForeground(Color.YELLOW);
            detailsPanel.add(quantityLabel);
        
            itemPanel.add(detailsPanel, BorderLayout.CENTER);
        
            JPanel totalPricePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            totalPricePanel.setBackground(Color.RED);
            totalHarga = item.getJumlah() * item.getMenu().getHarga();
            JLabel totalPriceLabel = new JLabel("Total: Rp " + totalHarga);
            totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 14));
            totalPriceLabel.setForeground(Color.YELLOW);
            totalPricePanel.add(totalPriceLabel);
            totalBelanja += totalHarga;
        
            itemPanel.add(totalPricePanel, BorderLayout.EAST);
        
            cartPanel.add(itemPanel);
        }

        JScrollPane cartScrollPane = new JScrollPane(cartPanel);
        cartScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        cartScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        cartScrollPane.setPreferredSize(new Dimension(800, 500));
        mainPanel.add(cartScrollPane);

        JPanel actionPanel = new JPanel(new GridBagLayout());
        actionPanel.setBackground(Color.RED);
        actionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel totalLabel = new JLabel("Total Belanja: Rp " + String.valueOf(totalBelanja));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST; 
        actionPanel.add(totalLabel, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.RED);

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        checkoutButton.setBackground(Color.RED);
        checkoutButton.setForeground(Color.YELLOW);

        JButton kembaliButton = new JButton("Kembali");
        kembaliButton.setFont(new Font("Arial", Font.BOLD, 16));
        kembaliButton.setBackground(Color.RED);
        kembaliButton.setForeground(Color.YELLOW);

        buttonPanel.add(kembaliButton);
        buttonPanel.add(checkoutButton);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST; 
        actionPanel.add(buttonPanel, gbc);

        kembaliButton.addActionListener(e -> {
            new ShowKeranjang();
            dispose();
        });
       
        CheckoutController checkoutController = new CheckoutController();
        checkoutButton.addActionListener(e -> checkoutController.Konfirmasi(this));

        mainPanel.add(actionPanel);

        add(new JScrollPane(mainPanel));
        setVisible(true);
    }

}
