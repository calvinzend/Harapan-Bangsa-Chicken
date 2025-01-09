package harapanbangsachicken.controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import harapanbangsachicken.model.classes.Promo;
import harapanbangsachicken.model.classes.SingletonManager;

public class PromoController {

    public static ArrayList<Promo> getAvailablePromos() {
        ArrayList<Promo> promos = Promo.getData(SingletonManager.getInstance().getUser().getUser_id());

        if (promos == null || promos.isEmpty()) {
            JOptionPane.showMessageDialog(
                null,
                "Anda belum memiliki promo yang tersedia.",
                "Info Promo",
                JOptionPane.INFORMATION_MESSAGE
            );
            return new ArrayList<>(); 
        }

        return promos;
    }
}
