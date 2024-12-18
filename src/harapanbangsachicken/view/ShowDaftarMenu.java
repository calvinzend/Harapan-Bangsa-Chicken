package harapanbangsachicken.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;

import harapanbangsachicken.model.classes.Drink;
import harapanbangsachicken.model.classes.Keranjang;
import harapanbangsachicken.model.classes.Menu;

public class ShowDaftarMenu extends JFrame {

    private JButton backButton, keranjangButton;
    private ArrayList<Keranjang> keranjang = new ArrayList<>();

    public ShowDaftarMenu(ArrayList<Menu> menu) {
        super("Show Menu");
        System.out.println("masuk");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.RED);

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setOpaque(false);

        JLabel header = new JLabel("Daftar Menu", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.PLAIN, 28));
        header.setForeground(Color.YELLOW);
        panel2.add(header, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel(new GridLayout(2, 0, 10, 10));
        menuPanel.setBackground(Color.RED);

        for (Menu dataMenu : menu) {
            JPanel itemPanel = new JPanel();
            itemPanel.setBackground(Color.RED);
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
            
            JLabel foodLabel = new JLabel(dataMenu.getNama());
            foodLabel.setForeground(Color.YELLOW);
            foodLabel.setBackground(Color.WHITE);
            foodLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            if (dataMenu instanceof Drink) {
                Drink data = (Drink)dataMenu;
                foodLabel.setText(data.getNama() + " - " + data.getSize());
            }

            JLabel foodImage = new JLabel(new ImageIcon(dataMenu.getGambarPath()));
            foodImage.setAlignmentX(Component.CENTER_ALIGNMENT);
            foodImage.setPreferredSize(new Dimension(200, 200));
            
            JLabel foodPriceLabel = new JLabel(String.valueOf(dataMenu.getHarga()));
            foodPriceLabel.setBackground(Color.WHITE);
            foodPriceLabel.setForeground(Color.YELLOW);
            foodPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            JPanel quantityPanel = new JPanel();
            quantityPanel.setBackground(Color.RED);
            quantityPanel.setLayout(new FlowLayout());

            JButton minusButton = new JButton("-");
            JTextField quantityField = new JTextField(3);
            quantityField.setText("0");
            quantityField.setHorizontalAlignment(JTextField.CENTER);
            JButton plusButton = new JButton("+");

            plusButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    quantityField.setText(String.valueOf(Integer.parseInt(quantityField.getText()) + 1));
                    keranjang.add(new Keranjang(dataMenu, Integer.parseInt(quantityField.getText())));
                }
            });
            minusButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
 
                    quantityField.setText(String.valueOf(Integer.parseInt(quantityField.getText()) - 1));

                }
            });

            quantityPanel.add(minusButton);
            quantityPanel.add(quantityField);
            quantityPanel.add(plusButton);

            itemPanel.add(foodLabel);
            itemPanel.add(foodImage);
            itemPanel.add(foodPriceLabel);
            itemPanel.add(quantityPanel);

            menuPanel.add(itemPanel);
        }

        JScrollPane scrollPane = new JScrollPane(menuPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        panel2.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(panel2, BorderLayout.CENTER);

        keranjangButton = new JButton("Keranjang");
        keranjangButton.setFont(new Font("Arial", Font.PLAIN, 16));
        keranjangButton.setBackground(Color.RED);
        keranjangButton.setForeground(Color.YELLOW);

        backButton = new JButton("Kembali");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.YELLOW);
        
        keranjangButton.addActionListener(e -> {
            // new ShowKeranjang(keranjang);
            dispose();
        });
        
        backButton.addActionListener(e -> {
            new ShowMenuView(keranjang);
            dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.RED);
        buttonPanel.add(keranjangButton);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        

        add(mainPanel);
        setVisible(true);
    }
}