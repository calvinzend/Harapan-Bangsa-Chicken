package harapanbangsachicken.view;
import javax.swing.*;
import javax.swing.border.Border;

import harapanbangsachicken.model.classes.Promo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeletePromo extends JFrame{
    private JPanel frame, input;
    private JLabel logoLabel, idPromo;
    private JTextField idPromoValue;
    private JButton submitPromo, backButton;
    private Border roundedBorder = BorderFactory.createLineBorder(Color.YELLOW, 2, true);

    public DeletePromo() {
        super("Delete Promo");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
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
    
        // label input id promo
        input.add(idPromo = new JLabel("Insert ID Promo that you want to delete :"));
        idPromo.setFont(new Font("Arial", Font.PLAIN, 16));
        idPromo.setForeground(Color.YELLOW);
    
        // input id promo
        idPromoValue = new JTextField(10);
        idPromoValue.setBorder(roundedBorder);
        idPromoValue.setPreferredSize(new Dimension(300, 40));
        idPromoValue.setFont(new Font("Arial", Font.PLAIN, 16));
        idPromoValue.setBackground(Color.WHITE);
        idPromoValue.setForeground(Color.BLACK);
        input.add(idPromoValue);

        // Submit button
        submitPromo = new JButton("Submit");
        submitPromo.setFont(new Font("Arial", Font.BOLD, 16));
        submitPromo.setBackground(Color.RED);
        submitPromo.setForeground(Color.YELLOW);

        // back button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.YELLOW);
        
        input.add(submitPromo);
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
                ArrayList<Promo> show = Promo.getData();
                dispose();
                new PromoView(show); 
            }
        });
    }

    public String getIdPromo() {
        return idPromoValue.getText();
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getSubmitPromo() {
        return submitPromo;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
