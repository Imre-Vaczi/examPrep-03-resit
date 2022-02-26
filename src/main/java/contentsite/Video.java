package contentsite;

import java.util.ArrayList;
import java.util.List;

public class Video implements Content{

    private String title;
    private int length;
    private boolean premiumContent;
    private List<User> users = new ArrayList<>();

    public Video(String title, int length) {
        this.title = title;
        this.length = length;
        if (length > 15) {
            this.premiumContent = true;
        } else {
            this.premiumContent = false;
        }
    }

    @Override
    public boolean isPremiumContent() {
        return premiumContent;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<User> clickedBy() {
        return new ArrayList<>(users);
    }

    @Override
    public void click(User user) {
        if (user != null) {
            users.add(user);
        } else {
            throw new IllegalArgumentException("User can not be added to users list if it is null.");
        }
    }
}
