package harapanbangsachicken.model.classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Promo {
    private int promo_id;
    private String namaPromo;
    private double nominalPromo;
    private Date date;
    
    public Promo(int promo_id, String namaPromo, double nominalPromo, Date date) {
        this.promo_id = promo_id;
        this.namaPromo = namaPromo;
        this.nominalPromo = nominalPromo;
        this.date = date;
    }

    public int getPromo_id() {
        return promo_id;
    }

    public void setPromo_id(int promo_id) {
        this.promo_id = promo_id;
    }

    public String getNamaPromo() {
        return namaPromo;
    }

    public void setNamaPromo(String namaPromo) {
        this.namaPromo = namaPromo;
    }

    public double getNominalPromo() {
        return nominalPromo;
    }

    public void setNominalPromo(double nominalPromo) {
        this.nominalPromo = nominalPromo;
    }

    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    public static boolean addPromo(Promo promo) {
        String query = "INSERT INTO `promo`(`promo_id`, `namaPromo`, `nominalPromo`, `promo_date`) VALUES (?, ?, ?, ?)";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {

            st.setInt(1, promo.getPromo_id());
            st.setString(2, promo.getNamaPromo());
            st.setDouble(3, promo.getNominalPromo());
            st.setDate(4, promo.getDate());

            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan saat menambahkan promo: " + ex.getMessage());
            return false;
        }
    }

    public static boolean updatePromo(Promo promo) {
        String query = "UPDATE `promo` SET `namaPromo` = ?, `nominalPromo` = ?, `promo_date` = ? WHERE `promo_id` = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {

            st.setString(1, promo.getNamaPromo());
            st.setDouble(2, promo.getNominalPromo());
            st.setDate(3, promo.getDate());
            st.setInt(4, promo.getPromo_id());

            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;

        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan saat memperbarui promo: " + ex.getMessage());
            return false;
        }
    }

    public static boolean deletePromo(int promo_id) {
        String query = "DELETE FROM `promo` WHERE `promo_id` = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {

            st.setInt(1, promo_id);

            int rowsDeleted = st.executeUpdate();
            return rowsDeleted > 0;

        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan saat menghapus promo: " + ex.getMessage());
            return false;
        }
    }

    public static ArrayList<Promo> getData() {
        ArrayList<Promo> promoList = new ArrayList<>();
        String query = "SELECT * FROM promo";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {
    
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    promoList.add(new Promo(
                        rs.getInt("promo_id"),
                        rs.getString("namaPromo"),
                        rs.getInt("nominalPromo"),
                        rs.getDate("promo_date")
                    ));
                }
            }
            return promoList;
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return null;
    }
    public static ArrayList<Promo> getData(int userId) {
        ArrayList<Promo> promoList = new ArrayList<>();
        String query = """
            SELECT p.promo_id, p.namaPromo, p.nominalPromo, p.promo_date 
            FROM customer_promo cp
            JOIN promo p ON cp.promo_id = p.promo_id
            WHERE cp.customer_id = ?
            """;
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {
                
            st.setInt(1, userId);
    
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    promoList.add(new Promo(
                        rs.getInt("promo_id"),
                        rs.getString("namaPromo"),
                        rs.getInt("nominalPromo"),
                        rs.getDate("promo_date")
                    ));
                }
            }
            return promoList;
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return null;
    }
}
