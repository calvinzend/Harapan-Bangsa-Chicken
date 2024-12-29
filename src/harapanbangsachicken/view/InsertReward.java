package harapanbangsachicken.view;

import javax.swing.*;
import javax.swing.border.Border;

import harapanbangsachicken.model.classes.Reward;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InsertReward extends JFrame{
    private JPanel frame, input;
    private JLabel logoLabel, namaReward, minimalPoinReward;
    private JTextField namaRewardValue, minimalPoinRewardValue;
    private JButton submitReward, backButton;
    private Border roundedBorder = BorderFactory.createLineBorder(Color.YELLOW, 2, true);

    public InsertReward() {
        super("Insert Reward");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));
    
        frame = new JPanel(new GridBagLayout());
        frame.setBackground(Color.RED);
    
        // untuk label dan input
        input = new JPanel(new GridLayout(5, 2, 10, 20));
        input.setOpaque(false);
    
        // set logo MCD
        ImageIcon logoIcon = new ImageIcon("src/harapanbangsachicken/view/gambar/mcd.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        logoLabel = new JLabel(logoIcon);
    
        // label nama reward
        input.add(namaReward = new JLabel("Reward Name                :"));
        namaReward.setFont(new Font("Arial", Font.PLAIN, 16));
        namaReward.setForeground(Color.YELLOW);
    
        // input nama reward
        namaRewardValue = new JTextField(10);
        namaRewardValue.setBorder(roundedBorder);
        namaRewardValue.setPreferredSize(new Dimension(300, 40));
        namaRewardValue.setFont(new Font("Arial", Font.PLAIN, 16));
        namaRewardValue.setBackground(Color.WHITE);
        namaRewardValue.setForeground(Color.BLACK);
        input.add(namaRewardValue);

        // label minimal poin reward
        input.add(minimalPoinReward = new JLabel("Minimal Poin Reward      :"));
        minimalPoinReward.setFont(new Font("Arial", Font.PLAIN, 16));
        minimalPoinReward.setForeground(Color.YELLOW);
    
        // input minimal poin reward
        minimalPoinRewardValue = new JTextField(10);
        minimalPoinRewardValue.setBorder(roundedBorder);
        minimalPoinRewardValue.setPreferredSize(new Dimension(300, 40));
        minimalPoinRewardValue.setFont(new Font("Arial", Font.PLAIN, 16));
        minimalPoinRewardValue.setBackground(Color.WHITE);
        minimalPoinRewardValue.setForeground(Color.BLACK);
        input.add(minimalPoinRewardValue);

        // Submit button
        submitReward = new JButton("Submit");
        submitReward.setFont(new Font("Arial", Font.BOLD, 16));
        submitReward.setBackground(Color.RED);
        submitReward.setForeground(Color.YELLOW);

        // back button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.YELLOW);
        
        input.add(submitReward);
        input.add(backButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);

        gbc.gridx = 0;
        gbc.gridy = 1; 
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(logoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2; 
        gbc.gridwidth = 1;  
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(input, gbc);
         
        add(frame);
 
        setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ArrayList<Reward> show = Reward.getData();
                new RewardView(show); 
            }
        });
    }

    public String getNamaReward() {
        return namaRewardValue.getText();
    }

    public String getMinimalPoinReward() {
        return minimalPoinRewardValue.getText();
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getSubmitReward() {
        return submitReward;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
