package harapanbangsachicken.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

import harapanbangsachicken.model.classes.SingletonManager;

public class ProfileView extends JFrame{

    private JPanel frame, panel;
    private JLabel logoLabel;
    private JButton back;
    private Border roundedBorderButton = BorderFactory.createLineBorder(Color.BLACK, 2, true);

    public ProfileView() {
        super("Show Profile");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.RED);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        frame = new JPanel(new GridBagLayout());
        frame.setBackground(Color.RED);

        ImageIcon logoIcon = new ImageIcon("src/harapanbangsachicken/view/gambar/mcd.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        logoLabel = new JLabel(logoIcon);

        JLabel profile = new JLabel("<html>"+SingletonManager.getInstance().getUser().showProfile()+"</html>");
        profile.setFont(new Font("Arial", Font.PLAIN, 30));
        profile.setBackground(Color.RED);
        profile.setForeground(Color.YELLOW);

        back =  new JButton("Kembali");
        back.setBorder(roundedBorderButton);
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.setBackground(Color.RED);
        back.setForeground(Color.YELLOW);

        frame.add(profile);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(logoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1; 
        gbc.gridwidth = 1;  
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(frame, gbc);
        
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(back);

        add(panel);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new MenuView();
            }
        });

        setVisible(true);
    }
}
