package abstractview;

import abstractcontrol.AbstractControl;
import adobjects.Post;
import adobjects.User;

public abstract class AbstractView {
    private AbstractControl controller;

    abstract public void update(Post[] data);
    abstract public void update(User data);
    abstract public void update(User[] data);
}
