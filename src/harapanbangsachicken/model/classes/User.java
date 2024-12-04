package harapanbangsachicken.model.classes;

public abstract class User {
    private int user_id;
    private String namaDepan;
    private String namaBelakang;
    private String password;
    private String email;
    private String noTelp;

    public User(int user_id, String namaDepan, String namaBelakang, String password, String email, String noTelp) {
        this.user_id = user_id;
        this.namaDepan = namaDepan;
        this.namaBelakang = namaBelakang;
        this.password = password;
        this.email = email;
        this.noTelp = noTelp;
    }
    
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNamaDepan() {
        return namaDepan;
    }

    public void setNamaDepan(String namaDepan) {
        this.namaDepan = namaDepan;
    }

    public String getNamaBelakang() {
        return namaBelakang;
    }

    public void setNamaBelakang(String namaBelakang) {
        this.namaBelakang = namaBelakang;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelp() {
        return noTelp;
    }
    
    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public static boolean cekLogin(String inputEmail, String inputPassword) {
        if (Customer.Login(inputEmail, inputPassword)) {
            return true;
        }else if (false) {
            return true;
        }
        return false;
    }

    public String showProfile(){
        String hasil = "ID User: " + user_id + "<br>Nama Depan: " + namaDepan + "<br>Nama Belakang: " + namaBelakang + "<br>Email: " + email + "<br>Nomor Telepon: " + noTelp;
        if (this instanceof Customer) {
            Customer customer = (Customer) this;
            hasil += "<br>Alamat: " + customer.getAlamat() + "<br>Gender: " + customer.getGender()+"<br>Saldo: " + customer.getSaldo() + "<br>Point: " + customer.getSaldo() + "<br>Level: " + customer.getLevel(); 
        }
        return hasil;
    }
}
