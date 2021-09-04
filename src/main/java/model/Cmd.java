package model;


/**
 * In this class, the main job is to
 * initialize the Runtime object
 */
public class Cmd {
    protected User user;
    protected String Command;
    protected Runtime WindowsEV;

    public Cmd(User user){
        this.user = user;
        this.Command = "rasdial PPPOE " + user.getUserName() + " " + user.getPassWord();
        WindowsEV = Runtime.getRuntime();
    }

    public void rasdialRun(){;}
}
