package harapanbangsachicken.controller;

import java.util.HashMap;

import javax.swing.JOptionPane;

import harapanbangsachicken.model.classes.Paket;

public class PaketEditAdminController {

    public String updatePaket(Paket paket) {
        if (Paket.updatePaket(paket)) {
            return "Update paket berhasil!";
        }
        return "Error! update paket gagal!";
    }

    public boolean insertMenuToPaket(int id_paket, int id_menu) {
        return Paket.insertPaketMenu(id_paket, id_menu);
    }

    public Integer insertPaket(Paket paket) {
        return Paket.insertPaket(paket);
    }

    public String deleteMenu(int paketId, int menuId) {
        String msg = null;

        if (Paket.deletePaketMenu(paketId, menuId)) {
            msg = "Delete Menu dari paket berhasil";
        } else {
            msg = "Error!, tidak dapat menghapus menu dari paket";
        }

        return msg;
    }

    public Integer getKeyByValue(HashMap<Integer, String> map, String value) {
        for (Integer key : map.keySet()) {
            if (map.get(key) == value) {
                return key;
            }
        }
        return null;
    }

    public boolean fieldNotEmpty(String nama, int harga, String gambar) {
        return nama != null && !nama.trim().isEmpty() && harga > 0 && gambar != null && !gambar.trim().isEmpty();
    }

    public String deletePaket(int paketId) {
        int choice = JOptionPane.showConfirmDialog(null, "Yakin ingin delete?", "Confirmation",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            String msg = "";

            boolean result = Paket.deletePaket(paketId);

            if (result) {
                msg = "Berhasil menghapus Paket";
            } else {
                msg = "Error!, Tidak dapat menghapus Paket";
            }

            return msg;
        } else {
            return null;
        }
    }

    public String deletePaketMenu(int paketId, int menuId) {
        int choice = JOptionPane.showConfirmDialog(null, "Yakin ingin delete?", "Confirmation",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            String msg = "";

            boolean result = Paket.deletePaketMenu(paketId, menuId);

            if (result) {
                msg = "Berhasil menghapus Menu dari Paket";
            } else {
                msg = "Error!, Tidak dapat menghapus Paket";
            }

            return msg;
        } else {
            return null;
        }
    }
}
