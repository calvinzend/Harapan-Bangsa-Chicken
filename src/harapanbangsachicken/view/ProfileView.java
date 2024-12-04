package harapanbangsachicken.view;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import harapanbangsachicken.model.classes.SingletonManager;

public class ProfileView extends JFrame{

    private JPanel frame; 

    public ProfileView() {
        super("Show Profile");
        setSize(800, 700);
        setLocationRelativeTo(null);

        frame = new JPanel(new GridBagLayout());
        JLabel profile = new JLabel("<html>"+SingletonManager.getInstance().getUser().showProfile()+"</html>");

        frame.add(profile);
        add(frame);

        setVisible(true);
    }
}
