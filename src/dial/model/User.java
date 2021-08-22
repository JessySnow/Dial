package dial.model;

public class User {
    /**
     * Status:
     * OFFLINE
     * INLINE
     * DIAING
     */
    private String userName;
    private String passWord;
    private String Status;
    private String Rule;
    private int staCode;


    public User(){
        this.userName = "stu";
        this.passWord = "123";
        this.Rule = "@null";
        this.Status = "OFFLINE";
        this.staCode = -1;
    }
    public String getUserName(){
        return userName;
    }
    public String getPassWord(){
        return passWord;
    }
    public String getRule(){
        return Rule;
    }
    public String getStatus(){
        return Status;
    }
    public int getStaCode() {
        return staCode;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setPassWord(String passWord){
        this.passWord = passWord;
    }
    public void setRule(String Rule){
        this.Rule = Rule;
    }
    public void setStatus(String Status){
        this.Status = Status;
    }
    public void setStaCode(int staCode) {
        this.staCode = staCode;
    }
}