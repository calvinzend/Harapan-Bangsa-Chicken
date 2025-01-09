package harapanbangsachicken.model.classes;

import java.sql.*;
import java.util.ArrayList;

public class Admin extends User{
    
    public Admin(int user_id, String namaDepan, String namaBelakang, String password, String email, String noTelp) {
        super(user_id, namaDepan, namaBelakang, password, email, noTelp);
    }

    public static boolean Login(String email, String password){
        String query = "SELECT * FROM admin WHERE email = ? and password = ?";

        System.out.println("hehe");
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {

            st.setString(1, email);
            st.setString(2, password);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return true;
                }else{
                    return false;
                }
            }
        }catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
            return false;
        }
    } 
    public static Admin getData(String email) {
        String query = "SELECT * FROM admin WHERE email = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {
    
            st.setString(1, email);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Admin(
                        rs.getInt("user_id"),
                        rs.getString("namaDepan"),
                        rs.getString("namaBelakang"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("noTelp") 
                    );
                }
            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return null;
    }

    public static double getTotalRevenue() {
        double totalRevenue = 0.0;

        String query = "SELECT SUM(hargaTotal) AS totalRevenue FROM transaction";

        try (Connection con = ConnectionManager.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                totalRevenue = rs.getDouble("totalRevenue");
            }

        } catch (SQLException ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }

        return totalRevenue;
    }

     public static ArrayList<Transaction> getDataHistory() {
        String query = "SELECT t.transaction_id, t.tanggalPembelian, t.potonganPromo, t.hargaTotal FROM history h JOIN transaction t ON t.transaction_id = h.transaction_id ;";
        ArrayList<Transaction> historyList = new ArrayList<>();
    
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {
            
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Transaction transaction = new E_Money();
                    transaction.setTransaction_id(rs.getInt("transaction_id"));
                    transaction.setTanggalPembelian(rs.getDate("tanggalPembelian"));
                    transaction.setPotonganPromo(rs.getDouble("potonganPromo"));
                    transaction.setHargaTotal(rs.getDouble("hargaTotal"));

    
                    historyList.add(transaction);
                }
            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
    
        return historyList; 
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
