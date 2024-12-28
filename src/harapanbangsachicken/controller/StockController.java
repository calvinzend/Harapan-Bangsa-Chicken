package harapanbangsachicken.controller;

import java.util.ArrayList;
import java.util.HashMap;

import harapanbangsachicken.model.classes.Keranjang;
import harapanbangsachicken.model.classes.Menu;
import harapanbangsachicken.model.classes.Paket;
import harapanbangsachicken.model.classes.Resep;

public class StockController {
    public void updateStock(ArrayList<Keranjang> keranjang) {
        for (Keranjang k : keranjang) {
            if (k.getMenu() != null) {
                updateStockMenu(k.getMenu(), k.getJumlah());
            } else {
                for (Menu m : k.getPaket().getMenu()) {
                    updateStockMenu(m, k.getJumlah());
                }
            }
        }
    }

    public void updateStockMenu(Menu m, int jumlah) {
        for (Resep r : m.getResep()) {
            if (r.getBahan().getStock() >= (r.getQuantity() * jumlah)) {
                double newStock = r.getBahan().getStock() - (r.getQuantity() * jumlah);
                r.reduceIngredientStock(newStock, r.getBahan().getIng_id());
            } else {
                System.out.println("error, Stock tidak cukup!");
            }
        }
    }

    public boolean checkStockMenu(Menu menu, int jumlah) {
        boolean stockAda = false;
        int count = 0;
        ArrayList<Resep> resep = menu.getResep();
        for (Resep r : resep) {
            if (r.getBahan().getStock() >= r.getQuantity() * jumlah) {
                count++;
            }
        }
        if (count == resep.size()) {
            stockAda = true;
        }
        return stockAda;
    }

    public boolean checkStockPaket(Paket paket, int jumlah) {
        boolean stockAda = false;
        int count = 0;
        for (Menu m : paket.getMenu()) {
            if (checkStockMenu(m, jumlah)) {
                count++;
            }
        }
        if (count == paket.getMenu().size()) {
            stockAda = true;
        }
        return stockAda;
    }

    public ArrayList<Keranjang> checkStockFinal(ArrayList<Keranjang> keranjang) {
        HashMap<String, Double> ingredientMap = new HashMap<>();
        ArrayList<Keranjang> itemTidakValid = new ArrayList<>();

        for (Keranjang k : keranjang) {
            if (k.getMenu() != null) {
                int count = 0;
                ArrayList<Resep> resep = k.getMenu().getResep();
                for (Resep r : resep) {
                    String nama = r.getBahan().getIngredientName();
                    if (ingredientMap.containsKey(nama)) {
                        double ingredientQuantityCheck = ingredientMap.get(nama) + r.getQuantity();
                        if (r.getBahan().getStock() >= ingredientQuantityCheck) {
                            ingredientMap.put(nama, ingredientMap.get(nama) + (r.getQuantity() * k.getJumlah()));
                            count++;
                        }
                    } else {
                        if (r.getBahan().getStock() >= r.getQuantity()) {
                            ingredientMap.put(nama, r.getQuantity() * k.getJumlah());
                            count++;
                        } else {
                            ingredientMap.put(nama, Double.valueOf(0));
                        }
                    }
                }
                if (count != k.getMenu().getResep().size()) {
                    itemTidakValid.add(k);
                }
            } else {
                for (Menu menu : k.getPaket().getMenu()) {
                    int count = 0;
                    ArrayList<Resep> resep = menu.getResep();
                    for (Resep r : resep) {
                        String nama = r.getBahan().getIngredientName();
                        if (ingredientMap.containsKey(nama)) {
                            double ingredientQuantityCheck = ingredientMap.get(nama) + r.getQuantity();
                            if (r.getBahan().getStock() >= ingredientQuantityCheck) {
                                ingredientMap.put(nama, ingredientMap.get(nama) + (r.getQuantity() * k.getJumlah()));
                                count++;
                            }
                        } else {
                            if (r.getBahan().getStock() >= r.getQuantity() * k.getJumlah()) {
                                ingredientMap.put(nama, r.getQuantity());
                                count++;
                            } else {
                                ingredientMap.put(nama, Double.valueOf(0));
                            }
                        }
                    }
                    if (count != k.getMenu().getResep().size()) {
                        itemTidakValid.add(k);
                    }
                }
            }
        }
        return itemTidakValid;
    }

    public boolean checkStockKeranjang(Keranjang k) {
        if (k.getMenu() != null) {
            return checkStockMenu(k.getMenu(), k.getJumlah());
        } else {
            return checkStockPaket(k.getPaket(), k.getJumlah());
        }
    }
}
