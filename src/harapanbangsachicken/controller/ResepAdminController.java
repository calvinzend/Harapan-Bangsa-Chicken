package harapanbangsachicken.controller;

import java.util.HashMap;

import javax.swing.JOptionPane;

import harapanbangsachicken.model.classes.Resep;

public class ResepAdminController {
    public boolean fieldNotEmpty(int newIngId, String newIngName, double newQuantity, String newSatuan) {
        return newIngId > 0 && newIngName != null && !newIngName.trim().isEmpty() && newQuantity > 0
                && newSatuan != null && !newSatuan.trim().isEmpty();
    }

    public boolean insertDataResep(int idMenu, int oldIdIng, int newIngId, String newIngName, double newQuantity,
            String newSatuan) {
        return Resep.updateResep(idMenu, oldIdIng, newIngId, newQuantity, newSatuan);
    }

    public Integer getKeyByValue(HashMap<Integer, String> map, String value) {
        for (Integer key : map.keySet()) {
            if (map.get(key) == value) {
                return key;
            }
        }
        return null;
    }

    public String deleteResep(int menu_id, int ing_id) {
        int choice = JOptionPane.showConfirmDialog(null, "Yakin ingin delete?", "Confirmation",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            String msg = "";

            boolean result = Resep.deleteResepMenu(menu_id, ing_id);

            if (result) {
                msg = "Berhasil menghapus Resep";
            } else {
                msg = "Error!, Tidak dapat menghapus Resep";
            }

            return msg;
        } else {
            return null;
        }
    }
}
