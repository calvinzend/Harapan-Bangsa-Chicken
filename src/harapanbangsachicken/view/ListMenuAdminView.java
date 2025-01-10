package harapanbangsachicken.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import harapanbangsachicken.controller.MenuEditAdminController;
import harapanbangsachicken.model.classes.Drink;
import harapanbangsachicken.model.classes.Menu;

public class ListMenuAdminView extends JFrame {
    private JPanel mainPanel, panel2, buttonPanel;
    private JLabel header;
    private JTable menuTable;
    private DefaultTableModel tableModel;
    private JButton backButton, insertNewButton;

    public ListMenuAdminView(ArrayList<Menu> listMenu) {
        super("Edit Menu Admin");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.RED);

        panel2 = new JPanel(new BorderLayout());
        panel2.setOpaque(false);

        header = new JLabel("Menu List", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.PLAIN, 28));
        header.setForeground(Color.YELLOW);
        panel2.add(header, BorderLayout.NORTH);

        String[] columnNames = { "ID", "Name", "Price", "Size", "Image", "View Recipe", "Update", "Delete" };
        tableModel = new DefaultTableModel(columnNames, 0);

        menuTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 5;
            }
        };
        
        JTableHeader header = menuTable.getTableHeader();
        header.setBackground(Color.RED);
        header.setForeground(Color.YELLOW);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        
        menuTable.setBackground(Color.RED);
        menuTable.setForeground(Color.YELLOW);
        menuTable.setFont(new Font("Arial", Font.PLAIN, 14));
        menuTable.setRowHeight(30);
        menuTable.setGridColor(Color.WHITE);

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);

        for (Menu m : listMenu) {
            int menu_id = m.getMenu_id();
            String nama = m.getNama();
            String harga = numberFormat.format(m.getHarga());
            String size = "-";
            String gambarPath = m.getGambarPath();

            if (m instanceof Drink) {
                Drink d = (Drink) m;
                size = String.valueOf(d.getSize());
            }
            tableModel.addRow(new Object[] {
                    menu_id,
                    nama,
                    harga,
                    size,
                    gambarPath,
                    "View Recipe",
                    "Update",
                    "Delete"
            });
        }

        menuTable.getColumn("View Recipe").setCellRenderer(new ButtonRenderer());
        menuTable.getColumn("Update").setCellRenderer(new ButtonRenderer());
        menuTable.getColumn("Delete").setCellRenderer(new ButtonRenderer());

        menuTable.getColumn("View Recipe").setCellEditor(new ButtonEditor(new JButton("Recipe"), menuTable, e -> {
            int menuId = Integer.valueOf(e.getActionCommand());
            new ListResepMenuView(menuId);
            dispose();
        }));

        menuTable.getColumn("Update").setCellEditor(new ButtonEditor(new JButton("Update"), menuTable, e -> {
            int menuId = Integer.valueOf(e.getActionCommand());
            new UpdateMenu(menuId);
            dispose();
        }));

        menuTable.getColumn("Delete").setCellEditor(new ButtonEditor(new JButton("Delete"), menuTable, e -> {
            int menuId = Integer.valueOf(e.getActionCommand());
            String msg = new MenuEditAdminController().deleteMenu(menuId);
            if (msg != null) {
                showMessage(msg);
                ArrayList<Menu> show = Menu.getData();
                new ListMenuAdminView(show);
                dispose();
            }
        }));

        JScrollPane scrollPane = new JScrollPane(menuTable);
        scrollPane.getViewport().setBackground(Color.RED);
        panel2.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(panel2, BorderLayout.CENTER);

        // Back button
        backButton = new JButton("Back");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.YELLOW);

        // Add new Button
        insertNewButton = new JButton("Add New");
        insertNewButton.setBackground(Color.RED);
        insertNewButton.setForeground(Color.YELLOW);

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
                new MenuAdmin();
                dispose();
            }
        });

        insertNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InsertMenu();
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
                    Object menuIdObj = table.getValueAt(row, 0);
                    if (menuIdObj != null) {
                        String menuId = menuIdObj.toString();
                        actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, menuId));
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