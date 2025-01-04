package harapanbangsachicken.model.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Resep {
    private Ingredient bahan;
    private double quantity;
    private String satuan;

    public Resep() {
    }

    public Resep(int resep_id, Ingredient bahan, double quantity, String satuan) {
        this.bahan = bahan;
        this.quantity = quantity;
        this.satuan = satuan;
    }

    public Resep(Ingredient bahan, double quantity, String satuan) {
        this.bahan = bahan;
        this.quantity = quantity;
        this.satuan = satuan;
    }

    public Ingredient getBahan() {
        return bahan;
    }

    public void setBahan(Ingredient bahan) {
        this.bahan = bahan;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String toString() {
        return "Ingredient : " + getBahan() + "\nQuantity : " + getQuantity() + "\nSatuan : " + getSatuan();
    }

    public static ArrayList<Resep> getData(int menu_id) {
        ArrayList<Resep> resepList = new ArrayList<>();
        String query = "SELECT * FROM resep WHERE menu_id = ?";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, menu_id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int ing_id = rs.getInt("ing_id");
                    double quantity = rs.getDouble("quantity");
                    String satuan = rs.getString("satuan");
                    Ingredient ingredient = Ingredient.getData(ing_id);

                    resepList.add(new Resep(ingredient, quantity, satuan));
                }

            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return resepList;
    }

    public static Resep getData(int menu_id, int ing_id) {
        Resep resep = null;
        String query = "SELECT * FROM resep WHERE menu_id = ? AND ing_id = ?";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, menu_id);
            st.setInt(2, ing_id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    double quantity = rs.getDouble("quantity");
                    String satuan = rs.getString("satuan");
                    Ingredient ingredient = Ingredient.getData(ing_id);

                    resep = new Resep(ingredient, quantity, satuan);
                }

            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return resep;
    }

    public String showResep() {
        String msg = "";
        Ingredient bahanResep = bahan;
        msg += "\nBahan  : " + bahanResep.getIngredientName();
        msg += "\nJumlah : " + getQuantity() + " " + getSatuan();
        return msg;
    }

    public static boolean updateResep(int _menuId, int _ingId, int _newIngId, double _quantity, String _satuan) {
        String query = "UPDATE resep SET ing_id = ?, quantity = ?, satuan = ? WHERE menu_id = ? AND ing_id = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {
            
            st.setInt(1, _newIngId);
            st.setDouble(2, _quantity);
            st.setString(3, _satuan);
            st.setInt(4, _menuId);
            st.setInt(5, _ingId);
    
            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return false;
    }
    

    public static boolean insertData(int menu_id, Resep resep) {
        String query = "INSERT INTO resep(menu_id, ing_id, quantity, satuan) VALUES(?, ?, ?, ?)";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {
    
            st.setInt(1, menu_id);
            st.setInt(2, resep.bahan.getIng_id());
            st.setDouble(3, resep.getQuantity());
            st.setString(4, resep.getSatuan());
    
            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return false;
    }
    

    public boolean reduceIngredientStock(double newQuantity, int _id) {
        return Ingredient.updateStock(newQuantity, _id);
    }

    public static boolean deleteData(int id) {
        String query = "DELETE FROM resep WHERE resep_id = ?";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, id);
            int affectedRows = st.executeUpdate();
            return affectedRows > 0;
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
            return false;
        }
    }

    public static boolean deleteResepMenu(int menuId, int ing_id) {
        String query = "DELETE FROM resep WHERE menu_id = ? AND ing_id = ?";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, menuId);
            st.setInt(2, ing_id);
            int affectedRows = st.executeUpdate();
            return affectedRows > 0;
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
            return false;
        }
    }
}
