package model;

import abstractmodel.AbstractModel;
import adobjects.Post;
import adobjects.User;

import java.io.IOException;

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

    public DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    @Override
    public void addUser(String login, String password, int type) {
        //userBase.addUser(login, password, type);
    }

    @Override
    public User getUser(int userID) {
        return null;
    }

    @Override
    public void addPost(Post post) {

    }

    @Override
    public Post[] getFeed(int userID) {
        return new Post[0];
    }

    @Override
    public void login(String login, String password) {

    }

    @Override
    public User[] getSubscribers(int userID) {
        return new User[0];
    }
}
