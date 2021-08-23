package dial.model;

public class Config {
    protected User user;

    protected Config(User user){
        this.user = user;
    }

    public void loadFromDisk(){;}
    public void writeToDisk(){;}
    public void initUserWithConfig(){;}
}