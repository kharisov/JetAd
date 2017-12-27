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
    private static final int DATA_HEIGHT_PIXELS = 700;
    private static final int WIDTH_PIXELS = 500;
    private static final String[] USER_TYPES = {"customer", "shop"};

    private JFrame frame = new JFrame();
    private JPanel dataPanel = new JPanel();
    private JButton showLoginButton = new JButton("Login");
    private JButton showRegisterButton = new JButton("Register");
    private JTextField loginField = new HintTextField("Login", 30);
    private JTextField passwordField = new HintTextField("Password", 30);
    private JComboBox<String> userTypeComboBox = new JComboBox<>(USER_TYPES);
    private JButton submitLoginButton = new JButton("Submit");
    private JButton submitRegisterButton = new JButton("Submit");
    private JTextField messageField = new JTextField();
    private JTextField userType = new JTextField(30);
    private boolean buttonsPanelAdded = false;
    private JPanel buttonsPanel = new JPanel();
    private JButton showMyProfileButton = new JButton("My profile");
    private JTextField searchField = new HintTextField("Enter shop name", 20);
    private JButton searchFieldButton = new JButton("Search");
    private JButton showFeedButton = new JButton("Show Feed");
    private JButton showPublicPostButton = new JButton("Public Post");
    private JTextField headerField = new HintTextField("Header", 30);
    private JButton submitPublicPostButton = new JButton("Submit");

    public View(AbstractControl control) {
        this.control = control;
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(WIDTH_PIXELS, HEIGHT_PIXELS);
        showLoginButton.addActionListener(new showLoginListener());
        showRegisterButton.addActionListener(new showRegisterListener());
        submitLoginButton.addActionListener(new submitLoginListener());
        submitRegisterButton.addActionListener(new submitRegisterListener());
        userTypeComboBox.setSelectedIndex(0);
        messageField.setEditable(false);
        showMyProfileButton.addActionListener(new showMyProfileListener());
        searchFieldButton.addActionListener(new searchListener());
        showFeedButton.addActionListener(new showFeedListener());
        showPublicPostButton.addActionListener(new showPublicPostListener());
        submitPublicPostButton.addActionListener(new submitPublicPostListener());
    }

    public void startSession() {
        dataPanel.setLayout(new GridBagLayout());
        dataPanel.setPreferredSize(new Dimension(WIDTH_PIXELS, HEIGHT_PIXELS));
        frame.add(BorderLayout.CENTER, dataPanel);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        dataPanel.add(showRegisterButton, c);
        c.gridy = 1;
        dataPanel.add(showLoginButton, c);
        frame.setVisible(true);
    }

    @Override
    public void update(Post[] data) {
        dataPanel.removeAll();
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        for (Post p : data) {
            JTextField header = new JTextField(p.getHeader(), 30);
            header.setEditable(false);
            dataPanel.add(header, c);
            c.gridy++;
        }
        frame.setVisible(true);
        frame.repaint();
    }

    @Override
    public void updateForCustomer(User data) {
        if (!buttonsPanelAdded) {
            buttonsPanel.setLayout(new GridBagLayout());
            buttonsPanel.setPreferredSize(new Dimension(WIDTH_PIXELS, HEIGHT_PIXELS - DATA_HEIGHT_PIXELS));
            buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            frame.add(BorderLayout.NORTH, buttonsPanel);
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridwidth = 1;
            buttonsPanel.add(showMyProfileButton, c);
            c.gridy = 1;
            buttonsPanel.add(searchField, c);
            c.gridx = 1;
            buttonsPanel.add(searchFieldButton, c);
            c.gridx = 0;
            c.gridy = 2;
            buttonsPanel.add(showFeedButton, c);
            dataPanel.setPreferredSize(new Dimension(WIDTH_PIXELS, DATA_HEIGHT_PIXELS));
            buttonsPanelAdded = true;
        }
        dataPanel.removeAll();
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        loginField.setEditable(false);
        loginField.setText(data.getLogin());
        dataPanel.add(loginField, c);
        c.gridy = 1;
        userType.setEditable(false);
        userType.setText("Customer");
        dataPanel.add(userType, c);
        frame.setVisible(true);
        frame.repaint();
    }

    @Override
    public void updateForShop(User data) {
        if (!buttonsPanelAdded) {
            buttonsPanel.setLayout(new GridBagLayout());
            buttonsPanel.setPreferredSize(new Dimension(WIDTH_PIXELS, HEIGHT_PIXELS - DATA_HEIGHT_PIXELS));
            buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            frame.add(BorderLayout.NORTH, buttonsPanel);
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridwidth = 1;
            buttonsPanel.add(showMyProfileButton, c);
            c.gridy = 1;
            buttonsPanel.add(showPublicPostButton, c);
            dataPanel.setPreferredSize(new Dimension(WIDTH_PIXELS, DATA_HEIGHT_PIXELS));
            buttonsPanelAdded = true;
        }
        dataPanel.removeAll();
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        loginField.setEditable(false);
        loginField.setText(data.getLogin());
        dataPanel.add(loginField, c);
        c.gridy = 1;
        userType.setEditable(false);
        userType.setText("Shop");
        dataPanel.add(userType, c);
        frame.setVisible(true);
        frame.repaint();
    }

    @Override
    public void update(User[] data) {
        dataPanel.removeAll();
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        for (User u : data) {
            JTextField login = new JTextField(u.getLogin(), 30);
            login.setEditable(false);
            JTextField type = new JTextField(30);
            type.setEditable(false);
            if (u.getType() == 0)
                type.setText("Customer");
            else
                type.setText("Shop");
            dataPanel.add(login, c);
            c.gridy++;
            dataPanel.add(type, c);
            c.gridy++;
            JButton button = new JButton("Subscribe");
            button.addActionListener(new subscribeListener(u.getId(), button));
            dataPanel.add(button, c);
            c.gridy++;
        }
        frame.setVisible(true);
        frame.repaint();
    }

    @Override
    public void updateMessage(String message) {
        dataPanel.removeAll();
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        messageField.setText(message);
        dataPanel.add(messageField, c);
        frame.setVisible(true);
        frame.repaint();
    }

    class showLoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dataPanel.removeAll();
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridwidth = 1;
            dataPanel.add(loginField, c);
            c.gridy = 1;
            dataPanel.add(passwordField, c);
            c.gridy = 2;
            dataPanel.add(submitLoginButton, c);
            frame.setVisible(true);
            frame.repaint();
        }
    }

    class showRegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dataPanel.removeAll();
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridwidth = 1;
            dataPanel.add(loginField, c);
            c.gridy = 1;
            dataPanel.add(passwordField, c);
            c.gridy = 2;
            dataPanel.add(userTypeComboBox, c);
            c.gridy = 3;
            dataPanel.add(submitRegisterButton, c);
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
            int type;
            if (((String) userTypeComboBox.getSelectedItem()).equals("customer"))
                type = User.CUSTOMER_TYPE;
            else
                type = User.SHOP_TYPE;
            control.register(loginField.getText(), passwordField.getText(),  type);
        }
    }

    class showMyProfileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            control.showMyProfile();
        }
    }

    class searchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            control.search(searchField.getText());
        }
    }

    class subscribeListener implements ActionListener {
        private int id;
        private JButton button;


        public subscribeListener(int id, JButton button) {
            this.id = id;
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            control.subscribe(id);
            button.setEnabled(false);
            button.setText("Subscribed");
            frame.repaint();
        }
    }

    class showFeedListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            control.showFeed();
        }
    }

    class showPublicPostListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dataPanel.removeAll();
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridwidth = 1;
            dataPanel.add(headerField, c);
            c.gridy = 1;
            dataPanel.add(submitPublicPostButton, c);
            frame.setVisible(true);
            frame.repaint();
        }
    }

    class submitPublicPostListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            control.publicPost(headerField.getText());
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
