package harapanbangsachicken.view;
import javax.swing.*;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;

import harapanbangsachicken.model.classes.Promo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InsertPromo extends JFrame{
    private JPanel frame, input;
    private JLabel logoLabel, namaPromo, nominalPromo, tanggalPromo;
    private JTextField namaPromoValue, nominalPromoValue;
    private JDateChooser tanggalPromoValue;
    private JButton submitPromo, backButton;
    private Border roundedBorder = BorderFactory.createLineBorder(Color.YELLOW, 2, true);

    public InsertPromo() {
        super("Insert Promo");
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
    
        // label nama promo
        input.add(namaPromo = new JLabel("Promo Name           :"));
        namaPromo.setFont(new Font("Arial", Font.PLAIN, 16));
        namaPromo.setForeground(Color.YELLOW);
    
        // input nama promo
        namaPromoValue = new JTextField(10);
        namaPromoValue.setBorder(roundedBorder);
        namaPromoValue.setPreferredSize(new Dimension(300, 40));
        namaPromoValue.setFont(new Font("Arial", Font.PLAIN, 16));
        namaPromoValue.setBackground(Color.WHITE);
        namaPromoValue.setForeground(Color.BLACK);
        input.add(namaPromoValue);

        // label nominal promo
        input.add(nominalPromo = new JLabel("Promo Nominal        :"));
        nominalPromo.setFont(new Font("Arial", Font.PLAIN, 16));
        nominalPromo.setForeground(Color.YELLOW);
    
        // input nominal promo
        nominalPromoValue = new JTextField(10);
        nominalPromoValue.setBorder(roundedBorder);
        nominalPromoValue.setPreferredSize(new Dimension(300, 40));
        nominalPromoValue.setFont(new Font("Arial", Font.PLAIN, 16));
        nominalPromoValue.setBackground(Color.WHITE);
        nominalPromoValue.setForeground(Color.BLACK);
        input.add(nominalPromoValue);

        // label tanggal promo
        input.add(tanggalPromo = new JLabel("Expired Date           :"));
        tanggalPromo.setFont(new Font("Arial", Font.PLAIN, 16));
        tanggalPromo.setForeground(Color.YELLOW);
    
        // input tanggal promo
        tanggalPromoValue = new JDateChooser();
        tanggalPromoValue.setBorder(roundedBorder);
        tanggalPromoValue.setPreferredSize(new Dimension(300, 40));
        tanggalPromoValue.setFont(new Font("Arial", Font.PLAIN, 16));
        tanggalPromoValue.setBackground(Color.WHITE);
        tanggalPromoValue.setForeground(Color.BLACK);
        input.add(tanggalPromoValue);

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
                dispose();
                ArrayList<Promo> show = Promo.getData();
                new PromoView(show); 
            }
        });
    }

    public String getNamaPromo() {
        return namaPromoValue.getText();
    }

    public String getNominalPromo() {
        return nominalPromoValue.getText();
    }

    public JDateChooser getTanggalPromo() {
        return tanggalPromoValue;
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
