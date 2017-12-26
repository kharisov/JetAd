import adobjects.Post;
import adobjects.User;
import model.PostBase;
import model.UserBase;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        UserBase userBase = new UserBase("userBase");
        PostBase postBase = new PostBase("postBase");
        try {
            userBase.addUser("Damir", "1234", User.CUSTOMER_TYPE);
            System.out.println(userBase.checkExistance("Damir", "1234"));
            System.out.println(userBase.getUser(0));
            System.out.println(userBase.getUser(100));
            postBase.addPost(new Post("ZARA"));
            System.out.println(postBase.getPost(0));
            System.out.println(postBase.getPost(1));
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
