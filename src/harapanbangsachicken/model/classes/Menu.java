package harapanbangsachicken.model.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import harapanbangsachicken.model.enums.Size;

public class Menu {
    private int menu_id;
    private String nama;
    private int harga;
    private String gambarPath;
    private ArrayList<Resep> resep;

    public Menu() {
    }

    public Menu(int menu_id, String nama, int harga, String gambarPath, ArrayList<Resep> resep) {
        this.menu_id = menu_id;
        this.nama = nama;
        this.harga = harga;
        this.gambarPath = gambarPath;
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

    public String getGambarPath() {
        return gambarPath;
    }

    public void setGambarPath(String gambarPath) {
        this.gambarPath = gambarPath;
    }

    public ArrayList<Resep> getResep() {
        return resep;
    }

    public void setResep(ArrayList<Resep> resep) {
        this.resep = resep;
    }

    public static ArrayList<Menu> getData(String type) {
        ArrayList<Menu> menuList = new ArrayList<>();
        String query = "SELECT * FROM menu WHERE type = ?";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, type);
            try (ResultSet rs = st.executeQuery()) {
                if (type.equals("Food")) {
                    while (rs.next()) {
                        int menu_id = rs.getInt("menu_id");
                        String nama = rs.getString("nama");
                        int harga = rs.getInt("harga");
                        String pic_path = rs.getString("picture_path");
                        ArrayList<Resep> resep = Resep.getData(menu_id);

                        menuList.add(new Food(menu_id, nama, harga, pic_path, resep));
                    }
                } else if (type.equals("Drink")) {
                    while (rs.next()) {
                        int menu_id = rs.getInt("menu_id");
                        String nama = rs.getString("nama");
                        int harga = rs.getInt("harga");
                        String pic_path = rs.getString("picture_path");
                        ArrayList<Resep> resep = Resep.getData(menu_id);
                        Size size = Size.valueOf(rs.getString("size"));

                        menuList.add(new Drink(menu_id, nama, harga, pic_path, resep, size));
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return menuList;
    }

    public static Menu getDataById(int id) {
        Menu m = null;
        String query = "SELECT * FROM menu WHERE menu_id = ?";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int menu_id = rs.getInt("menu_id");
                    String nama = rs.getString("nama");
                    int harga = rs.getInt("harga");
                    String pic_path = rs.getString("picture_path");
                    ArrayList<Resep> resep = Resep.getData(menu_id);

                    m = new Food(menu_id, nama, harga, pic_path, resep);
                }
            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return m;
    }

    public String toString() {
        return "ID Menu: " + getMenu_id() + "\nNama : " + getNama() + "\nHarga: " + getHarga();
    }
}
