package harapanbangsachicken.view;

import javax.swing.*;

import harapanbangsachicken.model.classes.CheckoutResult;
import harapanbangsachicken.model.classes.Customer;
import harapanbangsachicken.model.classes.Drink;
import harapanbangsachicken.model.classes.Keranjang;
import harapanbangsachicken.model.classes.Promo;
import harapanbangsachicken.model.classes.SingletonManager;
import harapanbangsachicken.model.classes.UpdateKeranjang;
import harapanbangsachicken.view.PromoMenu.ButtonEditor;
import harapanbangsachicken.controller.CheckoutController;
import harapanbangsachicken.controller.StockController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

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

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);

        for (Keranjang item : keranjang) {
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setBackground(Color.RED);
            itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            String img = "";
            String nama = "";
            double harga = 0;
            int jumlah = item.getJumlah();
            ImageIcon originalIcon;
            
            if (item.getMenu() != null) {
                img = item.getMenu().getGambarPath();
                try {
                    originalIcon = new ImageIcon(img);
                    if (originalIcon.getIconWidth() <= 0 || originalIcon.getIconHeight() <= 0) {
                        throw new Exception("Image not found");
                    }
                } catch (Exception e) {
                    String defaultImagePath = "src/harapanbangsachicken/view/gambar/defaultMenu.png";
                    originalIcon = new ImageIcon(defaultImagePath);
                }
                nama = item.getMenu().getNama();
                harga = item.getMenu().getHarga();
            } else {
                img = item.getPaket().getPicture_path();
                try {
                    originalIcon = new ImageIcon(img);
                    if (originalIcon.getIconWidth() <= 0 || originalIcon.getIconHeight() <= 0) {
                        throw new Exception("Image not found");
                    }
                } catch (Exception e) {
                    String defaultImagePath = "src/harapanbangsachicken/view/gambar/paket/defaultPaket.png";
                    originalIcon = new ImageIcon(defaultImagePath);
                }
                nama = item.getPaket().getNamaPaket();
                harga = item.getPaket().getHarga();
            }

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

            JLabel nameLabel = new JLabel(nama);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
            nameLabel.setForeground(Color.YELLOW);
            detailsPanel.add(nameLabel);

            if (item.getMenu() instanceof Drink) {
                Drink data = (Drink) item.getMenu();
                nameLabel.setText(data.getNama() + " - " + data.getSize());
            }

            JLabel priceLabel = new JLabel("Price: Rp " + numberFormat.format(harga));
            priceLabel.setForeground(Color.YELLOW);
            detailsPanel.add(priceLabel);

            JLabel quantityLabel = new JLabel("Quantity: " + jumlah);
            quantityLabel.setForeground(Color.YELLOW);
            detailsPanel.add(quantityLabel);

            itemPanel.add(detailsPanel, BorderLayout.CENTER);

            JPanel totalPricePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            totalPricePanel.setBackground(Color.RED);
            totalHarga = jumlah * harga;
            JLabel totalPriceLabel = new JLabel("Total: Rp " + numberFormat.format(totalHarga));
            totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 14));
            totalPriceLabel.setForeground(Color.YELLOW);
            totalPricePanel.add(totalPriceLabel);
            totalBelanja += totalHarga;

            itemPanel.add(totalPricePanel, BorderLayout.EAST);

            cartPanel.add(itemPanel);
        }
        int totalPromo = 0;
        ArrayList<Promo> promoList = ButtonEditor.getClaimedPromos();
        for (Promo promo : ButtonEditor.getClaimedPromos()) {
            StringBuilder claimedPromoList = new StringBuilder();
            claimedPromoList.append(promo.getNamaPromo())
                    .append(" - Nominal: ")
                    .append(numberFormat.format(promo.getNominalPromo()))
                    .append("\n");
            totalPromo += promo.getNominalPromo();
            JLabel promoLabel = new JLabel(claimedPromoList.toString());
            promoLabel.setFont(new Font("Arial", Font.BOLD, 16));
            promoLabel.setForeground(Color.YELLOW);
            cartPanel.add(promoLabel);
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

        double harga = totalBelanja - totalPromo;
        if (harga < 0) {
            harga = 0;
        }

        UpdateKeranjang.getInstance().setTotalHarga(harga);
        JLabel totalLabel = new JLabel("Total Expenditure: Rp " + numberFormat.format(harga));
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

        JButton kembaliButton = new JButton("Back");
        kembaliButton.setFont(new Font("Arial", Font.BOLD, 16));
        kembaliButton.setBackground(Color.RED);
        kembaliButton.setForeground(Color.YELLOW);

        JButton promoButton = new JButton("Promo");
        promoButton.setFont(new Font("Arial", Font.PLAIN, 16));
        promoButton.setBackground(Color.RED);
        promoButton.setForeground(Color.YELLOW);

        buttonPanel.add(kembaliButton);
        if (!keranjang.isEmpty()) {
            buttonPanel.add(promoButton);
        }
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

        ArrayList<Keranjang> finalKeranjang = new ArrayList<>(keranjang);
        double finalharga = harga;

        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckoutController checkoutController = new CheckoutController();
                StockController stockController = new StockController();

                CheckoutResult.initializeInstance(finalKeranjang, promoList, finalharga);

                boolean statusPembayaran = checkoutController.Konfirmasi(Checkout.this);
                if (statusPembayaran) {
                    Customer customer = (Customer) SingletonManager.getInstance().getUser();

                    int point = customer.getPoint();
                    point += 100;
                    customer.setPoint(point);

                    Customer.updateCustomerPoint();

                    UpdateKeranjang.getInstance().clearKeranjang();
                    stockController.updateStock(finalKeranjang);
                    new InfoPembayaran();
                    dispose();
                }
            }
        });

        promoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PromoMenu();
            }

        });

        mainPanel.add(actionPanel);

        add(new JScrollPane(mainPanel));
        setVisible(true);
    }

}
