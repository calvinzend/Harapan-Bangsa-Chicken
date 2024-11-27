package harapanbangsachicken.model.classes;

import java.sql.Date;
import java.util.ArrayList;

public class Transaction {
    private int transaction_id;
    private ArrayList<Menu> listMenu;
    private User pemesan;
    private Date tanggalPembelian;
    private double potonganPromo;
    private double hargaTotal;

    public Transaction() {
    }

    public Transaction(int transaction_id, ArrayList<Menu> listMenu, User pemesan, Date tanggalPembelian,
            double potonganPromo, double hargaTotal) {
        this.transaction_id = transaction_id;
        this.listMenu = listMenu;
        this.pemesan = pemesan;
        this.tanggalPembelian = tanggalPembelian;
        this.potonganPromo = potonganPromo;
        this.hargaTotal = hargaTotal;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public ArrayList<Menu> getListMenu() {
        return listMenu;
    }

    public void setListMenu(ArrayList<Menu> listMenu) {
        this.listMenu = listMenu;
    }

    public User getPemesan() {
        return pemesan;
    }

    public void setPemesan(User pemesan) {
        this.pemesan = pemesan;
    }

    public Date getTanggalPembelian() {
        return tanggalPembelian;
    }

    public void setTanggalPembelian(Date tanggalPembelian) {
        this.tanggalPembelian = tanggalPembelian;
    }

    public double getPotonganPromo() {
        return potonganPromo;
    }

    public void setPotonganPromo(double potonganPromo) {
        this.potonganPromo = potonganPromo;
    }

    public double getHargaTotal() {
        return hargaTotal;
    }

    public void setHargaTotal(double hargaTotal) {
        this.hargaTotal = hargaTotal;
    }

    @Override
    public String toString() {
        String menu = "";

        for (Menu msg1 : listMenu) {
            menu += msg1.toString();
        }

        return "\nID Transaction: " + transaction_id + "\nMenu: " + menu + "\nPemesan: " + pemesan + "\nTanggal Pembelian: " + tanggalPembelian + "\nPotongan Promo: " + potonganPromo + "\nHarga Total: " + hargaTotal;
    }
}
