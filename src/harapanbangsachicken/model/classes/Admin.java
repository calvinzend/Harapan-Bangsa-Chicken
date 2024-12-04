package harapanbangsachicken.model.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    @Override
    public String toString() {
        return super.toString();
    }
}
