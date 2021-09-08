package modelEx;

import model.User;
import org.junit.jupiter.api.Test;


class DialThreadTest {

    @Test
    void start() {
        User user = new User();
        user.setUserName("123456");
        user.setPassWord("123456");
        user.setRule("123456");
        DialThread thread = new DialThread("DialThread", user);
        thread.start();
    }
}