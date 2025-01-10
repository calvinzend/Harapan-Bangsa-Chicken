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

import harapanbangsachicken.controller.PaketEditAdminController;
import harapanbangsachicken.model.classes.Paket;

public class ListPaketAdminView extends JFrame {
    private JPanel mainPanel, panel2, buttonPanel;
    private JLabel header;
    private JTable paketTable;
    private DefaultTableModel tableModel;
    private JButton backButton, insertNewButton;

    public ListPaketAdminView(ArrayList<Paket> listPaket) {
        super("Edit Paket Admin");

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

        String[] columnNames = { "ID Paket", "Name", "Price", "Image", "View Menu", "Update", "Delete" };
        tableModel = new DefaultTableModel(columnNames, 0);
        paketTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 4;
            }
        };

        JTableHeader header = paketTable.getTableHeader();
        header.setBackground(Color.RED);
        header.setForeground(Color.YELLOW);
        header.setFont(new Font("Arial", Font.BOLD, 14));
                
        paketTable.setBackground(Color.RED);
        paketTable.setForeground(Color.YELLOW);
        paketTable.setFont(new Font("Arial", Font.PLAIN, 14));
        paketTable.setRowHeight(30);
        paketTable.setGridColor(Color.WHITE);

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);

        for (Paket p : listPaket) {
            int paket_id = p.getPaket_id();
            String nama = p.getNamaPaket();
            String harga = numberFormat.format(p.getHarga());
            String gambarPath = p.getPicture_path();

            tableModel.addRow(new Object[] {
                    paket_id,
                    nama,
                    harga,
                    gambarPath,
                    "View Menu",
                    "Update",
                    "Delete"
            });
        }

        paketTable.getColumn("View Menu").setCellRenderer(new ButtonRenderer());
        paketTable.getColumn("Update").setCellRenderer(new ButtonRenderer());
        paketTable.getColumn("Delete").setCellRenderer(new ButtonRenderer());

        paketTable.getColumn("View Menu").setCellEditor(new ButtonEditor(new JButton("View Menu"), paketTable, e -> {
            int paketId = Integer.valueOf(e.getActionCommand());
            new ShowPaketMenu(paketId);
            dispose();
        }));

        paketTable.getColumn("Update").setCellEditor(new ButtonEditor(new JButton("Update"), paketTable, e -> {
            int paketId = Integer.valueOf(e.getActionCommand());
            new UpdatePaketAdmin(paketId);
            dispose();
        }));

        paketTable.getColumn("Delete").setCellEditor(new ButtonEditor(new JButton("Delete"), paketTable, e -> {
            int paketId = Integer.valueOf(e.getActionCommand());
            String msg = new PaketEditAdminController().deletePaket(paketId);
            if(msg != null){
                showMessage(msg);
                ArrayList<Paket> show = Paket.getData(); 
                new ListPaketAdminView(show);
                dispose();
            }
        }));

        JScrollPane scrollPane = new JScrollPane(paketTable);
        scrollPane.getViewport().setBackground(Color.RED);
        panel2.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(panel2, BorderLayout.CENTER);

        // Back button
        backButton = new JButton("Back");

        // Add new Button
        insertNewButton = new JButton("Add New");

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
                new InsertPaket();
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
                        String paketId = menuIdObj.toString();
                        actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, paketId));
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