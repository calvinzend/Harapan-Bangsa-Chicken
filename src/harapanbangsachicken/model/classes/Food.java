package harapanbangsachicken.model.classes;

import java.util.ArrayList;

public class Food extends Menu {

    public Food(int menu_id, String nama, int harga, ArrayList<Resep> resep) {
        super(menu_id, nama, harga, resep);
    }

}
