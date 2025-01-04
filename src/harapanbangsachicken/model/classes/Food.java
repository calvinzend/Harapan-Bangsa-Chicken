package harapanbangsachicken.model.classes;

import java.util.ArrayList;

public class Food extends Menu {

    public Food(int menu_id, String nama, int harga, String gambarPath, ArrayList<Resep> resep) {
        super(menu_id, nama, harga, gambarPath, resep);
    }

        // Constructor Food tanpa id dan resep
    public Food(String nama, int harga, String gambarPath) {
        super(nama, harga, gambarPath);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
