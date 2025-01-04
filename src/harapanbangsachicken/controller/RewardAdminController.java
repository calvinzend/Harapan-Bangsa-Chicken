package harapanbangsachicken.controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import harapanbangsachicken.model.classes.Reward;
import harapanbangsachicken.view.DeleteReward;
import harapanbangsachicken.view.InsertReward;
import harapanbangsachicken.view.RewardView;
import harapanbangsachicken.view.UpdateReward;

public class RewardAdminController {
    private InsertReward view;
    private DeleteReward delete;
    private UpdateReward update;

    public RewardAdminController(InsertReward view) {
        this.view = view;
        System.out.println("Form reward ditampilkan");
        this.view.getSubmitReward().addActionListener(e -> insertRewardAdmin());
    }

    public RewardAdminController(DeleteReward delete) {
        this.delete = delete;
        System.out.println("Form reward ditampilkan");
        this.delete.getSubmitReward().addActionListener(e -> deleteRewardAdmin());
    }

    public RewardAdminController(UpdateReward update) {
        this.update = update;
        System.out.println("Form reward ditampilkan");
        this.update.getSubmitReward().addActionListener(e -> updateRewardAdmin());
    }

    public void insertRewardAdmin() {
        if (view.getNamaReward().trim().isEmpty() ||
                view.getMinimalPoinReward().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int minimalPoin = Integer.parseInt(view.getMinimalPoinReward().trim());
            if (minimalPoin <= 0) {
                JOptionPane.showMessageDialog(null, "Poin harus lebih dari 0!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String namaReward = view.getNamaReward();
    
            Reward reward = new Reward(0, namaReward, minimalPoin);
    
            if (Reward.addReward(reward)) {
                view.showMessage("Reward berhasil ditambahkan!");
                view.dispose();
                ArrayList<Reward> show = Reward.getData();
                new RewardView(show);
            } else {
                view.showMessage("Reward gagal ditambahkan! Silakan coba lagi.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public void deleteRewardAdmin() {
        if (delete.getIdReward().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID Reward harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int idReward = Integer.parseInt(delete.getIdReward().trim());
    
            if (Reward.deleteReward(idReward)) {
                delete.showMessage("Reward berhasil dihapus!");
                delete.dispose();
                ArrayList<Reward> show = Reward.getData();
                new RewardView(show);
            } else {
                delete.showMessage("Reward tidak terdata / gagal dihapus! Silakan coba lagi.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public void updateRewardAdmin() {
        if (update.getNamaReward().trim().isEmpty() ||
                update.getMinimalPoinReward().trim().isEmpty() ||
                update.getIdReward().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int minimalPoin = Integer.parseInt(update.getMinimalPoinReward().trim());
            if (minimalPoin <= 0) {
                JOptionPane.showMessageDialog(null, "Poin harus lebih dari 0!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String namaReward = update.getNamaReward();

            int idReward = Integer.parseInt(update.getIdReward().trim());
    
            Reward reward = new Reward(idReward, namaReward, minimalPoin);
    
            if (Reward.updateReward(reward)) {
                update.showMessage("Reward berhasil diupdate!");
                update.dispose();
                ArrayList<Reward> show = Reward.getData();
                new RewardView(show);
            } else {
                update.showMessage("Reward tidak terdata / gagal diupdate! Silakan coba lagi.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
}
