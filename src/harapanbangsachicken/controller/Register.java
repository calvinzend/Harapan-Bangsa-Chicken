package harapanbangsachicken.controller;

import harapanbangsachicken.view.RegisterView;

import javax.swing.JOptionPane;

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
        if (view.getNamaDepan().trim().isEmpty() ||
                view.getNamaBelakang().trim().isEmpty() ||
                view.getEmail().trim().isEmpty() ||
                view.getPassword().trim().isEmpty() ||
                view.getNoTelp().trim().isEmpty() ||
                view.getAlamat().trim().isEmpty() ||
                view.getSaldo().trim().isEmpty() ||
                (!view.getGender().equals("Pria") && !view.getGender().equals("Wanita"))) {

            JOptionPane.showMessageDialog(null, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double saldo = Double.parseDouble(view.getSaldo());
        try {
            saldo = Double.parseDouble(view.getSaldo().trim());
            if (saldo <= 0) {
                JOptionPane.showMessageDialog(null, "Saldo harus lebih dari 0!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Saldo harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String namaDepan = view.getNamaDepan();
        String namaBelakang = view.getNamaBelakang();
        String email = view.getEmail();
        String password = view.getPassword();
        String noTelp = view.getNoTelp();
        String alamat = view.getAlamat();
        String gender = view.getGender();
        
        int poin = 0;
        Level level = Level.BRONZE;

        Customer customer = new Customer(0, namaDepan, namaBelakang, password, email, noTelp, alamat, gender, saldo,
                null, null, poin, level, null);

        if (Customer.Register(customer)) {
            view.showMessage("Registrasi berhasil! Silakan login.");
            view.dispose();
            new Login(new LoginView());
        } else {
            view.showMessage("Registrasi gagal! Silakan coba lagi.");
        }
    }
}
