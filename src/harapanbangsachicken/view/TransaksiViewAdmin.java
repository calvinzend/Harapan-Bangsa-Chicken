package harapanbangsachicken.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import harapanbangsachicken.model.classes.Transaction;
import harapanbangsachicken.model.classes.Admin;
import harapanbangsachicken.model.classes.Customer;
import harapanbangsachicken.model.classes.SingletonManager;

public class TransaksiViewAdmin extends JFrame {
    private JPanel frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton backButton;

    public TransaksiViewAdmin() {
        super("Data History User");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame = new JPanel(new BorderLayout());
        frame.setBackground(Color.RED);

        JLabel titleLabel = new JLabel("History Transaksi", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.YELLOW);
        frame.add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"Transaction ID", "Tanggal Pembelian", "Potongan Promo", "Harga Total"};
        tableModel = new DefaultTableModel(columnNames, 0);


        ArrayList<Transaction> historyList = Admin.getDataHistory();
        for (Transaction transaction : historyList) {
            Object[] rowData = {
                transaction.getTransaction_id(),
                transaction.getTanggalPembelian(),
                transaction.getPotonganPromo(),
                transaction.getHargaTotal()
            };
            tableModel.addRow(rowData);
        }


        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setForeground(Color.YELLOW);
        table.setBackground(Color.RED);
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        table.getTableHeader().setBackground(Color.RED);
        table.getTableHeader().setForeground(Color.YELLOW);


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.RED);
        scrollPane.getViewport().setBackground(Color.RED);
        frame.add(scrollPane, BorderLayout.CENTER);


        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.YELLOW);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuAdmin();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.RED);
        buttonPanel.add(backButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        add(frame);

        setVisible(true);
    }
} 
