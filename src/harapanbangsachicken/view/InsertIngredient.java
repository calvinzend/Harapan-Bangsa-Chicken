package harapanbangsachicken.view;
import javax.swing.*;
import javax.swing.border.Border;

import harapanbangsachicken.model.classes.Ingredient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InsertIngredient extends JFrame{
    private JPanel frame, input;
    private JLabel logoLabel, namaIngredient, stock, satuan;
    private JTextField namaIngredientValue, stockValue, satuanValue;
    private JButton submitIngredient, backButton;
    private Border roundedBorder = BorderFactory.createLineBorder(Color.YELLOW, 2, true);

    public InsertIngredient() {
        super("Insert Ingredient");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));
    
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
        input.add(namaIngredient = new JLabel("Ingredient Name           :"));
        namaIngredient.setFont(new Font("Arial", Font.PLAIN, 16));
        namaIngredient.setForeground(Color.YELLOW);
    
        // input nama promo
        namaIngredientValue = new JTextField(10);
        namaIngredientValue.setBorder(roundedBorder);
        namaIngredientValue.setPreferredSize(new Dimension(300, 40));
        namaIngredientValue.setFont(new Font("Arial", Font.PLAIN, 16));
        namaIngredientValue.setBackground(Color.WHITE);
        namaIngredientValue.setForeground(Color.BLACK);
        input.add(namaIngredientValue);

        // label nominal promo
        input.add(stock = new JLabel("Ingredient Stock           :"));
        stock.setFont(new Font("Arial", Font.PLAIN, 16));
        stock.setForeground(Color.YELLOW);
    
        // input nominal promo
        stockValue = new JTextField(10);
        stockValue.setBorder(roundedBorder);
        stockValue.setPreferredSize(new Dimension(300, 40));
        stockValue.setFont(new Font("Arial", Font.PLAIN, 16));
        stockValue.setBackground(Color.WHITE);
        stockValue.setForeground(Color.BLACK);
        input.add(stockValue);

        // label tanggal promo
        input.add(satuan = new JLabel("Ingredient Unit              :"));
        satuan.setFont(new Font("Arial", Font.PLAIN, 16));
        satuan.setForeground(Color.YELLOW);
    
        // input tanggal promo
        satuanValue = new JTextField();
        satuanValue.setBorder(roundedBorder);
        satuanValue.setPreferredSize(new Dimension(300, 40));
        satuanValue.setFont(new Font("Arial", Font.PLAIN, 16));
        satuanValue.setBackground(Color.WHITE);
        satuanValue.setForeground(Color.BLACK);
        input.add(satuanValue);

        // Submit button
        submitIngredient = new JButton("Submit");
        submitIngredient.setFont(new Font("Arial", Font.BOLD, 16));
        submitIngredient.setBackground(Color.RED);
        submitIngredient.setForeground(Color.YELLOW);

        // back button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.YELLOW);
        
        input.add(submitIngredient);
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
                ArrayList<Ingredient> show = Ingredient.getDatas();
                new IngredientView(show); 
            }
        });
    }

    public String getNamaIngredient() {
        return namaIngredientValue.getText();
    }

    public String getStock() {
        return stockValue.getText();
    }

    public String getSatuan() {
        return satuanValue.getText();
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getSubmitIngredient() {
        return submitIngredient;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
