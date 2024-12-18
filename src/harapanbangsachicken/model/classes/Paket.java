package harapanbangsachicken.model.classes;

import java.util.ArrayList;

public class Paket {
    private int paket_id;
    private String namaPaket;
    private ArrayList<Menu> menu;
    private int harga;
    private String picture_path;

    public Paket() {
    }

    public Paket(int paket_id, String namaPaket, ArrayList<Menu> menu, int harga, String picture_path) {
        this.paket_id = paket_id;
        this.namaPaket = namaPaket;
        this.menu = menu;
        this.harga = harga;
        this.picture_path = picture_path;
    }

    public int getPaket_id() {
        return paket_id;
    }

    public void setPaket_id(int paket_id) {
        this.paket_id = paket_id;
    }

    public String getNamaPaket() {
        return namaPaket;
    }

    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }

    public ArrayList<Menu> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<Menu> menu) {
        this.menu = menu;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

}
