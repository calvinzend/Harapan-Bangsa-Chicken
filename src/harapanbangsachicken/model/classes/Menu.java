package harapanbangsachicken.model.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class Menu {
    private int menu_id;
    private String nama;
    private int harga;
    private ArrayList<Resep> resep;

    public Menu() {
    }

    public Menu(int menu_id, String nama, int harga, ArrayList<Resep> resep) {
        this.menu_id = menu_id;
        this.nama = nama;
        this.harga = harga;
        this.resep = resep;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public ArrayList<Resep> getResep() {
        return resep;
    }

    public void setResep(ArrayList<Resep> resep) {
        this.resep = resep;
    }

    public static ArrayList<Menu> getData(String type) {
        ArrayList<Menu> foodList = new ArrayList<>();
        String query = "SELECT * FROM menu WHERE type = ?";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
                st.setString(1, type);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    foodList.add(new Food(
                        rs.getInt("menu_id"),
                        rs.getString("nama"),
                        rs.getInt("harga"),
                        null));
                    }
                }
            } catch (Exception ex) {
                System.out.println("Terjadi kesalahan: " + ex.getMessage());
            }
        return foodList;
    }

    public String toString(){
        return "ID Menu: " + getMenu_id() + "\nNama : " + getNama() + "\nHarga: " + getHarga();
    }
}
