package harapanbangsachicken.model.classes;

import java.util.ArrayList;

public class Food extends Menu {

    public Food(int menu_id, String nama, int harga, String gambarPath, ArrayList<Resep> resep) {
        super(menu_id, nama, harga, gambarPath, resep);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
