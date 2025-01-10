package harapanbangsachicken.view;

import harapanbangsachicken.model.classes.Reward;
import harapanbangsachicken.model.classes.SingletonManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RewardMenu extends JFrame {
    private JPanel mainPanel;
    private JTable rewardTable;
    private DefaultTableModel tableModel;
    private JButton backButton;
    private JButton claimButton;

    public RewardMenu() {
        super("Daftar Reward");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.RED);

        String[] columnNames = {"ID Reward", "Nama Reward", "Minimal Poin"};
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

        claimButton = new JButton("Claim");
        claimButton.setFont(new Font("Arial", Font.BOLD, 20));
        claimButton.setBackground(Color.RED);
        claimButton.setForeground(Color.YELLOW);
        claimButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.RED);
        buttonPanel.add(backButton);
        buttonPanel.add(claimButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuView();
                dispose();
            }
        });

        claimButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                claimReward();
            }
        });

        loadRewardData();

        add(mainPanel);

        setVisible(true);
    }

    private void loadRewardData() {
        ArrayList<Reward> rewards = Reward.getData();
        if (rewards != null) {
            for (Reward reward : rewards) {
                Object[] rowData = {
                    reward.getReward_id(),
                    reward.getRewardName(),
                    reward.getMinimalPoint()
                };
                tableModel.addRow(rowData);
            }
        }
    }

    private void claimReward() {
        int selectedRow = rewardTable.getSelectedRow();
        if (selectedRow != -1) {
            int rewardId = (int) tableModel.getValueAt(selectedRow, 0);
            String rewardName = (String) tableModel.getValueAt(selectedRow, 1);
            int minimalPoin = (int) tableModel.getValueAt(selectedRow, 2);

            boolean isClaimed = Reward.claimReward(SingletonManager.getInstance().getUser().getUser_id(), rewardId, minimalPoin);
            if (isClaimed) {
                JOptionPane.showMessageDialog(this, "Berhasil klaim reward: " + rewardName, "Klaim Berhasil", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Poin Anda tidak mencukupi.", "Klaim Gagal", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih reward terlebih dahulu.", "Klaim Gagal", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new RewardMenu();
    }
}
