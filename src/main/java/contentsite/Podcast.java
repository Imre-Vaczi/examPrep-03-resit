package contentsite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Podcast implements Content{

    private String title;
    private List<String> speakers = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public Podcast(String title, List<String> speakers) {
        this.title = title;
        this.speakers = speakers;
    }

    public List<String> getSpeakers() {
        return speakers;
    }

    @Override
    public boolean isPremiumContent() {
        return false;
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
