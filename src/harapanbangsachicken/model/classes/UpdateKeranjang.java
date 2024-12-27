package harapanbangsachicken.model.classes;

import java.util.ArrayList;

public class UpdateKeranjang {
    private static UpdateKeranjang instance;
    private ArrayList<Keranjang> keranjang;

    UpdateKeranjang() {
        keranjang = new ArrayList<>();
    }

    public static UpdateKeranjang getInstance() {
        if (instance == null) {
            instance = new UpdateKeranjang();
        }
        return instance;
    }

    public ArrayList<Keranjang> getKeranjang() {
        return keranjang;
    }

    public void setKeranjang(ArrayList<Keranjang> keranjang) {
        this.keranjang = keranjang;
    }

    public void addKeranjang(Keranjang item) {
        if (keranjang == null) {
            keranjang = new ArrayList<>();
        }

        String namaItem = "";
        if (item.getMenu() != null) {
            namaItem = item.getMenu().getNama();
        } else {
            namaItem = item.getPaket().getNamaPaket();
        }
        System.out.println("Adding: " + namaItem);
        boolean found = false;
        for (Keranjang k : keranjang) {
            if (k.getMenu() != null) {
                if (k.getMenu().equals(item.getMenu())) {
                    k.setJumlah(item.getJumlah());
                    found = true;
                    System.out.println("Updated quantity: " + k.getJumlah());
                    break;
                }
            } else {
                if (k.getPaket().equals(item.getPaket())) {
                    k.setJumlah(item.getJumlah());
                    found = true;
                    System.out.println("Updated quantity: " + k.getJumlah());
                    break;
                }
            }

        }

        if (!found) {
            keranjang.add(item);
            System.out.println("Added new item.");
        }
    }

    public double getTotalHarga() {
        double totalHarga = 0;
        for (Keranjang item : keranjang) {
            if (item.getMenu() != null) {
                totalHarga += item.getJumlah() * item.getMenu().getHarga();
            } else {
                totalHarga += item.getJumlah() * item.getPaket().getHarga();
            }
        }
        return totalHarga;
    }

    public void clearKeranjang() {
        keranjang.clear();
    }
}
