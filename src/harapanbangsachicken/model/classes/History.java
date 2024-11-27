package harapanbangsachicken.model.classes;

import java.util.ArrayList;

public class History {
    private ArrayList<Transaction> transaksi;

    public History(ArrayList<Transaction> transaksi) {
        this.transaksi = transaksi;
    }

    public ArrayList<Transaction> getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(ArrayList<Transaction> transaksi) {
        this.transaksi = transaksi;
    }

    @Override
    public String toString() {
        String transaksiMsg = "";

        for (Transaction msg1 : transaksi) {
            transaksiMsg += msg1.toString();
        }
        
        return "Transaksi: " + transaksiMsg;
    }
}
