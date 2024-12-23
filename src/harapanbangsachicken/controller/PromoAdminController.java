package harapanbangsachicken.controller;

import java.sql.Date;

import javax.swing.JOptionPane;

import harapanbangsachicken.model.classes.Promo;
import harapanbangsachicken.view.InsertPromo;
import harapanbangsachicken.view.MenuAdmin;

public class PromoAdminController {
    private InsertPromo view;

    public PromoAdminController(InsertPromo view) {
        this.view = view;
        System.out.println("Form promo ditampilkan");
        this.view.getSubmitPromo().addActionListener(e -> insertPromoAdmin());
    }

    public void insertPromoAdmin() {
        if (view.getNamaPromo().trim().isEmpty() ||
                view.getNominalPromo().trim().isEmpty() ||
                view.getTanggalPromo().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double nominalPromo = Double.parseDouble(view.getNominalPromo());
        try {
            nominalPromo = Double.parseDouble(view.getNominalPromo().trim());
            if (nominalPromo <= 0) {
                JOptionPane.showMessageDialog(null, "Saldo harus lebih dari 0!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Saldo harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String namaPromo = view.getNamaPromo();
        Date tanggalPromo = Date.valueOf(view.getTanggalPromo());

        Promo promo = new Promo(0, namaPromo, nominalPromo, tanggalPromo);

        if (Promo.addPromo(promo)) {
            view.showMessage("Promo berhasil ditambahkan!");
            view.dispose();
            new MenuAdmin();
        } else {
            view.showMessage("Promo gagal ditambahkan! Silakan coba lagi.");
            view.dispose();
        }
    }
}
