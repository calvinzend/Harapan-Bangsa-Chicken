package harapanbangsachicken.model.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Ingredient {
    private int ing_id;
    private String ingredientName;
    private double stock;
    private String satuan;

    public Ingredient() {
    }

    public Ingredient(int ing_id, String ingredientName, double stock, String satuan) {
        this.ing_id = ing_id;
        this.ingredientName = ingredientName;
        this.stock = stock;
        this.satuan = satuan;
    }

    public int getIng_id() {
        return ing_id;
    }

    public void setIng_id(int ing_id) {
        this.ing_id = ing_id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String toString() {
        return "ID Ingredient: " + getIng_id() + "\nName : " + getIngredientName() + "\nStock : " + getStock()
                + "\nSatuan : " + getSatuan();
    }

    public static Ingredient getData(int id) {
        Ingredient ingredient = null;
        String query = "SELECT * FROM ingredient where ing_id = ?";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int ing_id = rs.getInt("ing_id");
                    String name = rs.getString("ingredient_name");
                    double stock = rs.getDouble("stock");
                    String satuan = rs.getString("satuan");

                    ingredient = new Ingredient(ing_id, name, stock, satuan);
                }
            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return ingredient;
    }

    public static boolean updateStock(double newStock, int id) {
        String query = "UPDATE ingredient SET stock = ? WHERE ing_id = ?";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setDouble(1, newStock);
            st.setInt(2, id);
            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return false;
    }

    // Fungsi untuk menambah Quantity, dilakukan secara manual oleh admin
    public boolean insertQuantity(double quantity) {
        setStock(getStock() + quantity);
        return true;
    }

    // Fungsi untuk mengurangi Ingredient setiap kali ada pemakaian ingredient
    public boolean deleteQuantity(double quantity) {
        if (getStock() >= quantity) {
            setStock(getStock() - quantity);
            return true;
        } else {
            return false;
        }
    }

    public String checkStock() {
        String str = "Jumlah Stock tersisa = " + getStock() + " " + getSatuan();
        return str;
    }
}
