package harapanbangsachicken.view;

import javax.swing.*;
import java.awt.*;

import harapanbangsachicken.controller.LevelController;
import harapanbangsachicken.model.classes.Customer;
import harapanbangsachicken.model.classes.SingletonManager;
import harapanbangsachicken.model.enums.Level;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelMenu extends JFrame {

    private JPanel mainPanel;
    private JLabel levelLabel;
    private JLabel rewardCardLabel;
    private JLabel levelText;
    private JLabel pointText;
    private JButton backButton;
    private int points;

    public LevelMenu() {
        super("Daftar Level");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LevelController levelController =  new LevelController();
        levelController.updateLevel();

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.RED);

        levelLabel = new JLabel("McDonald's", JLabel.CENTER);
        levelLabel.setFont(new Font("Arial", Font.BOLD, 60));
        levelLabel.setForeground(Color.YELLOW);
        levelLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        mainPanel.add(levelLabel);


        rewardCardLabel = new JLabel();
        rewardCardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        String rewardImagePath = "/harapanbangsachicken/view/gambar/mcd.png";


        Customer customer = (Customer) SingletonManager.getInstance().getUser();
        Level level = Level.BRONZE; 
        points = 0; 

        if (customer != null) {
            points = customer.getPoint();
            try {
                level = customer.getLevel();
            } catch (IllegalArgumentException e) {
                level = Level.BRONZE;
            }
        }

        switch (level) {
            case GOLD:
                rewardImagePath = "/harapanbangsachicken/view/gambar/member/gold.png";
                levelText = new JLabel("GOLD", JLabel.CENTER);
                break;
            case SILVER:
                rewardImagePath = "/harapanbangsachicken/view/gambar/member/silver.png";
                levelText = new JLabel("SILVER", JLabel.CENTER);
                break;
            case DIAMOND:
                rewardImagePath = "/harapanbangsachicken/view/gambar/member/diamond.png";
                levelText = new JLabel("DIAMOND", JLabel.CENTER);
                break;
            case BRONZE:
                rewardImagePath = "/harapanbangsachicken/view/gambar/member/bronze.png";
                levelText = new JLabel("BRONZE", JLabel.CENTER);
                break;
            default:
                levelText = new JLabel("LEVEL NOT FOUND", JLabel.CENTER);
        }


        ImageIcon rewardCardIcon = new ImageIcon(getClass().getResource(rewardImagePath));
        Image img = rewardCardIcon.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH); 
        rewardCardIcon = new ImageIcon(img);
        rewardCardLabel.setIcon(rewardCardIcon);
        mainPanel.add(rewardCardLabel);


        levelText.setFont(new Font("Arial", Font.BOLD, 40));
        levelText.setForeground(Color.YELLOW);
        levelText.setAlignmentX(Component.CENTER_ALIGNMENT); 
        mainPanel.add(levelText);


        pointText = new JLabel("Point: " + points, JLabel.CENTER);
        pointText.setFont(new Font("Arial", Font.BOLD, 40));
        pointText.setForeground(Color.YELLOW);
        pointText.setAlignmentX(Component.CENTER_ALIGNMENT); 
        mainPanel.add(pointText);

        mainPanel.add(Box.createVerticalGlue());

        // Back Button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.YELLOW);
        backButton.setFocusPainted(false);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT); 
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
        mainPanel.add(backButton);

           backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new MenuView();
                dispose();
            }

        });
        

        // Add Main Panel to Frame
        add(mainPanel);
        setVisible(true);
    }

    
}
