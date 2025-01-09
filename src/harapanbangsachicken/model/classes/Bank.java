package harapanbangsachicken.model.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Bank{
    private int card_id;
    private String noATM;
    private String jenisATM;
    private String passATM;
    private double saldo;

    public Bank() {
    }

    public Bank(int card_id, String noATM, String jenisATM, String passATM, double saldo) {
        this.card_id = card_id;
        this.noATM = noATM;
        this.jenisATM = jenisATM;
        this.passATM = passATM;
        this.saldo = saldo;
    }

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public String getNoATM() {
        return noATM;
    }

    public void setNoATM(String noATM) {
        this.noATM = noATM;
    }

    public String getJenisATM() {
        return jenisATM;
    }

    public void setJenisATM(String jenisATM) {
        this.jenisATM = jenisATM;
    }

    public String getPassATM() {
        return passATM;
    }

    public void setPassATM(String passATM) {
        this.passATM = passATM;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

     public static boolean addBank(Bank bank) {
        String query = "INSERT INTO `bank`(`card_id`, `noATM`, `jenisATM`, `passATM`, `saldo`) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {

            st.setInt(1, bank.getCard_id());
            st.setString(2, bank.getNoATM());
            st.setString(3, bank.getJenisATM());
            st.setString(4, bank.getPassATM());
            st.setDouble(5, bank.getSaldo());

            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException ex) {
            System.err.println("Error while inserting bank: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Bank> getBank() {
        String query = "SELECT * FROM `bank`";

        ArrayList<Bank> bank = new ArrayList<>();

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {

            ResultSet rs = st.executeQuery();


            while(rs.next()) {

                bank.add(new Bank(
                        rs.getInt("card_id"),
                        rs.getString("noATM"),
                        rs.getString("jenisATM"),
                        rs.getString("passATM"),
                        rs.getDouble("saldo")
                ));
            }

        } catch (SQLException ex) {
            System.err.println("Error while retrieving bank: " + ex.getMessage());
            ex.printStackTrace();
        }

        return bank;
    }

    public static void updateBankPayment(String bankAccountNumber, double amount) {
        String sql = "UPDATE bank SET saldo = saldo - ? WHERE noATM = ?";
      
    
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            Customer customer = (Customer) SingletonManager.getInstance().getUser();
            stmt.setDouble(1, amount);
            stmt.setString(2, bankAccountNumber);  
            stmt.executeUpdate();
    
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update bank payment in the database.");
        }
    }
}
