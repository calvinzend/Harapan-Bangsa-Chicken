package harapanbangsachicken.view;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import harapanbangsachicken.model.classes.Menu;

public class ShowDaftarMenu extends JFrame {

    private JButton back;

    public ShowDaftarMenu(ArrayList<Menu> menu) {
        super("Show Menu");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));

        JPanel panel = new JPanel(new GridLayout());
        panel.setBackground(Color.RED);

        JPanel panel2 = new JPanel(new GridLayout(3, 1, 15, 10));
        panel2.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel header = new JLabel("Daftar Menu", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.PLAIN, 28));
        header.setForeground(Color.YELLOW);
 
        panel2.add(header);

        JPanel menuPanel = new JPanel();
        menuPanel.setFont(new Font("Arial", Font.PLAIN, 20));
        menuPanel.setBackground(Color.RED);
        menuPanel.setForeground(Color.YELLOW);

        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        for (Menu item : menu) {
            JLabel menuItem = new JLabel(item.toString());
            menuItem.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            menuPanel.add(menuItem);
        }

        JScrollPane scrollPane = new JScrollPane(menuPanel);

        panel2.add(scrollPane);

        back = new JButton("Kembali");
        back.setFont(new Font("Arial", Font.PLAIN, 16));
        back.setBackground(Color.RED);
        back.setForeground(Color.YELLOW);

        panel2.add(back);

        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(panel2, gbc);

        back.addActionListener(e -> {
            new ShowMenuView();
            dispose();
        });

        add(panel);
        setVisible(true);
    }
}
