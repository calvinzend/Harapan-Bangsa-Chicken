package harapanbangsachicken.model.classes;

import java.util.ArrayList;

import harapanbangsachicken.model.enums.Size;

public class Drink extends Menu {
    private Size size;

    public Drink() {

    }

    public Drink(int menu_id, String nama, int harga, ArrayList<Resep> resep, Size size) {
        super(menu_id, nama, harga, resep);
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return super.toString() + "\nSize: " + size;
    }
}
