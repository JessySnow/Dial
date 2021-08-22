package dial.model;

public class Noti {
    protected User user;
    protected String notiInfo;

    public Noti(User user){
        this.user = user;
        this.notiInfo = "null";
    }

    public String getNotiInfo() {
        return notiInfo;
    }
    public void setNotiInfo(String notiInfo) {
        this.notiInfo = notiInfo;
    }
}