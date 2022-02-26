package contentsite;

import java.util.Objects;

public class User {

    private String userName;
    private int password;
    private boolean logIn;
    private boolean premiumMember;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = (userName + password).hashCode();
        setLogIn(false);
    }

    public void upgradeForPremium() {
        premiumMember = true;
    }

    public void setLogIn(boolean value) {
        logIn = value;
    }

    public String getUserName() {
        return userName;
    }

    public int getPassword() {
        return password;
    }

    public boolean isLogIn() {
        return logIn;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

}
