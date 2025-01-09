    package harapanbangsachicken.model.classes;

    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

    public class E_Money extends Transaction{
        
        @Override
        public boolean prosesPembayaran(){
                Customer customer = (Customer) SingletonManager.getInstance().getUser();
                double totalHarga = UpdateKeranjang.getInstance().getTotalHarga();

                if (customer.getSaldo() >= UpdateKeranjang.getInstance().getTotalHarga()) {
                    customer.kurangiSaldo(totalHarga);
                    JOptionPane.showMessageDialog(
                        null,
                        "Pembayaran berhasil menggunakan saldo! Saldo tersisa: Rp " + customer.getSaldo(),
                        "Sukses",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    UpdateKeranjang.getInstance().clearKeranjang();
                    E_Money.updateEMoney();

                    return true;

                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Saldo tidak mencukupi untuk melakukan pembayaran.",
                        "Kesalahan",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return false;
                }
        }
        
          public static Transaction getLatestTransactionByUserId(int userId) {
                Transaction transaction = null;
                String query = "SELECT * FROM `transaction` WHERE `customer_id` = ? ORDER BY `tanggalPembelian` DESC LIMIT 1";
            
                try (Connection con = ConnectionManager.getConnection();
                    PreparedStatement pst = con.prepareStatement(query)) {
            
                    pst.setInt(1, userId);
                    ResultSet rs = pst.executeQuery();
            
                    if (rs.next()) {
                        transaction = new E_Money();
                        transaction.setTransaction_id(rs.getInt("transaction_id"));
                        transaction.setTanggalPembelian(rs.getDate("tanggalPembelian"));
                        transaction.setHargaTotal(rs.getDouble("hargaTotal"));
                        transaction.setPotonganPromo(rs.getDouble("potonganPromo"));
                    }
                } catch (Exception e) {
                    System.out.println("Error fetching latest transaction: " + e.getMessage());
                }
            
                return transaction;
            }
    

    public static void updateEMoney() {
        String sql = "UPDATE customer SET saldo = ? WHERE user_id = ?";
    
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            Customer customer = (Customer) SingletonManager.getInstance().getUser();
            stmt.setDouble(1, customer.getSaldo());
            stmt.setInt(2, customer.getUser_id());
            stmt.executeUpdate();
    
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update bank payment in the database.");
        }
    }

    }
