package adobjects;

public class User {
    public static final int CUSTOMER_TYPE = 0;
    public static final int SHOP_TYPE = 1;
    private String login;
    private int id;
    private int type;

    public User(String login, int id, int type) {
        this.login = login;
        this.id = id;
        this.type = type;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }
}
