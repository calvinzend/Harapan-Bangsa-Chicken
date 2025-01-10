package harapanbangsachicken.model.classes;

import java.text.NumberFormat;
import java.util.Locale;

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
        }
        return false;
    }

    public String showProfile() {
        StringBuilder hasil = new StringBuilder("<table>");
        hasil.append("<tr><td><b>ID User</b></td><td>: ").append(user_id).append("</td></tr>");
        hasil.append("<tr><td><b>Nama Depan</b></td><td>: ").append(namaDepan).append("</td></tr>");
        hasil.append("<tr><td><b>Nama Belakang</b></td><td>: ").append(namaBelakang).append("</td></tr>");
        hasil.append("<tr><td><b>Email</b></td><td>: ").append(email).append("</td></tr>");
        hasil.append("<tr><td><b>Nomor Telepon</b></td><td>: ").append(noTelp).append("</td></tr>");
    
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        if (this instanceof Customer) {
            Customer customer = (Customer) this;
            hasil.append("<tr><td><b>Alamat</b></td><td>: ").append(customer.getAlamat()).append("</td></tr>");
            hasil.append("<tr><td><b>Gender</b></td><td>: ").append(customer.getGender()).append("</td></tr>");
            hasil.append("<tr><td><b>Saldo</b></td><td>: ").append(numberFormat.format(customer.getSaldo())).append("</td></tr>");
            hasil.append("<tr><td><b>Point</b></td><td>: ").append(customer.getPoint()).append("</td></tr>");
            hasil.append("<tr><td><b>Level</b></td><td>: ").append(customer.getLevel()).append("</td></tr>");
        }
    
        hasil.append("</table>");
        return hasil.toString();
    }

     
    
}
