package abstractmodel;

import adobjects.Post;
import adobjects.User;

public interface AbstractModel {
    void addUser(String login, String password, int type); //TODO user not needed
    User getUser(int userID);
    void addPost(Post post);
    Post[] getFeed(int userID);
    void login(String login, String password);
    User[] getSubscribers(int userID);
}
