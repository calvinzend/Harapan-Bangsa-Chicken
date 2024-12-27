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

    public String showResep() {
        String msg = "";
        Ingredient bahanResep = bahan;
        msg += "\nBahan  : " + bahanResep.getIngredientName();
        msg += "\nJumlah : " + getQuantity() + " " + getSatuan();
        return msg;
    }

    public boolean updateResep(Ingredient _bahan, int _quantity, String _satuan) {
        if (_bahan != null) {
            setBahan(_bahan);
            return true;
        } else {
            if (_quantity != 0) {
                if (_satuan != null) {
                    setSatuan(_satuan);
                }
                setQuantity(_quantity);
            } else {
                if (_satuan != null) {
                    setSatuan(_satuan);
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean insertResep(Ingredient _bahan, int _quantity, String _satuan) {
        boolean lengkap = false;
        if (_bahan != null || _quantity != 0 || _satuan != null) {
            lengkap = true;
        }
        if (lengkap) {
            setBahan(_bahan);
            setQuantity(_quantity);
            setSatuan(_satuan);
            return true;
        }
        return false;
    }

    public boolean reduceIngredientStock(double newQuantity, int _id){
        return Ingredient.updateStock(newQuantity, _id);
    }
}
