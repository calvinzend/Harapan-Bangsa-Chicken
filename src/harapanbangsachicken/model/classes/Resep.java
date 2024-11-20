package harapanbangsachicken.model.classes;

import java.util.ArrayList;

public class Resep {
    private ArrayList<Ingredient> bahan;
    private int quantity;
    private String satuan;

    public Resep() {
    }

    public Resep(ArrayList<Ingredient> bahan, int quantity, String satuan) {
        this.bahan = bahan;
        this.quantity = quantity;
        this.satuan = satuan;
    }

    public ArrayList<Ingredient> getBahan() {
        return bahan;
    }

    public void setBahan(ArrayList<Ingredient> bahan) {
        this.bahan = bahan;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

}
