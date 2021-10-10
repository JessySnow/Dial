package modelEx;

import model.User;

public class DialThread implements Runnable{

    private Thread dialThread;
    private String threadName;
    private volatile User user;  /* Get form main thread */
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
        user.setStatus("DIAING");
        pbkObject.copyPbk();
        winCMD.rasdialRun();
        notification.showNoti();

        /* If connected to isp, save user's info to hard_disk */
        if(user.getStaCode() == 0){
            propertiesConfig.writeToDisk();
            user.setStatus("INLINE");
        }

        user.setStatus("OFFLINE");
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