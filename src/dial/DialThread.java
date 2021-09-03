package dial;

import dial.modelEx.WinCMD;
import dial.model.User;
import dial.modelEx.Notification;
import dial.modelEx.PropertiesConfig;
import dial.modelEx.pbkOp;

public class DialThread implements Runnable{

    private Thread dialThread;
    private String threadName;
    private User user;  /* Get form main thread */
    private WinCMD winCMD ; /* Create in this thread */
    private Notification notification; /* Create in this thread */
    private PropertiesConfig propertiesConfig;
    private pbkOp pbkObject;

    public DialThread(String threadName, User user){
        this.threadName = threadName;
        this.user = user;
        winCMD = new WinCMD(user);
        notification = new Notification(user);
        propertiesConfig = new PropertiesConfig(user);
        pbkObject = new pbkOp();
    }

    @Override
    public void run() {
        pbkObject.copyPbk();
        winCMD.rasdialRun();
        notification.showNoti();

        /* If connected to isp, save user's info to hard_disk */
        if(user.getStaCode() == 0){
            propertiesConfig.writeToDisk();
        }

        winCMD = null;
        propertiesConfig = null;
        pbkObject = null;
    }

    /* Entrance of  this thread */
    public void start(){
        if(dialThread == null){
            dialThread = new Thread(this, threadName);
            dialThread.start();
        }
    }
}