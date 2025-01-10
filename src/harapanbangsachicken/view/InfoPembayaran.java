package harapanbangsachicken.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.Border;

import harapanbangsachicken.model.classes.SingletonManager;
import harapanbangsachicken.model.classes.Transaction;

public class InfoPembayaran extends JFrame {

    private JPanel panel;
    private JLabel logoLabel;
    private JButton back;
    private Border roundedBorderButton = BorderFactory.createLineBorder(Color.BLACK, 2, true);

    public InfoPembayaran() {
        super("Info Pembayaran");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.RED);

        // Logo setup
        ImageIcon logoIcon = new ImageIcon("src/harapanbangsachicken/view/gambar/mcd.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        logoLabel = new JLabel(logoIcon);

        // Title setup
        JLabel titleLabel = new JLabel("Informasi Pembelian");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.YELLOW);

        // Transaction Info setup
        Transaction transaction = Transaction.getLatestTransactionByUserId(
            SingletonManager.getInstance().getUser().getUser_id()
        );

        JPanel transactionPanel = new JPanel();
        transactionPanel.setBackground(Color.RED);
        transactionPanel.setLayout(new BoxLayout(transactionPanel, BoxLayout.Y_AXIS));
        
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);

        if (transaction != null) {
            String transactionInfo = String.format(
                "<html><div style='text-align: center;'>ID Transaksi: %d<br>Tanggal: %s<br>Total Harga: Rp %s<br>Potongan Promo: Rp %s</div></html>",
                transaction.getTransaction_id(),
                transaction.getTanggalPembelian(),
                numberFormat.format(transaction.getHargaTotal()),
                numberFormat.format(transaction.getPotonganPromo())
            );

            JLabel transactionLabel = new JLabel(transactionInfo);
            transactionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            transactionLabel.setForeground(Color.YELLOW);
            transactionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            transactionPanel.add(transactionLabel);
        } else {
            JLabel noDataLabel = new JLabel("Tidak ada riwayat pembelian.");
            noDataLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            noDataLabel.setForeground(Color.YELLOW);
            noDataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            transactionPanel.add(noDataLabel);
        }

        // Back Button setup
        back = new JButton("Back");
        back.setBorder(roundedBorderButton);
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.setBackground(Color.RED);
        back.setForeground(Color.YELLOW);
        back.setPreferredSize(new Dimension(200, 40));

        // Layout setup
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        panel.add(logoLabel, gbc);

        gbc.gridy = 1;
        panel.add(titleLabel, gbc);

        gbc.gridy = 2;
        panel.add(transactionPanel, gbc);

        gbc.gridy = 3;
        panel.add(back, gbc);

        add(panel);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuView();
            }
        });

        setVisible(true);
    }
}
