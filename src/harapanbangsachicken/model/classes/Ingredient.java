package harapanbangsachicken.model.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

    public static ArrayList<Ingredient> getData() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        String query = "SELECT * FROM ingredient";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int ing_id = rs.getInt("ing_id");
                    String name = rs.getString("ingredient_name");
                    double stock = rs.getDouble("stock");
                    String satuan = rs.getString("satuan");

                    ingredients.add(new Ingredient(ing_id, name, stock, satuan));
                }
            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return ingredients;
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

    // =============================================================================================

    public static boolean addIngredient(Ingredient ingredient) {
        String query = "INSERT INTO `ingredient`(`ing_id`, `ingredient_name`, `stock`, `satuan`) VALUES (?, ?, ?, ?)";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {

            st.setInt(1, ingredient.getIng_id());
            st.setString(2, ingredient.getIngredientName());
            st.setDouble(3, ingredient.getStock());
            st.setString(4, ingredient.getSatuan());

            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan saat menambahkan ingredient: " + ex.getMessage());
            return false;
        }
    }

    public static boolean updateIngredient(Ingredient ingredient) {
        String query = "UPDATE `ingredient` SET `ingredient_name` = ?, `stock` = ?, `satuan` = ? WHERE `ing_id` = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {

            st.setString(1, ingredient.getIngredientName());
            st.setDouble(2, ingredient.getStock());
            st.setString(3, ingredient.getSatuan());
            st.setInt(4, ingredient.getIng_id());

            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;

        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan saat memperbarui ingredient: " + ex.getMessage());
            return false;
        }
    }

    public static boolean deleteIngredient(int ing_id) {
        String query = "DELETE FROM `ingredient` WHERE `ing_id` = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {

            st.setInt(1, ing_id);

            int rowsDeleted = st.executeUpdate();
            return rowsDeleted > 0;

        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan saat menghapus ingredient: " + ex.getMessage());
            return false;
        }
    }

    public static ArrayList<Ingredient> getDatas() {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        String query = "SELECT * FROM ingredient";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {
    
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ingredientList.add(new Ingredient(
                        rs.getInt("ing_id"),
                        rs.getString("ingredient_name"),
                        rs.getDouble("stock"),
                        rs.getString("satuan")
                    ));
                }
            }
            return ingredientList;
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return null;
    }
}
