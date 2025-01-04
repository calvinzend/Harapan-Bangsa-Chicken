package harapanbangsachicken.controller;

import javax.swing.JOptionPane;

import harapanbangsachicken.model.classes.Drink;
import harapanbangsachicken.model.classes.Food;
import harapanbangsachicken.model.classes.Menu;
import harapanbangsachicken.model.enums.Size;

public class MenuEditAdminController {

    public boolean fieldNotEmpty(String name, int harga, String gambarPath) {
        return name != null && !name.trim().isEmpty() && harga > 0 && gambarPath != null
                && !gambarPath.trim().isEmpty();
    }

    public boolean fieldNotEmpty(String name, int harga, String gambarPath, String size) {
        return (name != null && !name.trim().isEmpty() && harga > 0 && gambarPath != null
                && !gambarPath.trim().isEmpty() && size != null && !size.trim().isEmpty())
                && Size.isSize(size);
    }

    public Integer insertDataMenu(String name, int harga, String gambarPath, String size) {
        Menu newMenu = null;

        if (Size.isSize(size)) {
            newMenu = new Drink(name, harga, gambarPath, Size.valueOf(size));
        } else {
            newMenu = new Food(name, harga, gambarPath);
        }

        Integer resultId = Menu.insertData(newMenu);
        return resultId;
    }

    public String deleteMenu(int menuId) {
        int choice = JOptionPane.showConfirmDialog(null, "Yakin ingin delete?", "Confirmation",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            String msg = "";

            int result = Menu.deleteData(menuId);

            if (result == 0) {
                msg = "Berhasil menghapus Menu!";
            } else if (result == 2) {
                msg = "Error!, Tidak dapat menghapus Menu\nMenu mungkin terhubung ke paket tertentu";
            } else {
                msg = "Error!, Gagal menghapus Menu";
            }

            return msg;
        } else {
            return null;
        }
    }

    public String updateMenu(Menu newMenu) {
        if (Menu.updateData(newMenu)) {
            return "Update Menu Berhasil!";
        } else {
            return "Update Menu gagal!";
        }
    }
}
