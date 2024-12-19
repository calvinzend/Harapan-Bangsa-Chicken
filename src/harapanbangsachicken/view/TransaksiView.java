package harapanbangsachicken.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;

public class TransaksiView extends JFrame{
    private JPanel frame;

    public TransaksiView() {
        super("Menu Admin");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));

        frame = new JPanel(new GridBagLayout());
        frame.setBackground(Color.RED);

        

        add(frame);

        setVisible(true);
    }
}
