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

import harapanbangsachicken.controller.IngredientAdminController;
import harapanbangsachicken.model.classes.Ingredient;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class IngredientView extends JFrame{
    private JPanel mainPanel, panel2, buttonPanel;
    private JLabel header;
    private JTable rewardTable;
    private DefaultTableModel tableModel;
    private JButton updateIngredient, insertIngredient, deleteIngredient, addStock, backButton;
    private Border roundedBorder = BorderFactory.createLineBorder(Color.YELLOW, 2, true);

    public IngredientView(ArrayList<Ingredient> ingredient) {
        super("Menu Admin");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.RED);

        panel2 = new JPanel(new BorderLayout());
        panel2.setOpaque(false);

        header = new JLabel("Info Ingredient", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.PLAIN, 28));
        header.setForeground(Color.YELLOW);
        panel2.add(header, BorderLayout.NORTH);

        String[] columnNames = {"Ingredient ID", "Ingredient Name", "Ingredient Stock", "Stock Unit"};
        tableModel = new DefaultTableModel(columnNames, 0);
        rewardTable = new JTable(tableModel);
        rewardTable.setBackground(Color.RED);
        rewardTable.setForeground(Color.YELLOW);
        rewardTable.setFont(new Font("Arial", Font.PLAIN, 15));
        rewardTable.setRowHeight(50);

        for (Ingredient ing : ingredient) {
            
            Object[] rowData = {
                ing.getIng_id(),
                ing.getIngredientName(),
                ing.getStock(),
                ing.getSatuan()
            };
        
            tableModel.addRow(rowData);
        }

        JScrollPane scrollPane = new JScrollPane(rewardTable);
        scrollPane.setBorder(roundedBorder);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        panel2.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(panel2, BorderLayout.CENTER);

        // Button untuk mengubah promo secara spesifik
        updateIngredient = new JButton("Update Ingredient");
        updateIngredient.setFont(new Font("Arial", Font.BOLD, 16));
        updateIngredient.setBackground(Color.RED);
        updateIngredient.setForeground(Color.YELLOW);

        // Button untuk menambahkan promo
        insertIngredient = new JButton("Insert Ingredient");
        insertIngredient.setFont(new Font("Arial", Font.BOLD, 16));
        insertIngredient.setBackground(Color.RED);
        insertIngredient.setForeground(Color.YELLOW);

        // Button untuk menghapus promo
        deleteIngredient = new JButton("Delete Ingredient");
        deleteIngredient.setFont(new Font("Arial", Font.BOLD, 16));
        deleteIngredient.setBackground(Color.RED);
        deleteIngredient.setForeground(Color.YELLOW);

        // Button untuk menambahkan stock
        addStock = new JButton("Add Stock Ingredient");
        addStock.setFont(new Font("Arial", Font.BOLD, 16));
        addStock.setBackground(Color.RED);
        addStock.setForeground(Color.YELLOW);

        // Button untuk kembali ke menu admin
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.YELLOW);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.RED);
        buttonPanel.add(insertIngredient);
        buttonPanel.add(updateIngredient);
        buttonPanel.add(deleteIngredient);
        buttonPanel.add(addStock);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setVisible(true);

        updateIngredient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new IngredientAdminController(new UpdateIngredient());
            }

        });

        insertIngredient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new IngredientAdminController(new InsertIngredient());
            }

        });

        deleteIngredient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new IngredientAdminController(new DeleteIngredient());
            }
            
        });

        addStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new IngredientAdminController(new AddStockIngredient());
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
