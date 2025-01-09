package harapanbangsachicken.model.classes;

import java.util.ArrayList;

public class CheckoutResult {
    private ArrayList<Keranjang> keranjang;
    private ArrayList<Promo> promos;
    private double harga;
    private static CheckoutResult instance;

    
    private CheckoutResult(ArrayList<Keranjang> keranjang, ArrayList<Promo> promos, double harga) {
        this.keranjang = keranjang;
        this.promos = promos;
        this.harga = harga;
    }

    public static CheckoutResult getInstance() {
        return instance;
    }

    public static void initializeInstance(ArrayList<Keranjang> keranjang, ArrayList<Promo> promos, double harga) {
        if (instance == null) {
            instance = new CheckoutResult(keranjang, promos, harga);
        } else {
            instance.setKeranjang(keranjang);
            instance.setPromos(promos);
            instance.setHarga(harga);
        }
    }

    public ArrayList<Keranjang> getKeranjang() {
        return keranjang;
    }

    public ArrayList<Promo> getPromos() {
        return promos;
    }

    public double getHarga() {
        return harga;
    }

    public void setKeranjang(ArrayList<Keranjang> keranjang) {
        this.keranjang = keranjang;
    }

    public void setPromos(ArrayList<Promo> promos) {
        this.promos = promos;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
}
