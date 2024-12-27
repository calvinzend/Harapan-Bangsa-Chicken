package harapanbangsachicken.model.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public String getPicture_path() {
        return picture_path;
    }

    public void setPicture_path(String picture_path) {
        this.picture_path = picture_path;
    }

    public static ArrayList<Paket> getData() {
        ArrayList<Paket> paketList = new ArrayList<>();
        String query = "SELECT * FROM paket";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int paket_id = rs.getInt("paket_id");
                    String nama = rs.getString("nama_paket");
                    ArrayList<Menu> menu = Paket.getPaketMenu(paket_id);
                    int harga = rs.getInt("harga");
                    String pic_path = rs.getString("picture_path");

                    paketList.add(new Paket(paket_id, nama, menu, harga, pic_path));
                }
            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return paketList;
    }

    public static ArrayList<Menu> getPaketMenu(int id) {
        ArrayList<Menu> menuList = new ArrayList<>();
        String query = "SELECT * FROM paketmenu where paket_id = ?";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
                    st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int menu_id = rs.getInt("menu_id");
                    menuList.add(Menu.getDataById(menu_id));
                }
            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return menuList;
    }

}
