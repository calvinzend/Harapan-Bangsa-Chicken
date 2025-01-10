package harapanbangsachicken.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import harapanbangsachicken.controller.PromoAdminController;
import harapanbangsachicken.model.classes.Promo;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class PromoView extends JFrame{
    private JPanel mainPanel, panel2, buttonPanel;
    private JLabel header;
    private JTable promoTable;
    private DefaultTableModel tableModel;
    private JButton updatePromo, insertPromo, deletePromo, backButton;
    private Border roundedBorder = BorderFactory.createLineBorder(Color.YELLOW, 2, true);

    public PromoView(ArrayList<Promo> promo) {
        super("Menu Admin");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.RED);

        panel2 = new JPanel(new BorderLayout());
        panel2.setOpaque(false);

        header = new JLabel("Info Promo", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.PLAIN, 28));
        header.setForeground(Color.YELLOW);
        panel2.add(header, BorderLayout.NORTH);

        String[] columnNames = {"Promo ID", "Promo Name", "Promo Nominal", "Expired Date"};
        tableModel = new DefaultTableModel(columnNames, 0);

        promoTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        promoTable.setBackground(Color.RED);
        promoTable.setForeground(Color.YELLOW);
        promoTable.setFont(new Font("Arial", Font.PLAIN, 15));
        promoTable.setRowHeight(25);
        promoTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        promoTable.getTableHeader().setBackground(Color.RED);
        promoTable.getTableHeader().setForeground(Color.YELLOW);

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);

        for (Promo prm : promo) {
            String nominalPromo = numberFormat.format(prm.getNominalPromo());

            Object[] rowData = {
                prm.getPromo_id(),
                prm.getNamaPromo(),
                nominalPromo,
                prm.getDate()
            };
        
            tableModel.addRow(rowData);
        }

        JScrollPane scrollPane = new JScrollPane(promoTable);
        scrollPane.getViewport().setBackground(Color.RED);
        scrollPane.setBorder(roundedBorder);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        panel2.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(panel2, BorderLayout.CENTER);

        // Button untuk mengubah promo secara spesifik
        updatePromo = new JButton("Update Promo");
        updatePromo.setFont(new Font("Arial", Font.BOLD, 16));
        updatePromo.setBackground(Color.RED);
        updatePromo.setForeground(Color.YELLOW);

        // Button untuk menambahkan promo
        insertPromo = new JButton("Insert Promo");
        insertPromo.setFont(new Font("Arial", Font.BOLD, 16));
        insertPromo.setBackground(Color.RED);
        insertPromo.setForeground(Color.YELLOW);

        // Button untuk menghapus promo
        deletePromo = new JButton("Delete Promo");
        deletePromo.setFont(new Font("Arial", Font.BOLD, 16));
        deletePromo.setBackground(Color.RED);
        deletePromo.setForeground(Color.YELLOW);

        // Button untuk kembali ke menu admin
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.YELLOW);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.RED);
        buttonPanel.add(insertPromo);
        buttonPanel.add(updatePromo);
        buttonPanel.add(deletePromo);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setVisible(true);

        updatePromo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new PromoAdminController(new UpdatePromo());
            }

        });

        insertPromo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new PromoAdminController(new InsertPromo());
            }

        });

        deletePromo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new PromoAdminController(new DeletePromo());
            }
            
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuAdmin();
            }
        });
    }
}
