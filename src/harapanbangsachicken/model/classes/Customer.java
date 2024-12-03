package harapanbangsachicken.model.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import harapanbangsachicken.model.enums.Level;

public class Customer extends User {
    private String alamat;
    private String gender;
    private double saldo;
    private Level level;
    private int point;
    private ArrayList<History> history;
    private ArrayList<Promo> listPromo;
    private ArrayList<Reward> reward;

    public Customer(int user_id, String namaDepan, String namaBelakang, String password, String email, String noTelp,
            String alamat, String gender, double saldo, ArrayList<History> history, ArrayList<Promo> listPromo,
            int point, Level level, ArrayList<Reward> reward) {
        super(user_id, namaDepan, namaBelakang, password, email, noTelp);
        this.alamat = alamat;
        this.gender = gender;
        this.saldo = saldo;
        this.history = history;
        this.listPromo = listPromo;
        this.point = point;
        this.level = level;
        this.reward = reward;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<History> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<History> history) {
        this.history = history;
    }

    public ArrayList<Promo> getListPromo() {
        return listPromo;
    }

    public void setListPromo(ArrayList<Promo> listPromo) {
        this.listPromo = listPromo;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public ArrayList<Reward> getReward() {
        return reward;
    }

    public void setReward(ArrayList<Reward> reward) {
        this.reward = reward;
    }

    public static boolean Login(String email, String password){
        String query = "SELECT * FROM customer WHERE email = ? and password = ?";

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
    public static boolean Register(Customer customer) {
        String query = "INSERT INTO customer (user_id, namaDepan, namaBelakang, password, email, noTelp, alamat, gender, saldo, poin, lvl) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {

            st.setInt(1, customer.getUser_id());
            st.setString(2, customer.getNamaDepan());
            st.setString(3, customer.getNamaBelakang());
            st.setString(4, customer.getPassword());
            st.setString(5, customer.getEmail());
            st.setString(6, customer.getNoTelp());
            st.setString(7, customer.getAlamat());
            st.setString(8, customer.getGender());
            st.setDouble(9, customer.getSaldo());
            st.setInt(10, customer.getPoint());
            st.setString(11, customer.getLevel().toString());

            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan saat registrasi: " + ex.getMessage());
            return false;
        }
    }

    // public static boolean Register(){
    //     String query= "INSERT INTO customer (user_id, namaDepan, namaBelakang, password, email, noTelp, alamat, gender, saldo, poin, lvl) " +
    //                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    @Override
    public String toString() {
        String historyMsg = "";
        String promoMsg = "";
        String rewardMsg = "";

        for (History msg1 : history) {
            historyMsg += msg1.toString();
        }

        for (Promo msg2 : listPromo) {
            promoMsg += msg2.toString();
        }

        for (Reward msg3 : reward) {
            rewardMsg += msg3.toString();
        }
        return super.toString() + "\nAlamat: " + alamat + "\nGender: " + gender + "\nSaldo: " + saldo + "\nLevel: " + level + "\nPoint: " + point + "\nHistory: " + historyMsg + "\nPromo: " + promoMsg + "\nReward: " + rewardMsg;

                

    
    }

   
}
