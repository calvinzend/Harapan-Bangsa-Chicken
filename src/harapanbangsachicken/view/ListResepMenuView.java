package harapanbangsachicken.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import harapanbangsachicken.controller.ResepAdminController;
import harapanbangsachicken.model.classes.Menu;
import harapanbangsachicken.model.classes.Resep;

public class ListResepMenuView extends JFrame {
    private JPanel mainPanel, panel2, buttonPanel;
    private JLabel header;
    private JTable resepTable;
    private DefaultTableModel tableModel;
    private JButton backButton, insertNewButton;

    public ListResepMenuView(final int menu_id) {
        super("Edit Resep Admin");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.RED);

        panel2 = new JPanel(new BorderLayout());
        panel2.setOpaque(false);

        header = new JLabel("Resep List", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.PLAIN, 28));
        header.setForeground(Color.YELLOW);
        panel2.add(header, BorderLayout.NORTH);

        Menu menu = Menu.getDataById(menu_id);

        String[] columnNames = { "Menu Id", "Ingredient Id", "Ingredient name", "Quantity", "Unit", "Update",
                "Delete" };
        tableModel = new DefaultTableModel(columnNames, 0);
        resepTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 5;
            }
        };

        JTableHeader header = resepTable.getTableHeader();
        header.setBackground(Color.RED);
        header.setForeground(Color.YELLOW);
        header.setFont(new Font("Arial", Font.BOLD, 14));

        resepTable.setBackground(Color.RED);
        resepTable.setForeground(Color.YELLOW);
        resepTable.setFont(new Font("Arial", Font.PLAIN, 14));
        resepTable.setRowHeight(30);
        resepTable.setGridColor(Color.WHITE);

        for (Resep r : menu.getResep()) {
            int ing_id = r.getBahan().getIng_id();
            String ing_name = r.getBahan().getIngredientName();
            double quantity = r.getQuantity();
            String satuan = r.getSatuan();

            tableModel.addRow(new Object[] {
                    menu_id,
                    ing_id,
                    ing_name,
                    quantity,
                    satuan,
                    "Update",
                    "Delete"
            });
        }

        resepTable.getColumn("Update").setCellRenderer(new ButtonRenderer());
        resepTable.getColumn("Delete").setCellRenderer(new ButtonRenderer());

        resepTable.getColumn("Update").setCellEditor(new ButtonEditor(new JButton("Update"), resepTable, e -> {
            int ing_id = Integer.valueOf(e.getActionCommand());
            new UpdateResepAdmin(menu_id, ing_id);
            dispose();
        }));

        resepTable.getColumn("Delete").setCellEditor(new ButtonEditor(new JButton("Delete"), resepTable, e -> {
            int ing_id = Integer.valueOf(e.getActionCommand());
            String msg = new ResepAdminController().deleteResep(menu_id, ing_id);
            if (msg != null) {
                showMessage(msg);
                new ListResepMenuView(menu_id);
                dispose();
            }
        }));

        JScrollPane scrollPane = new JScrollPane(resepTable);
        scrollPane.getViewport().setBackground(Color.RED);

        panel2.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(panel2, BorderLayout.CENTER);

        // Back button
        backButton = new JButton("Back");

        // Insert button
        insertNewButton = new JButton("Add Resep");

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.RED);
        buttonPanel.add(backButton);
        buttonPanel.add(insertNewButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Menu> menu = Menu.getData();
                new ListMenuAdminView(menu);
                dispose();
            }
        });

        insertNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InsertResep(menu_id);
                dispose();
            }
        });
    }

    private static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    private static class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private JTable table;
        private ActionListener actionListener;

        public ButtonEditor(JButton button, JTable table, ActionListener actionListener) {
            super(new JTextField());
            this.button = button;
            this.button.setOpaque(true);
            this.table = table;
            this.actionListener = actionListener;

            this.button.addActionListener(e -> {
                int row = table.getEditingRow();
                if (row >= 0) {
                    Object menuIdObj = table.getValueAt(row, 1);
                    if (menuIdObj != null) {
                        String ingId = menuIdObj.toString();
                        actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ingId));
                    }
                }
                fireEditingStopped();
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            button.setText(value != null ? value.toString() : "");
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return button.getText();
        }
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}