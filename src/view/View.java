package view;

import abstractview.AbstractView;
import adobjects.Post;
import adobjects.User;

import javax.swing.*;
import java.awt.*;


public class View extends AbstractView {
    private JFrame frame = new JFrame();

    public void startSession() {
        JTextField text = new JTextField("ASD");
        //frame.add(text);
        //text.setVisible(true);
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

    @Override
    public void updateError() {

    }
}
