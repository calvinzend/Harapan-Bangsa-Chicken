package harapanbangsachicken.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import harapanbangsachicken.model.classes.Promo;
import harapanbangsachicken.view.DeletePromo;
import harapanbangsachicken.view.InsertPromo;
import harapanbangsachicken.view.PromoView;
import harapanbangsachicken.view.UpdatePromo;

public class PromoAdminController {
    private InsertPromo view;
    private DeletePromo delete;
    private UpdatePromo update;

    public PromoAdminController(InsertPromo view) {
        this.view = view;
        System.out.println("Form promo ditampilkan");
        this.view.getSubmitPromo().addActionListener(e -> insertPromoAdmin());
    }

    public PromoAdminController(DeletePromo delete) {
        this.delete = delete;
        System.out.println("Form promo ditampilkan");
        this.delete.getSubmitPromo().addActionListener(e -> deletePromoAdmin());
    }

    public PromoAdminController(UpdatePromo update) {
        this.update = update;
        System.out.println("Form promo ditampilkan");
        this.update.getSubmitPromo().addActionListener(e -> updatePromoAdmin());
    }

    public void insertPromoAdmin() {
        if (view.getNamaPromo().trim().isEmpty() ||
                view.getNominalPromo().trim().isEmpty() ||
                view.getTanggalPromo().getDate() == null) {

            JOptionPane.showMessageDialog(null, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double nominalPromo = Double.parseDouble(view.getNominalPromo().trim());
            if (nominalPromo <= 0) {
                JOptionPane.showMessageDialog(null, "Saldo harus lebih dari 0!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            java.util.Date tanggalPromoRaw = view.getTanggalPromo().getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
            String formattedTanggalPromo = sdf.format(tanggalPromoRaw);
    
            java.sql.Date tanggalPromo = java.sql.Date.valueOf(formattedTanggalPromo);

            String namaPromo = view.getNamaPromo();
    
            Promo promo = new Promo(0, namaPromo, nominalPromo, tanggalPromo);
    
            if (Promo.addPromo(promo)) {
                view.showMessage("Promo berhasil ditambahkan!");
                view.dispose();
                ArrayList<Promo> show = Promo.getData();
                new PromoView(show);
            } else {
                view.showMessage("Promo gagal ditambahkan! Silakan coba lagi.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public void deletePromoAdmin() {
        if (delete.getIdPromo().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID Promo harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int idPromo = Integer.parseInt(delete.getIdPromo().trim());
    
            if (Promo.deletePromo(idPromo)) {
                delete.showMessage("Promo berhasil dihapus!");
                delete.dispose();
                ArrayList<Promo> show = Promo.getData();
                new PromoView(show);
            } else {
                delete.showMessage("Promo gagal dihapus! Silakan coba lagi.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public void updatePromoAdmin() {
        if (update.getNamaPromo().trim().isEmpty() ||
                update.getNominalPromo().trim().isEmpty() ||
                update.getTanggalPromo().getDate() == null ||
                update.getIdPromo().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double nominalPromo = Double.parseDouble(update.getNominalPromo().trim());
            if (nominalPromo <= 0) {
                JOptionPane.showMessageDialog(null, "Saldo harus lebih dari 0!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            java.util.Date tanggalPromoRaw = update.getTanggalPromo().getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
            String formattedTanggalPromo = sdf.format(tanggalPromoRaw);
    
            java.sql.Date tanggalPromo = java.sql.Date.valueOf(formattedTanggalPromo);

            String namaPromo = update.getNamaPromo();

            int idPromo = Integer.parseInt(update.getIdPromo().trim());
    
            Promo promo = new Promo(idPromo, namaPromo, nominalPromo, tanggalPromo);
    
            if (Promo.updatePromo(promo)) {
                update.showMessage("Promo berhasil diupdate!");
                update.dispose();
                ArrayList<Promo> show = Promo.getData();
                new PromoView(show);
            } else {
                update.showMessage("Promo gagal diupdate! Silakan coba lagi.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
}
