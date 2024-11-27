package harapanbangsachicken.model.classes;

import java.util.ArrayList;
import java.util.logging.Level;

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
