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

import harapanbangsachicken.controller.RewardAdminController;
import harapanbangsachicken.model.classes.Reward;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RewardView extends JFrame{
    private JPanel mainPanel, panel2, buttonPanel;
    private JLabel header;
    private JTable rewardTable;
    private DefaultTableModel tableModel;
    private JButton updateReward, insertReward, deleteReward, backButton;
    private Border roundedBorder = BorderFactory.createLineBorder(Color.YELLOW, 2, true);

    public RewardView(ArrayList<Reward> reward) {
        super("Menu Admin");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.RED);

        panel2 = new JPanel(new BorderLayout());
        panel2.setOpaque(false);

        header = new JLabel("Info Reward", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.PLAIN, 28));
        header.setForeground(Color.YELLOW);
        panel2.add(header, BorderLayout.NORTH);

        String[] columnNames = {"Reward ID", "Reward Name", "Minimal Poin"};
        tableModel = new DefaultTableModel(columnNames, 0);
        
        rewardTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        rewardTable.setBackground(Color.RED);
        rewardTable.setForeground(Color.YELLOW);
        rewardTable.setFont(new Font("Arial", Font.PLAIN, 15));
        rewardTable.setRowHeight(25);
        rewardTable.setGridColor(Color.WHITE);
        rewardTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        rewardTable.getTableHeader().setBackground(Color.RED);
        rewardTable.getTableHeader().setForeground(Color.YELLOW);

        for (Reward rwd : reward) {
            
            Object[] rowData = {
                rwd.getReward_id(),
                rwd.getRewardName(),
                rwd.getMinimalPoint()
            };
        
            tableModel.addRow(rowData);
        }

        JScrollPane scrollPane = new JScrollPane(rewardTable);
        scrollPane.getViewport().setBackground(Color.RED);
        scrollPane.setBorder(roundedBorder);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        panel2.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(panel2, BorderLayout.CENTER);

        // Button untuk mengubah promo secara spesifik
        updateReward = new JButton("Update Reward");
        updateReward.setFont(new Font("Arial", Font.BOLD, 16));
        updateReward.setBackground(Color.RED);
        updateReward.setForeground(Color.YELLOW);

        // Button untuk menambahkan promo
        insertReward = new JButton("Insert Reward");
        insertReward.setFont(new Font("Arial", Font.BOLD, 16));
        insertReward.setBackground(Color.RED);
        insertReward.setForeground(Color.YELLOW);

        // Button untuk menghapus promo
        deleteReward = new JButton("Delete Reward");
        deleteReward.setFont(new Font("Arial", Font.BOLD, 16));
        deleteReward.setBackground(Color.RED);
        deleteReward.setForeground(Color.YELLOW);

        // Button untuk kembali ke menu admin
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.YELLOW);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.RED);
        buttonPanel.add(insertReward);
        buttonPanel.add(updateReward);
        buttonPanel.add(deleteReward);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setVisible(true);

        updateReward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new RewardAdminController(new UpdateReward());
            }

        });

        insertReward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new RewardAdminController(new InsertReward());
            }

        });

        deleteReward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new RewardAdminController(new DeleteReward());
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
