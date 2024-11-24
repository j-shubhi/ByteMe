import java.util.List;

abstract class User {
    protected String username;

    public User(String username) {

        this.username = username;
    }

    public abstract void displayMenu(List<MenuItem> menu);
}