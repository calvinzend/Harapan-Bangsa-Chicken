package harapanbangsachicken.model.classes;

public class Keranjang {
    private Menu Menu;
    private int jumlah;
    private Paket paket;

    public Keranjang() {
    }

    public Keranjang(Menu menu, int jumlah) {
        Menu = menu;
        this.jumlah = jumlah;
    }

    public Keranjang(int jumlah, Paket paket) {
        this.jumlah = jumlah;
        this.paket = paket;
    }

    public Menu getMenu() {
        return Menu;
    }

    public void setMenu(Menu menu) {
        Menu = menu;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public Paket getPaket() {
        return paket;
    }

    public void setPaket(Paket paket) {
        this.paket = paket;
    }

}