package control;

import abstractcontrol.AbstractControl;
import abstractmodel.AbstractModel;
import abstractview.AbstractView;
import adobjects.Post;
import adobjects.User;

public class Control extends AbstractControl{
    private AbstractView view;
    private AbstractModel model;
    private User currentUser;

    public void useView(AbstractView view){

    }
    public void useModel(AbstractModel model){

    }
    public void endWork(){

    }
    public void register(String login, String password){

    }
    public void showFeed(){}
    public void showProfile(int userID){}
    public void showMyProfile(){
        showProfile(currentUser.getId());
    }
    public void openPost(int postID){}
    public void search(String subject){}
    public void publicPost(int postID){}
    public void subscribe(int userID){}
    public void showSubscribers(){}
}
