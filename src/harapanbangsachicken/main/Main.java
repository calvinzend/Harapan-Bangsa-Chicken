package harapanbangsachicken.main;

import harapanbangsachicken.controller.*;
import harapanbangsachicken.view.*;

public class Main {
    public static void main(String[] args) {    
        LoginView loginView = new LoginView();
        new Login(loginView);
    }
}
