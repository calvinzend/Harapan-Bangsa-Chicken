package harapanbangsachicken.model.classes;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Card extends Transaction {
    private ArrayList<Bank> banks = Bank.getBank();

    public Card() {
        this.banks = Bank.getBank();
    }

    @Override
    public boolean prosesPembayaran() {

        if (banks == null) {
            JOptionPane.showMessageDialog(
                null,
                "Bank not initialized!",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        String noATM = JOptionPane.showInputDialog("Input No ATM");
        String pass = JOptionPane.showInputDialog("Input Password");

        for (Bank bank : banks) {
            if (noATM != null && noATM.equals(bank.getNoATM())) {
                if (pass != null && pass.equals(bank.getPassATM())) {
                    double totalHarga = UpdateKeranjang.getInstance().getTotalHarga();
                    Bank.updateBankPayment(noATM, totalHarga);
                  
                    JOptionPane.showMessageDialog(
                        null,
                        "Pembayaran berhasil menggunakan kartu bank! \nSisa Saldo :" +  (bank.getSaldo()-totalHarga),
                        "Sukses",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    return true;  
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Password ATM tidak valid!",
                        "Kesalahan",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return false; 
                }
            }
        }
        JOptionPane.showMessageDialog(
                    null,
                    "No ATM tidak valid!",
                    "Kesalahan",
                    JOptionPane.ERROR_MESSAGE
                );
        return false;
    }

    
}
