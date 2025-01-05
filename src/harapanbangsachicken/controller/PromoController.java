package harapanbangsachicken.controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import harapanbangsachicken.model.classes.Promo;
import harapanbangsachicken.model.classes.SingletonManager;
import harapanbangsachicken.view.MenuView;

public class PromoController {
    public static ArrayList<Promo> PromoController(){
        ArrayList<Promo> promo = Promo.getData(SingletonManager.getInstance().getUser().getUser_id()); 

        if (promo == null) {
            JOptionPane.showMessageDialog(null, "Anda belum ada Promo");
            new MenuView();
        }
        return promo;
    }
}
