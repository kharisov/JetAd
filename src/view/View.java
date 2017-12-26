package view;

import abstractcontrol.AbstractControl;
import abstractview.AbstractView;
import adobjects.Post;
import adobjects.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class View extends AbstractView {
    private static final int HEIGHT_PIXELS = 1000;
    private static final int WIDTH_PIXELS = 500;
    private static final String[] USER_TYPES = {"customer", "shop"};

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JButton showLoginButton = new JButton("Login");
    private JButton showRegisterButton = new JButton("Register");
    private JTextField loginField = new HintTextField("Login", 30);
    private JTextField passwordField = new HintTextField("Password", 30);
    private JComboBox<String> userTypeComboBox = new JComboBox<>(USER_TYPES);
    private JButton submitLoginButton = new JButton("Submit");
    private JButton submitRegisterButton = new JButton("Submit");

    public View(AbstractControl control) {
        this.control = control;
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(WIDTH_PIXELS, HEIGHT_PIXELS);
        showLoginButton.addActionListener(new showLoginListener());
        showRegisterButton.addActionListener(new showRegisterListener());
        submitLoginButton.addActionListener(new submitLoginListener());
        submitRegisterButton.addActionListener(new submitRegisterListener());
        userTypeComboBox.setSelectedIndex(0);
    }

    public void startSession() {
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(WIDTH_PIXELS, HEIGHT_PIXELS));
        frame.add(BorderLayout.CENTER, panel);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        panel.add(showLoginButton, c);
        c.gridy = 1;
        panel.add(showRegisterButton, c);
        frame.setVisible(true);
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

    class showLoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridwidth = 1;
            panel.add(loginField, c);
            c.gridy = 1;
            panel.add(passwordField, c);
            c.gridy = 2;
            panel.add(submitLoginButton);
            frame.setVisible(true);
            frame.repaint();
        }
    }

    class showRegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridwidth = 1;
            panel.add(loginField, c);
            c.gridy = 1;
            panel.add(passwordField, c);
            c.gridy = 2;
            panel.add(userTypeComboBox, c);
            c.gridy = 3;
            panel.add(submitLoginButton);
            frame.setVisible(true);
            frame.repaint();
        }
    }

    class submitLoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            control.login(loginField.getText(), passwordField.getText());
        }
    }

    class submitRegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            control.register(loginField.getText(), passwordField.getText(),  Integer.parseInt((String) userTypeComboBox.getSelectedItem()));
        }
    }

    class HintTextField extends JTextField implements FocusListener {
        private final String hint;
        private boolean showingHint;

        public HintTextField(final String hint) {
            super(hint);
            this.hint = hint;
            this.showingHint = true;
            super.addFocusListener(this);
        }

        public HintTextField(final String hint, int columns) {
            super(hint, columns);
            this.hint = hint;
            this.showingHint = true;
            super.addFocusListener(this);
        }

        @Override
        public void focusGained(FocusEvent e) {
            if( this.getText().isEmpty()) {
                super.setText("");
                showingHint = false;
            }
        }
        @Override
        public void focusLost(FocusEvent e) {
            if (this.getText().isEmpty()) {
                super.setText(hint);
                showingHint = true;
            }
        }

        @Override
        public String getText() {
            return showingHint ? "" : super.getText();
        }
    }
}
