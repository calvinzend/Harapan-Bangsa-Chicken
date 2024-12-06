package harapanbangsachicken.model.classes;

public class SingletonManager {
    private static SingletonManager instance;
    private User user;

    SingletonManager() {
    }

    public static SingletonManager getInstance() {
        if (instance == null) {
            instance = new SingletonManager();
        }
        return instance;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void clearUser() {
        this.user = null;
    }

}
