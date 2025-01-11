package harapanbangsachicken.main;

import java.util.ArrayList;

import harapanbangsachicken.controller.*;
import harapanbangsachicken.model.classes.Promo;
import harapanbangsachicken.view.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<Promo> promoList = Promo.getData();
        Promo.updatePromoExpiration(promoList);
        
        LoginView loginView = new LoginView();
        new Login(loginView);
    }
}
