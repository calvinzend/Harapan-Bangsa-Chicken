package harapanbangsachicken.model.classes;

public class Bank {
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

}
