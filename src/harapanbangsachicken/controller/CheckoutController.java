package harapanbangsachicken.controller;

import javax.swing.*;
import harapanbangsachicken.model.classes.*;
import harapanbangsachicken.view.InfoPembayaran;

import java.util.Date;
import java.util.ArrayList;

public class CheckoutController {

    public boolean Konfirmasi(JFrame parentFrame) {
        int response = JOptionPane.showConfirmDialog(
            parentFrame,
            "Apakah Anda yakin ingin melanjutkan ke checkout?",
            "Konfirmasi Checkout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (Customer.Konfirmasi(response)) {
            return Pembayaran();
        }

        return false;
    }

    public boolean Pembayaran() {
        String[] options = {"Saldo User", "Kartu (Bank)"};

        int pilihan = JOptionPane.showOptionDialog(
            null,
            "Pilih metode pembayaran:",
            "Metode Pembayaran",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        boolean berhasilDibayar = Customer.Pembayaran(pilihan);

        if (berhasilDibayar) {
            CheckoutResult checkoutResult = CheckoutResult.getInstance();
            ArrayList<Keranjang> keranjangList = checkoutResult.getKeranjang();
            ArrayList<Promo> promoList = checkoutResult.getPromos();
            double harga = checkoutResult.getHarga();

            ArrayList<Menu> menus = new ArrayList<>();
            for (Keranjang keranjang : keranjangList) {
                menus.add(keranjang.getMenu());
            }
            

            Transaction transaction = new E_Money();
            transaction.setPemesan(SingletonManager.getInstance().getUser());
            transaction.setTanggalPembelian(new java.sql.Date(new Date().getTime()));
            transaction.setListMenu(menus);
            transaction.setPotonganPromo(totalPotonganPromo(promoList));
            transaction.setHargaTotal(harga);

            boolean berhasilDisimpan = Transaction.insertTransaction(transaction, keranjangList, promoList);

            if (berhasilDisimpan) {
                    JOptionPane.showMessageDialog(null, "Pembayaran berhasil, data transaksi tersimpan, dan history tercatat.");
                   return true;
            } else {
                JOptionPane.showMessageDialog(null, "Pembayaran gagal. Silakan coba lagi.");
                return false;
            }
        }
        return false;
    }

    private double totalPotonganPromo(ArrayList<Promo> promos) {
        double total = 0;
        for (Promo promo : promos) {
            total += promo.getNominalPromo();
        }
        return total;
    }
   
}
