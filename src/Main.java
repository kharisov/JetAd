import adobjects.Post;
import adobjects.User;
import control.Control;
import model.DataBase;
import model.PostBase;
import model.UserBase;
import view.View;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Control control = new Control();
        View view = new View(control);
        control.useModel(DataBase.getInstance());
        control.useView(view);
        view.startSession();
    }
}
