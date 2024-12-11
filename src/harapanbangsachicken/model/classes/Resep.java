package harapanbangsachicken.model.classes;

public class Resep {
    private Ingredient bahan;
    private int quantity;
    private String satuan;

    public Resep() {
    }

    public Resep(Ingredient bahan, int quantity, String satuan) {
        this.bahan = bahan;
        this.quantity = quantity;
        this.satuan = satuan;
    }

    public Ingredient getBahan() {
        return bahan;
    }

    public void setBahan(Ingredient bahan) {
        this.bahan = bahan;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String toString() {
        return "Ingredient : " + getBahan() + "\nQuantity : " + getQuantity() + "\nSatuan : " + getSatuan();
    }

    public String showResep() {
        String msg = "";
        Ingredient bahanResep = bahan;
        msg += "\nBahan  : " + bahanResep.getIngredientName();
        msg += "\nJumlah : " + getQuantity() + " " + getSatuan();
        return msg;
    }

    public boolean updateResep(Ingredient _bahan, int _quantity, String _satuan) {
        if (_bahan != null) {
            setBahan(_bahan);
            return true;
        } else {
            if (_quantity != 0) {
                if (_satuan != null) {
                    setSatuan(_satuan);
                }
                setQuantity(_quantity);
            } else {
                if (_satuan != null) {
                    setSatuan(_satuan);
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean insertResep(Ingredient _bahan, int _quantity, String _satuan) {
        boolean lengkap = false;
        if (_bahan != null || _quantity != 0 || _satuan != null) {
            lengkap = true;
        }
        if (lengkap) {
            setBahan(_bahan);
            setQuantity(_quantity);
            setSatuan(_satuan);
            return true;
        }
        return false;
    }
}
