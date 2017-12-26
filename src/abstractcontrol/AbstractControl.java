package abstractcontrol;

import abstractmodel.AbstractModel;
import abstractview.AbstractView;
import adobjects.User;

public abstract class AbstractControl {
    private AbstractView view;
    private AbstractModel model;
    private User currentUser;

    abstract public void useView(AbstractView view);
    abstract public void useModel(AbstractModel model);
    abstract public void endWork();
    abstract public void register(String login, String password, int type);
    abstract public void showFeed();
    abstract public void showProfile(int userID);
    abstract public void showMyProfile();
    abstract public void openPost(int postID);
    abstract public void search(String subject);
    abstract public void publicPost(int postID);
    abstract public void subscribe(int userID);
    abstract public void showSubscribers();
}
