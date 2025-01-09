package harapanbangsachicken.model.classes;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Reward {
    private int reward_id;
    private String rewardName;
    private int minimalPoint;

    private Reward(RewardBuilder builder) {
        this.reward_id = builder.reward_id;
        this.rewardName = builder.rewardName;
        this.minimalPoint = builder.minimalPoint;
    }

    public int getReward_id() {
        return reward_id;
    }

    public void setReward_id(int reward_id) {
        this.reward_id = reward_id;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public int getMinimalPoint() {
        return minimalPoint;
    }

    public void setMinimalPoint(int minimalPoint) {
        this.minimalPoint = minimalPoint;
    }

    public static boolean addReward(Reward reward) {
        String query = "INSERT INTO `reward`(`reward_id`, `name`, `minimalPoin`) VALUES (?, ?, ?)";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {

            st.setInt(1, reward.getReward_id());
            st.setString(2, reward.getRewardName());
            st.setInt(3, reward.getMinimalPoint());

            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan saat menambahkan reward: " + ex.getMessage());
            return false;
        }
    }

    public static boolean updateReward(Reward reward) {
        String query = "UPDATE `reward` SET `name` = ?, `minimalPoin` = ? WHERE `reward_id` = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {

            st.setString(1, reward.getRewardName());
            st.setInt(2, reward.getMinimalPoint());
            st.setInt(3, reward.getReward_id());

            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;

        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan saat memperbarui reward: " + ex.getMessage());
            return false;
        }
    }

    public static boolean deleteReward(int reward_id) {
        String query = "DELETE FROM `reward` WHERE `reward_id` = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {

            st.setInt(1, reward_id);

            int rowsDeleted = st.executeUpdate();
            return rowsDeleted > 0;

        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan saat menghapus reward: " + ex.getMessage());
            return false;
        }
    }

    public static ArrayList<Reward> getData() {
        ArrayList<Reward> rewardList = new ArrayList<>();
        String query = "SELECT * FROM reward";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    rewardList.add(new Reward.RewardBuilder()
                            .setRewardId(rs.getInt("reward_id"))
                            .setRewardName(rs.getString("name"))
                            .setMinimalPoint(rs.getInt("minimalPoin"))
                            .build());
                }
            }
            return rewardList;
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "ID Reward: " + reward_id + "\nNama Reward: " + rewardName + "\nMinimal Poin: " + minimalPoint;
    }

    public static class RewardBuilder {
        private int reward_id;
        private String rewardName;
        private int minimalPoint;

        public RewardBuilder setRewardId(int reward_id) {
            this.reward_id = reward_id;
            return this;
        }

        public RewardBuilder setRewardName(String rewardName) {
            this.rewardName = rewardName;
            return this;
        }

        public RewardBuilder setMinimalPoint(int minimalPoint) {
            this.minimalPoint = minimalPoint;
            return this;
        }

        public Reward build() {
            return new Reward(this);
        }
    }

    public static boolean claimReward(int userId, int rewardId, int minimalPoin) {
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
    
            String queryUser = "SELECT poin FROM customer WHERE user_id = ?";
            PreparedStatement stmtUser = con.prepareStatement(queryUser);
            stmtUser.setInt(1, userId);
            ResultSet rs = stmtUser.executeQuery();
    
            if (rs.next()) {
                int userPoints = rs.getInt("poin");
                if (userPoints >= minimalPoin) {
                    String updateUser = "UPDATE customer SET poin = poin - ? WHERE user_id = ?";
                    PreparedStatement stmtUpdate = con.prepareStatement(updateUser);
                    stmtUpdate.setInt(1, minimalPoin);
                    stmtUpdate.setInt(2, userId);
                    stmtUpdate.executeUpdate();

    
                    String deleteRewardQuery = "DELETE FROM reward WHERE reward_id = ?";
                    PreparedStatement stmtDelete = con.prepareStatement(deleteRewardQuery);
                    stmtDelete.setInt(1, rewardId);
                    stmtDelete.executeUpdate();
    
                    return true; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    
    
}
