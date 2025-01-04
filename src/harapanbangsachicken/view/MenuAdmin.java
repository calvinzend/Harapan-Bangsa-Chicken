package harapanbangsachicken.view;

import java.awt.*;

import javax.swing.*;

import harapanbangsachicken.controller.Login;
import harapanbangsachicken.model.classes.Ingredient;
import harapanbangsachicken.model.classes.Menu;
import harapanbangsachicken.model.classes.Paket;
import harapanbangsachicken.model.classes.Promo;
import harapanbangsachicken.model.classes.Reward;
import harapanbangsachicken.model.classes.SingletonManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuAdmin extends JFrame{
    private JButton viewTransaksi, viewTotalPendapatan, menu, ingredient, paket, promo, reward, profile, logout;
    private JLabel logoLabel;
    private JPanel panel;

    public MenuAdmin(){
        super("Menu Admin");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));

        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.RED);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // show image icon
        ImageIcon logoIcon = new ImageIcon("src/harapanbangsachicken/view/gambar/mcd.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        logoLabel = new JLabel(logoIcon, SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5; 
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(logoLabel, gbc);
        
        // Header
        JLabel judulAtas = new JLabel("WELCOME ADMIN " + SingletonManager.getInstance().getUser().getNamaDepan().toUpperCase(), SwingConstants.CENTER);
        judulAtas.setFont(new Font("Arial", Font.PLAIN, 28));
        judulAtas.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 5; 
        panel.add(judulAtas, gbc);

        // Header 2
        JLabel judulBawah = new JLabel("Choose Your Option", SwingConstants.CENTER);
        judulBawah.setFont(new Font("Arial", Font.PLAIN, 20));
        judulBawah.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 5; 
        panel.add(judulBawah, gbc);

        // Menu 1 : Show Total Pendapatan
        viewTotalPendapatan = new JButton("Total Income");
        viewTotalPendapatan.setFont(new Font("Arial", Font.PLAIN, 16));
        viewTotalPendapatan.setBackground(Color.RED);
        viewTotalPendapatan.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        panel.add(viewTotalPendapatan, gbc);

        // Menu 2 : Show Checkout
        viewTransaksi= new JButton("Transaction View");
        viewTransaksi.setFont(new Font("Arial", Font.PLAIN, 16));
        viewTransaksi.setBackground(Color.RED);
        viewTransaksi.setForeground(Color.YELLOW);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(viewTransaksi, gbc);

        // Menu 3 : Show List Menu
        menu = new JButton("Edit Menu");
        menu.setFont(new Font("Arial", Font.PLAIN, 16));
        menu.setBackground(Color.RED);
        menu.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(menu, gbc);

        // Menu 4 : Show List Ingredient
        ingredient = new JButton("Edit Ingredient");
        ingredient.setFont(new Font("Arial", Font.PLAIN, 16));
        ingredient.setBackground(Color.RED);
        ingredient.setForeground(Color.YELLOW);

        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(ingredient, gbc);

        // Menu 5 : Show List Paket
        paket = new JButton("Edit Paket");
        paket.setFont(new Font("Arial", Font.PLAIN, 16));
        paket.setBackground(Color.RED);
        paket.setForeground(Color.YELLOW);

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(paket, gbc);

        // Menu 6 : Show List Promo
        promo = new JButton("Edit Promo");
        promo.setFont(new Font("Arial", Font.PLAIN, 16));
        promo.setBackground(Color.RED);
        promo.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(promo, gbc);

        // Menu 7 : Show List Reward
        reward = new JButton("Edit Reward");
        reward.setFont(new Font("Arial", Font.PLAIN, 16));
        reward.setBackground(Color.RED);
        reward.setForeground(Color.YELLOW);

        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(reward, gbc);

        // Menu 8 : Show Profile Admin
        profile = new JButton("Show Profile");
        profile.setFont(new Font("Arial", Font.PLAIN, 16));
        profile.setBackground(Color.RED);
        profile.setForeground(Color.YELLOW);

        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(profile, gbc);

        // Log out Button
        logout = new JButton("Log out");
        logout.setFont(new Font("Arial", Font.PLAIN, 16));
        logout.setBackground(Color.RED);
        logout.setForeground(Color.YELLOW);

        gbc.gridx = 4;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(logout, gbc);
        
        
        add(panel);
        
        setVisible(true);
        
        viewTotalPendapatan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new TotalPendapatanView();
            }

        });

        viewTransaksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new TransaksiView();
            }

        });

        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ArrayList<Menu> show = Menu.getData();
                dispose();
                new ListMenuAdminView(show);
            }

        });

        ingredient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ArrayList<Ingredient> show = Ingredient.getDatas();
                dispose();
                new IngredientView(show);
            }

        });

        paket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ArrayList<Paket> show = Paket.getData();
                dispose();
                new ListPaketAdminView(show);
            }

        });

        promo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ArrayList<Promo> show = Promo.getData();
                dispose();
                new PromoView(show);
            }

        });

        reward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                ArrayList<Reward> show = Reward.getData();
                new RewardView(show);
            }

        });

        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println(SingletonManager.getInstance().getUser().showProfile());
                dispose();
                new AdminProfileView();
            }

        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                SingletonManager.getInstance().clearUser();
                dispose();
                new Login(new LoginView());
            }

        });

    }
}
