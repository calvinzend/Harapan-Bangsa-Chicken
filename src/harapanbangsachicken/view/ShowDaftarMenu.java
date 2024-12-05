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

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel header = new JLabel("Daftar Menu", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(header);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        for (Menu item : menu) {
            JLabel menuItem = new JLabel(item.toString());
            menuItem.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            menuPanel.add(menuItem);
        }
        JScrollPane scrollPane = new JScrollPane(menuPanel);
        panel.add(scrollPane);

        back = new JButton("Kembali");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.addActionListener(e -> {
            new ShowMenuView();
            dispose();
        });
        panel.add(Box.createRigidArea(new Dimension(0, 10))); 
        panel.add(back);

        add(panel);
        setVisible(true);
    }
}
