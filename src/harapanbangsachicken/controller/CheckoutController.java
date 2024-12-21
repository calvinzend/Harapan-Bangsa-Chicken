package harapanbangsachicken.controller;

import javax.swing.*;

import harapanbangsachicken.model.classes.Customer;


public class CheckoutController {

    public void Konfirmasi(JFrame parentFrame) {
        int response = JOptionPane.showConfirmDialog(
            parentFrame,
            "Apakah Anda yakin ingin melanjutkan ke checkout?",
            "Konfirmasi Checkout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (Customer.Konfirmasi(response)) {
            Pembayaran();
        }
       
    }

    public void Pembayaran(){
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

        Customer.Pembayaran(pilihan);
    }
}
