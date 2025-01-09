package harapanbangsachicken.model.classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import harapanbangsachicken.model.interfaces.PembayaranIF;

abstract public class Transaction implements PembayaranIF {
    private int transaction_id;
    private ArrayList<Menu> listMenu;
    private User pemesan;
    private Date tanggalPembelian;
    private double potonganPromo;
    private double hargaTotal;

    public Transaction() {
    }

    public Transaction(int transaction_id, ArrayList<Menu> listMenu, User pemesan, Date tanggalPembelian,
            double potonganPromo, double hargaTotal) {
        this.transaction_id = transaction_id;
        this.listMenu = listMenu;
        this.pemesan = pemesan;
        this.tanggalPembelian = tanggalPembelian;
        this.potonganPromo = potonganPromo;
        this.hargaTotal = hargaTotal;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public ArrayList<Menu> getListMenu() {
        return listMenu;
    }

    public void setListMenu(ArrayList<Menu> listMenu) {
        this.listMenu = listMenu;
    }

    public User getPemesan() {
        return pemesan;
    }

    public void setPemesan(User pemesan) {
        this.pemesan = pemesan;
    }

    public Date getTanggalPembelian() {
        return tanggalPembelian;
    }

    public void setTanggalPembelian(Date tanggalPembelian) {
        this.tanggalPembelian = tanggalPembelian;
    }

    public double getPotonganPromo() {
        return potonganPromo;
    }

    public void setPotonganPromo(double potonganPromo) {
        this.potonganPromo = potonganPromo;
    }

    public double getHargaTotal() {
        return hargaTotal;
    }

    public void setHargaTotal(double hargaTotal) {
        this.hargaTotal = hargaTotal;
    }

    public static boolean insertTransaction(Transaction transaction) {
        String query = "INSERT INTO `transaction`(`transaction_id`, `tanggalPembelian`, `promo_id`, `customer_id`, `potonganPromo`, `hargaTotal`) "
                +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {

            st.setInt(1, transaction.getTransaction_id());
            st.setDate(2, new java.sql.Date(transaction.getTanggalPembelian().getTime()));

            if (transaction.getPemesan() instanceof Customer) {
                Customer customer = (Customer) transaction.getPemesan();
                if (customer.getListPromo() != null && !customer.getListPromo().isEmpty()) {
                    st.setInt(3, customer.getListPromo().get(0).getPromo_id());
                } else {
                    st.setNull(3, java.sql.Types.INTEGER);
                }
            } else {
                st.setNull(3, java.sql.Types.INTEGER);
            }

            st.setInt(4, transaction.getPemesan().getUser_id());
            st.setDouble(5, transaction.getPotonganPromo());
            st.setDouble(6, transaction.getHargaTotal());

            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan saat menyimpan transaksi: " + ex.getMessage());
            return false;
        }
    }

    public static Transaction getLatestTransactionByUserId(int userId) {
        Transaction transaction = null;
        String query = "SELECT t.* FROM `transaction` t " +
                "INNER JOIN `history` h ON t.transaction_id = h.transaction_id " +
                "WHERE h.customer_id = ? " +
                "ORDER BY t.tanggalPembelian DESC LIMIT 1";

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

    public static boolean insertTransaction(Transaction transaction, ArrayList<Keranjang> keranjangList,
            ArrayList<Promo> promoList) {
        String insertTransactionQuery = "INSERT INTO `transaction` (`tanggalPembelian`, `promo_id`, `potonganPromo`, `hargaTotal`) "
                + "VALUES (?, ?, ?, ?)";
        String insertTransactionMenuQuery = "INSERT INTO `transactionmenu` (`transaction_id`, `menu_id`, `paket_id`) "
                + "VALUES (?, ?, ?)";
        String insertHistoryQuery = "INSERT INTO `history` (`transaction_id`, `customer_id`, `date`) VALUES (?, ?, ?)";

        try (Connection connection = ConnectionManager.getConnection()) {
            connection.setAutoCommit(false); // Start transaction

            try (PreparedStatement transactionStatement = connection.prepareStatement(insertTransactionQuery,
                    Statement.RETURN_GENERATED_KEYS);
                    PreparedStatement transactionMenuStatement = connection
                            .prepareStatement(insertTransactionMenuQuery);
                    PreparedStatement historyStatement = connection.prepareStatement(insertHistoryQuery)) {

                // Insert transaction details
                transactionStatement.setDate(1, transaction.getTanggalPembelian());
                if (promoList != null && !promoList.isEmpty()) {
                    transactionStatement.setInt(2, promoList.get(0).getPromo_id());
                } else {
                    transactionStatement.setNull(2, java.sql.Types.INTEGER);
                }
                transactionStatement.setDouble(3, transaction.getPotonganPromo());
                transactionStatement.setDouble(4, transaction.getHargaTotal());

                int rowsInserted = transactionStatement.executeUpdate();
                if (rowsInserted == 0) {
                    connection.rollback();
                    return false;
                }

                // Retrieve generated transaction ID
                int transactionId;
                try (ResultSet rs = transactionStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        transactionId = rs.getInt(1);
                    } else {
                        connection.rollback();
                        return false;
                    }
                }

                // Insert transaction menu details
                for (Keranjang keranjang : keranjangList) {
                    transactionMenuStatement.setInt(1, transactionId);
                    if (keranjang.getMenu() != null) {
                        transactionMenuStatement.setInt(2, keranjang.getMenu().getMenu_id());
                        transactionMenuStatement.setNull(3, java.sql.Types.INTEGER);
                    } else if (keranjang.getPaket() != null) {
                        transactionMenuStatement.setNull(2, java.sql.Types.INTEGER);
                        transactionMenuStatement.setInt(3, keranjang.getPaket().getPaket_id());
                    } else {
                        transactionMenuStatement.setNull(2, java.sql.Types.INTEGER);
                        transactionMenuStatement.setNull(3, java.sql.Types.INTEGER);
                    }

                    int rowsInsertedMenu = transactionMenuStatement.executeUpdate();
                    if (rowsInsertedMenu == 0) {
                        connection.rollback();
                        return false;
                    }
                }

                // Insert history details
                historyStatement.setInt(1, transactionId);
                historyStatement.setInt(2, SingletonManager.getInstance().getUser().getUser_id());
                historyStatement.setDate(3, transaction.getTanggalPembelian());
                int rowsInsertedHistory = historyStatement.executeUpdate();
                if (rowsInsertedHistory == 0) {
                    connection.rollback();
                    return false;
                }

                connection.commit(); // Commit transaction
                return true;

            } catch (SQLException e) {
                connection.rollback(); // Rollback on error
                System.err.println("Transaction error: " + e.getMessage());
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
            return false;
        }
    }

}
