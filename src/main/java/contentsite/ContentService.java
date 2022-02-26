package contentsite;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContentService {

    private Set<User> users = new HashSet<>();
    private Set<Content> contents = new HashSet<>();

    public void registerUser(String name, String password) {
        if (users.stream().anyMatch(u -> u.getUserName().equals(name))) {
            throw new IllegalArgumentException("Username is already taken: " + name);
        }
        users.add(new User(name, password));
    }


    public void addContent(Content content) {
        if (contents.stream().anyMatch(c -> c.getTitle().equals(content.getTitle()))){
            throw new IllegalArgumentException("Content name is already taken: " + content.getTitle());
        }
        contents.add(content);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public List<Content> getAllContent() {
        return new ArrayList<>(contents);
    }

    public void logIn(String username, String password) {
        User actual = findUserByName(username);
        if (actual == null) {
            throw new IllegalArgumentException("Username is wrong!");
        }
        if (actual.getPassword() == (username+password).hashCode()) {
            actual.setLogIn(true);
        } else {
            throw new IllegalArgumentException("Password is Invalid!");
        }
    }

    private User findUserByName(String username) {
        for (User actual : users) {
            if (actual.getUserName().equals(username)) {
                return actual;
            }
        }
        return null;
    }

    public void clickOnContent(User user, Content content) {
        if(!findUserByName(user.getUserName()).isLogIn()) {
            throw new IllegalStateException("Log in to watch this content!");
        }

        if (content.isPremiumContent() & !user.isPremiumMember()) {
            throw new IllegalStateException("Upgrade for Premium to watch this content!");
        }

        content.click(user);
    }
}
