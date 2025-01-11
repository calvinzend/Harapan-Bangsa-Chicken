package harapanbangsachicken.view;


import harapanbangsachicken.model.classes.Reward;
import harapanbangsachicken.model.classes.SingletonManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowClaimReward extends JFrame{
    private JPanel mainPanel;
    private JTable rewardTable;
    private DefaultTableModel tableModel;
    private JButton backButton;


    public ShowClaimReward(){

        super("Daftar Reward");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.RED);
    
        String[] columnNames = {"ID Reward", "Nama Reward"};
        tableModel = new DefaultTableModel(columnNames, 0);
        rewardTable = new JTable(tableModel);
        rewardTable.setForeground(Color.YELLOW);
        rewardTable.setFont(new Font("Arial", Font.PLAIN, 16));
        rewardTable.setRowHeight(30);
        rewardTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        rewardTable.getTableHeader().setBackground(Color.YELLOW);
        rewardTable.getTableHeader().setForeground(Color.BLACK);
        rewardTable.setBackground(Color.RED);
        rewardTable.setForeground(Color.YELLOW);
    
        JScrollPane scrollPane = new JScrollPane(rewardTable);
        scrollPane.setBackground(Color.RED);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.getViewport().setBackground(Color.RED);
    
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.YELLOW);
        backButton.setFocusPainted(false);
    
       
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.RED);
        buttonPanel.add(backButton);
    
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuView();
                dispose();
            }
        });
    
    
        loadRewardData();
    
        add(mainPanel);
    
        setVisible(true);
    }
    private void loadRewardData() {
        ArrayList<Reward> rewards = Reward.getClaimedRewardsWithRewardDetails(SingletonManager.getInstance().getUser().getUser_id());
        if (rewards != null) {
            for (Reward reward : rewards) {
                Object[] rowData = {
                    reward.getReward_id(),
                    reward.getRewardName(),
                };
                tableModel.addRow(rowData);
            }
        }
    }
}



