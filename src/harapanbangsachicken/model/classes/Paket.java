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

    public Paket(int paket_id, String namaPaket, int harga, String picture_path) {
        this.paket_id = paket_id;
        this.namaPaket = namaPaket;
        this.harga = harga;
        this.picture_path = picture_path;
    }

    public Paket(String namaPaket, int harga, String picture_path) {
        this.namaPaket = namaPaket;
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

    public static Paket getData(int paketId) {
        Paket paket = null;
        String query = "SELECT * FROM paket WHERE paket_id = ?";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, paketId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int paket_id = rs.getInt("paket_id");
                    String nama = rs.getString("nama_paket");
                    ArrayList<Menu> menu = Paket.getPaketMenu(paket_id);
                    int harga = rs.getInt("harga");
                    String pic_path = rs.getString("picture_path");

                    paket = new Paket(paket_id, nama, menu, harga, pic_path);
                }
            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return paket;
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

    public static Integer insertPaket(Paket paket) {
        String query = "INSERT INTO paket(nama_paket, harga, picture_path) VALUES(?, ?, ?)";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            st.setString(1, paket.getNamaPaket());
            st.setInt(2, paket.getHarga());
            st.setString(3, paket.picture_path);

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

    public static boolean insertPaketMenu(int id_paket, int id_menu) {
        String query = "INSERT INTO paketmenu(paket_id, menu_id) VALUES(?, ?)";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            st.setInt(1, id_paket);
            st.setInt(2, id_menu);

            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception ex) {
            System.err.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return false;
    }

    public static boolean updatePaket(Paket paket) {
        String query = "UPDATE paket SET nama_paket = ?, harga = ?, picture_path = ? WHERE paket_id = ?";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {

            st.setString(1, paket.getNamaPaket());
            st.setInt(2, paket.getHarga());
            st.setString(3, paket.getPicture_path());
            st.setInt(4, paket.getPaket_id());

            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return false;
    }

    public static boolean deletePaket(int paket_id) {
        String query = "DELETE FROM paket WHERE paket_id = ?";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, paket_id);
            int affectedRows = st.executeUpdate();
            return affectedRows > 0;

        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return false;
    }

    public static boolean deletePaketMenu(int id_paket, int id_menu) {
        String query = "DELETE FROM paketmenu WHERE paket_id = ? AND menu_id = ?";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, id_paket);
            st.setInt(2, id_menu);
            int affectedRows = st.executeUpdate();
            return affectedRows > 0;
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
            return false;
        }
    }

}
