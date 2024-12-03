package harapanbangsachicken.controller;

import harapanbangsachicken.view.RegisterView;


import harapanbangsachicken.model.classes.Customer;
import harapanbangsachicken.model.enums.Level;
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
        String alamat = view.getAlamat();
        String gender = view.getGender();
        double saldo = Double.parseDouble(view.getSaldo());
        int poin = 0;
        Level level = Level.BRONZE;
    
        Customer customer = new Customer(0, namaDepan, namaBelakang, password, email, noTelp, alamat, gender, saldo, null, null, poin, level, null);
    
        if (Customer.Register(customer)) {
            view.showMessage("Registrasi berhasil! Silakan login.");
            view.dispose();  
            new Login(new LoginView());  
        } else {
            view.showMessage("Registrasi gagal! Silakan coba lagi.");
        }
    }
}
