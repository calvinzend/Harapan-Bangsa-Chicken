package harapanbangsachicken.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import harapanbangsachicken.model.classes.SingletonManager;

public class ProfileView extends JFrame{

    private JPanel frame, panel;
    private JButton back;

    public ProfileView() {
        super("Show Profile");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        frame = new JPanel(new GridBagLayout());
        JLabel profile = new JLabel("<html>"+SingletonManager.getInstance().getUser().showProfile()+"</html>");
        back =  new JButton("Kembali");
        back.setPreferredSize(new Dimension(100, 30));

        frame.add(profile);
        panel.add(frame);
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
