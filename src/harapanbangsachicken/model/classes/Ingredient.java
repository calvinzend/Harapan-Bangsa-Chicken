package harapanbangsachicken.model.classes;

public class Ingredient {
    private int ing_id;
    private String ingredientName;
    private double stock;
    private String satuan;

    public Ingredient() {
    }

    public Ingredient(int ing_id, String ingredientName, double stock, String satuan) {
        this.ing_id = ing_id;
        this.ingredientName = ingredientName;
        this.stock = stock;
        this.satuan = satuan;
    }

    public int getIng_id() {
        return ing_id;
    }

    public void setIng_id(int ing_id) {
        this.ing_id = ing_id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String toString() {
        return "ID Ingredient: " + getIng_id() + "\nName : " + getIngredientName() + "\nStock : " + getStock() + "\nSatuan : " + getSatuan();
    }
}
