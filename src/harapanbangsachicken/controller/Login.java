package harapanbangsachicken.controller;

import harapanbangsachicken.view.*;

import java.util.ArrayList;

import harapanbangsachicken.model.classes.*;

public class Login {
    private LoginView loginView;
    private Admin dataAdmin;

    public Login(LoginView loginView) {
        this.loginView = loginView;
        this.loginView.getLoginButton().addActionListener(e -> cekLogin());
    }
    public void cekLogin(){

        String user = loginView.getUsername();
        String pass = loginView.getPassword();

        ArrayList<Admin> dataAdmin = new ArrayList<>();
       dataAdmin.add(new  Admin(1, "Calvin", "Zendrato", "1234", "calvin", "081234567890")); 
       dataAdmin.add(new Admin(2, "John", "Doe", "1234", "john", "081987654321"));

       boolean isValid = false;
       for (Admin admin : dataAdmin) {
           if (admin.cekLogin(user,pass)) {
            isValid =  true;
            loginView.showMessage("Login berhasil! Selamat datang, " + admin.getNamaDepan());
           }
       }

       if (!isValid) {
        loginView.showMessage("Gagal");
       }
    }

}
