package harapanbangsachicken.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import harapanbangsachicken.controller.PromoController;
import harapanbangsachicken.model.classes.Paket;
import harapanbangsachicken.model.classes.Promo;

public class PromoMenu {
    public PromoMenu() {
        JFrame frame = new JFrame("View Promo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);;
        frame.getContentPane().setBackground(Color.RED);
        frame.setFont(new Font("Arial",Font.BOLD,30));;
        frame.setLayout(new BorderLayout());

        ArrayList<Promo> promoList = PromoController.PromoController();
       
        JLabel headerLabel = new JLabel("View Promo", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.YELLOW);
        headerLabel.setOpaque(true);
        headerLabel.setBackground(Color.RED);
        frame.add(headerLabel, BorderLayout.NORTH);

        String[] columnNames = {"Nama Promo", "Deskripsi", "Aksi"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };

        for (Promo promo : promoList) {
            tableModel.addRow(new Object[]{promo.getNamaPromo(), promo.getNominalPromo(), "Claim"});
        }

        table.getColumn("Aksi").setCellRenderer(new ButtonRenderer());

        table.getColumn("Aksi").setCellEditor(new ButtonEditor(new JCheckBox(), frame, promoList));

        table.setRowHeight(40);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setBackground(Color.RED);
        table.setForeground(Color.WHITE);
        table.setGridColor(Color.YELLOW);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(Color.BLACK);
        headerRenderer.setForeground(Color.YELLOW);
        table.getTableHeader().setDefaultRenderer(headerRenderer);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.RED);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Kembali");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.YELLOW);
        backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        backButton.addActionListener(e -> frame.dispose());
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.RED);
        bottomPanel.add(backButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Checkout();
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            setBackground(Color.RED);
            setForeground(Color.BLACK);
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            return this;
        }
    }

    static class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean clicked;
        private JTable table;
        private JFrame frame;
        private ArrayList<Promo> promoList;
        private static ArrayList<Promo> claimedPromos = new ArrayList<>();
        
        public ButtonEditor(JCheckBox checkBox, JFrame frame, ArrayList<Promo> promoList) {
            super(checkBox);
            this.frame = frame;
            this.promoList = promoList;
            this.claimedPromos = new ArrayList<>(); 
        
            button = new JButton();
            button.setOpaque(true);
            button.setBackground(Color.RED);
            button.setForeground(Color.BLACK);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
            button.addActionListener((ActionEvent e) -> {
                int row = table.convertRowIndexToModel(table.getEditingRow()); 
                
                if (row >= 0 && row < promoList.size()) {
                    Promo promo = promoList.get(row);
                    
                    if (claimedPromos.contains(promo)) {
                        JOptionPane.showMessageDialog(frame, "Promo '" + promo.getNamaPromo() + "' sudah diklaim sebelumnya!");
                    } else {
                        claimedPromos.add(promo);
                        JOptionPane.showMessageDialog(frame, "Promo '" + promo.getNamaPromo() + "' berhasil diklaim!");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Kesalahan: Promo tidak ditemukan!");
                }
        
                fireEditingStopped();
            });
        }
        public static ArrayList<Promo> getClaimedPromos() {
            return claimedPromos;
        }
        
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.table = table; 
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            clicked = true;
            return button;
        }
        
        @Override
        public Object getCellEditorValue() {
            return label;
        }
        
        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }
        
        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
    
}
