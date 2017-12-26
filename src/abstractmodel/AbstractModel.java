package abstractmodel;

import adobjects.Post;
import adobjects.User;

import java.io.IOException;

public interface AbstractModel {
    void addUser(String login, String password, int type) throws IOException; //TODO user not needed
    User getUser(int userID) throws IOException;
    void addPost(Post post) throws IOException;
    Post[] getFeed(int userID);
    User login(String login, String password);
    User[] getSubscribers(int userID);
}
