package harapanbangsachicken.model.classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Transaction {
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
        String query = "INSERT INTO `transaction`(`transaction_id`, `tanggalPembelian`, `promo_id`, `customer_id`, `potonganPromo`, `hargaTotal`) " +
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


}
