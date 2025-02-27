package harapanbangsachicken.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import harapanbangsachicken.model.classes.Transaction;
import harapanbangsachicken.model.classes.Customer;
import harapanbangsachicken.model.classes.SingletonManager;

public class TransaksiView extends JFrame {
    private JPanel frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton backButton;

    public TransaksiView() {
        super("History");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame = new JPanel(new BorderLayout());
        frame.setBackground(Color.RED);

        JLabel titleLabel = new JLabel("Transaction History", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.YELLOW);
        frame.add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"Transaction ID", "Purchase Date", "Promotional Pieces", "Total Price"};
        tableModel = new DefaultTableModel(columnNames, 0);

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);

        ArrayList<Transaction> historyList = Customer.getDataHistory(SingletonManager.getInstance().getUser().getUser_id());
        for (Transaction transaction : historyList) {
            Object[] rowData = {
                transaction.getTransaction_id(),
                transaction.getTanggalPembelian(),
                "Rp " + numberFormat.format(transaction.getPotonganPromo()),
                "Rp " + numberFormat.format(transaction.getHargaTotal())
            };
            tableModel.addRow(rowData);
        }


        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setForeground(Color.YELLOW);
        table.setBackground(Color.RED);
        table.setRowHeight(30);
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
                new MenuView();
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
