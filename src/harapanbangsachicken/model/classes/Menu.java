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

    public Menu(int menu_id, String nama, int harga, String gambarPath) {
        this.menu_id = menu_id;
        this.nama = nama;
        this.harga = harga;
        this.gambarPath = gambarPath;
    }

    public Menu(String nama, int harga, String gambarPath) {
        this.nama = nama;
        this.harga = harga;
        this.gambarPath = gambarPath;
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

    public static ArrayList<Menu> getData() {
        ArrayList<Menu> menuList = new ArrayList<>();
        String query = "SELECT * FROM menu";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    String type = rs.getString("type");
                    int menu_id = rs.getInt("menu_id");
                    String nama = rs.getString("nama");
                    int harga = rs.getInt("harga");
                    String pic_path = rs.getString("picture_path");
                    ArrayList<Resep> resep = Resep.getData(menu_id);

                    if (type.equals("Food")) {
                        menuList.add(new Food(menu_id, nama, harga, pic_path, resep));
                    } else {
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
                    String type = rs.getString("type");
                    String pic_path = rs.getString("picture_path");
                    ArrayList<Resep> resep = Resep.getData(menu_id);

                    if (type.equals("Food")) {
                        m = new Food(menu_id, nama, harga, pic_path, resep);
                    } else {
                        Size size = Size.valueOf(rs.getString("size"));
                        m = new Drink(menu_id, nama, harga, pic_path, resep, size);
                    }
                    return m;
                }
            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return m;
    }

    // Return integer dibutuhkan untuk pengecekan jika menu terhubung ke paket
    // tertentu
    // 0 = Berhasil
    // 1 = gagal delete
    // 2 = gagal delete karena menu terhubung ke paket tertentu

    public static int deleteData(int id) {
        String query = "DELETE FROM menu WHERE menu_id = ?";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, id);
            int affectedRows = st.executeUpdate();
            if (affectedRows > 0) {
                return 0;
            } else {
                return 1;
            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
            return 2;
        }
    }

    public static boolean updateData(Menu updatedMenu) {
        String query = "UPDATE menu SET nama = ?, harga = ?, picture_path = ?, size = ? WHERE menu_id = ?";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {

            String size = null;
            if (updatedMenu instanceof Drink) {
                Drink d = (Drink) updatedMenu;
                size = String.valueOf(d.getSize());
            }

            st.setString(1, updatedMenu.getNama());
            st.setInt(2, updatedMenu.getHarga());
            st.setString(3, updatedMenu.getGambarPath());
            st.setString(4, size);
            st.setInt(5, updatedMenu.getMenu_id());

            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return false;
    }

    public static Integer insertData(Menu menu) {
        String query = "INSERT INTO menu(nama, harga, picture_path, type, size) VALUES(?, ?, ?, ?, ?)";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            String size = null;
            String type;

            if (menu instanceof Drink) {
                Drink d = (Drink) menu;
                size = String.valueOf(d.getSize());
                type = "Drink";
            } else if (menu instanceof Food) {
                type = "Food";
            } else {
                throw new IllegalArgumentException("Unknown menu type: " + menu.getClass().getSimpleName());
            }

            st.setString(1, menu.getNama());
            st.setInt(2, menu.getHarga());
            st.setString(3, menu.getGambarPath());
            st.setString(4, type);
            st.setString(5, size);

            int rowsInserted = st.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (Exception ex) {
            System.err.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return null;
    }

    public String toString() {
        return "ID Menu: " + getMenu_id() + "\nNama : " + getNama() + "\nHarga: " + getHarga();
    }
}
