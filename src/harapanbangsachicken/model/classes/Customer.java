package harapanbangsachicken.model.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.swing.JOptionPane;

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

    public static Customer getData(String email) {
        String query = "SELECT * FROM customer WHERE email = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {
    
            st.setString(1, email);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                        rs.getInt("user_id"),
                        rs.getString("namaDepan"),
                        rs.getString("namaBelakang"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("noTelp"),
                        rs.getString("alamat"),
                        rs.getString("gender"),
                        rs.getDouble("saldo"),
                        null, 
                        null, 
                        rs.getInt("poin"),
                        Level.valueOf(rs.getString("lvl")),
                        null  
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
    public static Boolean Konfirmasi(int response) {
        if (response == 0) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null,
                "Checkout dibatalkan.",
                "Informasi",
                JOptionPane.INFORMATION_MESSAGE
            );            
            return false;
        }
    }
    public void kurangiSaldo(double jumlah) {
        if (this.saldo >= jumlah) {
            this.saldo -= jumlah;
        }
    }

     public static boolean Pembayaran(int pilihan) {
        if (pilihan == 0) {

            E_Money eMoney = new E_Money();
            
            return eMoney.prosesPembayaran();

        } else if (pilihan == 1) {

          Card card= new Card();
          return card.prosesPembayaran();

        }
        return false;
    }

    
    
    
    public static ArrayList<Transaction> getDataHistory(int userId) {
        String query = "SELECT t.transaction_id, t.tanggalPembelian, t.potonganPromo, t.hargaTotal " +
                       "FROM history h " +
                       "JOIN transaction t ON t.transaction_id = h.transaction_id " +
                       "WHERE h.customer_id = ?";
        ArrayList<Transaction> historyList = new ArrayList<>();
    
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {
            
            st.setInt(1, userId); 
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

    public void addSaldo(double amount) {
        if (amount > 0) {
            this.saldo += amount;
            updateSaldoInDatabase();
        } else {
            throw new IllegalArgumentException("Jumlah saldo yang ditambahkan harus lebih besar dari 0.");
        }
    }

    public void updateSaldo(double amount) {
        if (amount >= 0) {
            this.saldo = amount;
            updateSaldoInDatabase();
        } else {
            throw new IllegalArgumentException("Saldo harus lebih besar atau sama dengan 0.");
        }
    }

    private void updateSaldoInDatabase() {
        String sql = "UPDATE customer SET saldo = ? WHERE user_id = ?";
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, this.saldo);
            stmt.setInt(2, this.getUser_id());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update saldo in the database.");
        }
    }


    public static boolean updateCustomerLevel() {
        String query = "UPDATE customer SET lvl = ? WHERE user_id = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {
    
            Customer customer = (Customer) SingletonManager.getInstance().getUser();

            st.setString(1, customer.getLevel().toString());
            st.setInt(2, customer.getUser_id());
    
            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan saat memperbarui level dan poin: " + ex.getMessage());
        }
        return false;
    }

    public static boolean updateCustomerPoint() {
        String query = "UPDATE customer SET poin = ? WHERE user_id = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {
    
            Customer customer = (Customer) SingletonManager.getInstance().getUser();

            st.setInt(1, customer.getPoint());
            st.setInt(2, customer.getUser_id());
    
            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan saat memperbarui level dan poin: " + ex.getMessage());
        }
        return false;
    }
    
   
}
