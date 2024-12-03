package harapanbangsachicken.model.classes;

import java.sql.Date;

public class Promo {
    private int promo_id;
    private String namaPromo;
    private int nominalPromo;
    private Date date;

    public Promo(int promo_id, String namaPromo, int nominalPromo, Date date) {
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

    public int getNominalPromo() {
        return nominalPromo;
    }

    public void setNominalPromo(int nominalPromo) {
        this.nominalPromo = nominalPromo;
    }

    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ID Promo: " + promo_id + "\nNama Promo: " + namaPromo + "\nNominal Promo: " + nominalPromo + "\nTanggal Kadaluarsa: " + date;
    }
}
