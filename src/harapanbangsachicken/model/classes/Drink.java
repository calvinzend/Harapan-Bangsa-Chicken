package harapanbangsachicken.model.classes;

import java.util.ArrayList;

import harapanbangsachicken.model.enums.Size;

public class Drink extends Menu {
    private Size size;

    public Drink() {

    }

    public Drink(int menu_id, String nama, int harga, String gambarPath, ArrayList<Resep> resep, Size size) {
        super(menu_id, nama, harga, gambarPath, resep);
        this.size = size;
    }

    // Constructor drink tanpa resep
    public Drink(int menu_id, String nama, int harga, String gambarPath, Size size) {
        super(menu_id, nama, harga, gambarPath);
        this.size = size;
    }

    // Constructor drink tanpa id dan resep
    public Drink(String nama, int harga, String gambarPath, Size size) {
        super(nama, harga, gambarPath);
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
