package harapanbangsachicken.controller;

import java.util.ArrayList;

import harapanbangsachicken.model.classes.Keranjang;
import harapanbangsachicken.model.classes.Menu;
import harapanbangsachicken.model.classes.Paket;
import harapanbangsachicken.model.classes.Resep;

public class StockController {
    public void updateStock(ArrayList<Keranjang> keranjang) {
        for (Keranjang k : keranjang) {
            if (k.getMenu() != null) {
                updateStockMenu(k.getMenu());
            }else{
                for (Menu m : k.getPaket().getMenu()) {
                    updateStockMenu(m);
                }
            }
        }
    }

    public void updateStockMenu(Menu m) {
        for (Resep r : m.getResep()) {
            if (r.getBahan().getStock() > 0) {
                double newStock = r.getBahan().getStock() - r.getQuantity();
                r.reduceIngredientStock(newStock, r.getBahan().getIng_id());
            } else {
                System.out.println("error, Stock tidak cukup!");
            }
        }
    }

    public boolean checkStockMenu(Menu menu) {
        boolean stockAda = false;
        int count = 0;
        ArrayList<Resep> resep = menu.getResep();
        for (Resep r : resep) {
            if (r.getBahan().getStock() >= r.getQuantity()) {
                count++;
            }
        }
        if (count == resep.size()) {
            stockAda = true;
        }
        return stockAda;
    }

    public boolean checkStockPaket(Paket paket) {
        boolean stockAda = false;
        int count = 0;
        for (Menu m : paket.getMenu()) {
            if (checkStockMenu(m)) {
                count++;
            }
        }
        if (count == paket.getMenu().size()) {
            stockAda = true;
        }
        return stockAda;
    }

    public boolean checkStockKeranjang(Keranjang k) {
        if (k.getMenu() != null) {
            return checkStockMenu(k.getMenu());
        } else {
            return checkStockPaket(k.getPaket());
        }
    }
}
