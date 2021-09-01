package dial.test;
import dial.modelEx.Notification;
import dial.model.User;

public class NotiTest {
    private static User ZeroTwo = new User();
    private static Notification notification = new Notification(ZeroTwo);

    public static void main(String[] args) {
        notification.showNoti();
        System.out.println("############## END ##############");
    }
}
