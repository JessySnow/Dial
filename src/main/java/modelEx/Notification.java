package modelEx;
import model.Noti;
import model.User;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.Toolkit;
import java.net.URL;

/**
 * Only can be used in a new Thread
 *
 * Do not initialize this class in Main Thread
 * cause so that the main thread won't get stuck
 */
public class Notification extends Noti{

    private final SystemTray systemTray;
    private final TrayIcon trayIcon;
    private final Image iconImage;

    public Notification(User user){
        super(user);
        systemTray = SystemTray.getSystemTray();
        URL icon = getClass().getResource("/images/TrayIcon.png");
        iconImage = Toolkit.getDefaultToolkit().getImage(icon);
        trayIcon = new TrayIcon(iconImage, "Dial");
        try {
            systemTray.add(trayIcon);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            icon = null;
        }
    }

    /**
     * Update Notification's type and info according to user's status
     */
    private void updateNoti(){
        if(user.getStaCode() == 0){
            notiInfo = "Already connected to Internet";
        }else{
            switch (user.getStaCode()){
                case(623):
                    notiInfo = "ERROR-623, 未找到拨号连接入口";
                    break;
                case(676):
                    notiInfo = "ERROR-676, 当前拨号连接正忙";
                    break;
                default:
                    notiInfo = "Unknown Error!";
            }
        }
    }

    /**
     * Using Java AWT API -- SystemTray
     * Using Java AWT API -- TrayIcon
     */
    public void showNoti(){
        updateNoti();
        trayIcon.displayMessage("Connection Status", notiInfo, user.getStatus().equals("INLINE")?
                                    MessageType.INFO : MessageType.ERROR);

        try{
            Thread.sleep(6500);
        }catch (Exception e){
            e.printStackTrace();
        }

        systemTray.remove(trayIcon);
    }
}