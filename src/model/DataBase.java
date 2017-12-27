package model;

import abstractmodel.AbstractModel;
import adobjects.Post;
import adobjects.User;

import java.io.IOException;
import java.util.ArrayList;

public class DataBase implements AbstractModel {
    private static final String postBasePath = "postBase";
    private PostBase postBase;
    private static final String userBasePath = "userBase";
    private UserBase userBase;
    private static DataBase instance = null;

    private DataBase() {
        postBase = new PostBase(postBasePath);
        userBase = new UserBase(userBasePath);
    }

    static public DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    @Override
    public User addUser(String login, String password, int type) throws IOException {
        userBase.addUser(login, password, type);
        User user;
        try {
            user = userBase.checkExistance(login, password);
            return user;
        }
        catch (IOException err){
            return null;
        }
    }

    @Override
    public User getUser(int userID) throws IOException {
        return userBase.getUser(userID);
    }

    @Override
    public void addPost(Post post) throws IOException {
        int ownerId = post.getOwnerId();
        int postId = postBase.addPost(post);
        boolean result = userBase.addLink(ownerId, postId);

    }

    @Override
    public Post[] getFeed(int userID) throws IOException {
        Integer[] postsIDs = userBase.getFeed(userID);
        ArrayList<Post> feed = new ArrayList<>();
        for (Integer i : postsIDs){
            Post post = postBase.getPost(i);
            feed.add(post);
        }
        return feed.toArray(new Post[feed.size()]);
    }

    @Override
    public User login(String login, String password) {
        User user;
        try {
            user = userBase.checkExistance(login, password);
            return user;
        }
        catch (IOException err){
            return null;
        }
    }

    @Override
    public User[] getSubscribers(int userID) {
        return new User[0];
    }

    @Override
    public User[] find(String login){
        try {
            return userBase.findShops(login);
        }
        catch (IOException err){
            return null;
        }
    }

    @Override
    public boolean subscribe(int subscriber, int subscription) {
        return userBase.subscribe(subscriber, subscription);
    }
}
