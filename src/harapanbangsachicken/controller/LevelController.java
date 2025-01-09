package harapanbangsachicken.controller;

import harapanbangsachicken.model.classes.Customer;
import harapanbangsachicken.model.classes.SingletonManager;
import harapanbangsachicken.model.enums.Level;

public class LevelController {

    public void updateLevel() {
        Customer customer = (Customer) SingletonManager.getInstance().getUser();

        if (customer == null) {
            System.out.println("No customer is logged in.");
            return;
        }

        int points = customer.getPoint();

        Level newLevel = determineLevel(points);


        if (newLevel != customer.getLevel()) {
            customer.setLevel(newLevel);
            Customer.updateCustomerLevel();
            System.out.println("Congratulations! Your level has been updated to: " + newLevel);
        } else {
            System.out.println("Your level remains: " + newLevel);
        }
    }


    private Level determineLevel(int points) {
        if (points >= 2000) {
            return Level.DIAMOND;
        } else if (points >= 1000) {
            return Level.GOLD;
        } else if (points >= 500) {
            return Level.SILVER;
        } else {
            return Level.BRONZE;
        }
    }
}
