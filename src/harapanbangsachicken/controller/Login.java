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
        String user = loginView.getUsername();
        String pass = loginView.getPassword();
        

        boolean isValid = false;
        
        if (User.cekLogin(user, pass)) {
            isValid = true;
            loginView.showMessage("Login berhasil! Selamat datang, " + user);
            return;
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
