package harapanbangsachicken.controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import harapanbangsachicken.model.classes.Ingredient;
import harapanbangsachicken.view.AddStockIngredient;
import harapanbangsachicken.view.DeleteIngredient;
import harapanbangsachicken.view.InsertIngredient;
import harapanbangsachicken.view.IngredientView;
import harapanbangsachicken.view.UpdateIngredient;

public class IngredientAdminController {
    private InsertIngredient view;
    private DeleteIngredient delete;
    private UpdateIngredient update;
    private AddStockIngredient stock;

    public IngredientAdminController(InsertIngredient view) {
        this.view = view;
        System.out.println("Form ingredient ditampilkan");
        this.view.getSubmitIngredient().addActionListener(e -> insertIngredientAdmin());
    }

    public IngredientAdminController(DeleteIngredient delete) {
        this.delete = delete;
        System.out.println("Form ingredient ditampilkan");
        this.delete.getSubmitIngredient().addActionListener(e -> deleteIngredientAdmin());
    }

    public IngredientAdminController(UpdateIngredient update) {
        this.update = update;
        System.out.println("Form ingredient ditampilkan");
        this.update.getSubmitIngredient().addActionListener(e -> updateIngredientAdmin());
    }

    public IngredientAdminController(AddStockIngredient stock) {
        this.stock = stock;
        System.out.println("Form ingredient ditampilkan");
        this.stock.getSubmitIngredient().addActionListener(e -> addStockAdmin());
    }

    public void insertIngredientAdmin() {
        if (view.getNamaIngredient().trim().isEmpty() ||
                view.getStock().trim().isEmpty() ||
                view.getSatuan().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double stock = Double.parseDouble(view.getStock().trim());
            if (stock <= 0) {
                JOptionPane.showMessageDialog(null, "Stock harus lebih dari 0!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String namaIngredient = view.getNamaIngredient();

            String satuan = view.getSatuan();
    
            Ingredient ing = new Ingredient(0, namaIngredient, stock, satuan);
    
            if (Ingredient.addIngredient(ing)) {
                view.showMessage("Ingredient berhasil ditambahkan!");
                view.dispose();
                ArrayList<Ingredient> show = Ingredient.getDatas();
                new IngredientView(show);
            } else {
                view.showMessage("Ingredient gagal ditambahkan! Silakan coba lagi.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public void deleteIngredientAdmin() {
        if (delete.getIdIngredient().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID Ingredient harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int idIngredient = Integer.parseInt(delete.getIdIngredient().trim());
    
            if (Ingredient.deleteIngredient(idIngredient)) {
                delete.showMessage("Ingredient berhasil dihapus!");
                delete.dispose();
                ArrayList<Ingredient> show = Ingredient.getDatas();
                new IngredientView(show);
            } else {
                delete.showMessage("Ingredient tidak terdata / tidak bisa dihapus karna telah terpakai pada suatu menu! Silakan coba lagi.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public void updateIngredientAdmin() {
        if (update.getNamaIngredient().trim().isEmpty() ||
                update.getStock().trim().isEmpty() ||
                update.getSatuan().trim().isEmpty() ||
                update.getIdIngredient().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double stock = Double.parseDouble(update.getStock().trim());
            if (stock <= 0) {
                JOptionPane.showMessageDialog(null, "Stock harus lebih dari 0!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String namaIngredient = update.getNamaIngredient();

            String satuan = update.getSatuan();

            int idIngredient = Integer.parseInt(update.getIdIngredient().trim());
    
            Ingredient ing = new Ingredient(idIngredient, namaIngredient, stock, satuan);
    
            if (Ingredient.updateIngredient(ing)) {
                update.showMessage("Ingredient berhasil diupdate!");
                update.dispose();
                ArrayList<Ingredient> show = Ingredient.getDatas();
                new IngredientView(show);
            } else {
                update.showMessage("Ingredient tidak terdata / gagal diupdate! Silakan coba lagi.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public void addStockAdmin() {
        if (stock.getIdIngredient().trim().isEmpty() || 
                stock.getQtyStock().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double qtyStock = Double.parseDouble(stock.getQtyStock().trim());
            if (qtyStock <= 0) {
                JOptionPane.showMessageDialog(null, "Stock harus lebih dari 0!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idIngredient = Integer.parseInt(stock.getIdIngredient().trim());
    
            if (Ingredient.updateStock(qtyStock, idIngredient)) {
                stock.showMessage("Stock ingredient berhasil diupdate!");
                stock.dispose();
                ArrayList<Ingredient> show = Ingredient.getDatas();
                new IngredientView(show);
            } else {
                stock.showMessage("Ingredient tidak terdata / Stock ingredient gagal diupdate! Silakan coba lagi.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

}
