package harapanbangsachicken.model.classes;

public class Reward {
    private int reward_id;
    private String rewardName;
    private int minimalPoint;

    public Reward(int reward_id, String rewardName, int minimalPoint) {
        this.reward_id = reward_id;
        this.rewardName = rewardName;
        this.minimalPoint = minimalPoint;
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

}
