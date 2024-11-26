package harapanbangsachicken.controller;

import harapanbangsachicken.view.RegisterView;
import harapanbangsachicken.view.LoginView;

public class Register {
    private RegisterView view;

    public Register(RegisterView view) {
        this.view = view;
        System.out.println("Form registrasi ditampilkan");
        this.view.getRegisterButton().addActionListener(e -> register());
    }

    public void register() {
        String namaDepan = view.getNamaDepan();
        String namaBelakang = view.getNamaBelakang();
        String email = view.getEmail();
        String password = view.getPassword();
        String noTelp = view.getNoTelp();

        System.out.println("Nama: " + namaDepan + " " + namaBelakang);
        System.out.println("Email: " + email);
        System.out.println("No Telp: " + noTelp);


        view.showMessage("Registrasi berhasil! Silakan login.");
        view.dispose();  
        new Login(new LoginView());  
    }
}
