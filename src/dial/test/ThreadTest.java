package dial.test;
import dial.model.User;

class newThread implements Runnable{
    private User user;
    private Thread thread;
    private String threadName;

    public newThread(String threadName, User user){
        System.out.println("INIT");
        this.threadName = threadName;
        this.user = user;
    }

    public void run(){
        System.out.println("RUN");
        user.setUserName("New Thread Name");
    }

    public void start(){
        System.out.println("START");
        thread = new Thread(this, threadName);
        thread.start();
    }
}

public class ThreadTest {
    public static void main(String[] args) {
        User new_user = new User();

        new_user.setUserName("New User");
        newThread newthread = new newThread("new therad", new_user);
        newthread.start();
    }
}
