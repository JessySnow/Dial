package dial.test;
import dial.modelEx.Notification;
import dial.model.User;
import dial.modelEx.WinCMD;

public class CMDTEST {
    public static void main(String[] args) {
        User user = new User();
        Notification notification = new Notification(user);
        WinCMD CMD = new WinCMD(user);

        CMD.rasdialRun();

        notification.showNoti();


        System.out.println("### TEST END! ###");
    }
}
