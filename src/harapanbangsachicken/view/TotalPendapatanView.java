package harapanbangsachicken.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TotalPendapatanView extends JFrame{
    private JPanel frame;
    private JLabel logoLabel, showPendapatan;
    private JButton backButton;
    private GridBagConstraints gbc;

    public TotalPendapatanView() {
        super("Total Pendapatan Harapan Bangsa Chicken");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));

        frame = new JPanel(new GridBagLayout());
        frame.setBackground(Color.RED);

        // show image icon
        ImageIcon logoIcon = new ImageIcon("src/harapanbangsachicken/view/gambar/mcd.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        logoLabel = new JLabel(logoIcon, SwingConstants.CENTER);

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        frame.add(logoLabel, gbc);

        // JLabel untuk Show Total Pendapatan
        showPendapatan = new JLabel("-- Total Income of Harapan Bangsa Chicken --");
        showPendapatan.setFont(new Font("Arial", Font.PLAIN, 28));
        showPendapatan.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 5;
        frame.add(showPendapatan, gbc);


        backButton = new JButton("Kembali");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        frame.add(backButton, gbc);

        add(frame);

        setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuAdmin();
            }
        });
    }
}
