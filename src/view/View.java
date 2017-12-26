package view;

import abstractview.AbstractView;
import adobjects.Post;
import adobjects.User;

import javax.swing.*;
import java.awt.*;


public class View extends AbstractView {
    private JFrame frame = new JFrame();
    private JPanel canvas = new Canvas();

    public void startSession() {

    }

    @Override
    public void update(Post[] data) {

    }

    @Override
    public void update(User data) {

    }

    @Override
    public void update(User[] data) {

    }
}
