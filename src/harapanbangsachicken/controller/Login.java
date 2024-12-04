package harapanbangsachicken.controller;

import harapanbangsachicken.view.*;
import harapanbangsachicken.model.classes.*;

public class Login {
    private LoginView loginView;

    public Login(LoginView loginView) {
        this.loginView = loginView;

        this.loginView.getLoginButton().addActionListener(e -> cekLogin());
        this.loginView.getRegisterButton().addActionListener(e -> showRegisterForm());
    }

    public void cekLogin() {
        String email = loginView.getEmail();
        String pass = loginView.getPassword();
        
        boolean isValid = false;
        
        if (User.cekLogin(email, pass)) {
            isValid = true;
            loginView.showMessage("Login berhasil! Selamat datang, " + email);
            User User = Customer.getData(email); 
            SingletonManager.getInstance().setUser(User);
            System.out.println(User.getNamaDepan());
            loginView.dispose();
            new MenuView();
        }
        if (Admin.Login(email,pass)) {
            isValid = true;
            loginView.showMessage("Login berhasil! Selamat datang, " + email);
            User User = Admin.getData(email); 
            SingletonManager.getInstance().setUser(User);
            System.out.println(User.getNamaDepan());
            loginView.dispose();
            new MenuAdmin();
        }
        
        if (!isValid) {
            loginView.showMessage("Login gagal. Silakan registrasi.");
            showRegisterForm();
        }
    }

    private void showRegisterForm() {
        loginView.dispose();  
        new Register(new RegisterView());  
    }
}
