package modelEx;
import model.Config;
import model.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

interface configUser{
    public void setConfig_UserName(String USER_NAME);
    public void setConfig_PassWord(String PASS_WORD);
    public void setConfig_DialRule(String DIAL_RULE);
    public String getConfig_UserName();
    public String getConfig_PassWord();
    public String getConfig_DialRule();
}

public class PropertiesConfig extends Config implements configUser{
    private Properties configObject;

    public PropertiesConfig(User user){
        super(user);
        configObject = new Properties();
    }

    public void initUserWithConfig(){
        user.setUserName(getConfig_UserName());
        user.setPassWord(getConfig_PassWord());
        user.setRule(getConfig_DialRule());
    }
    public void loadFromDisk(){
        try {
            configObject.load(getClass().getResourceAsStream("/config/config.properties"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void writeToDisk(){
        try {
            FileOutputStream propsOut = new FileOutputStream("/config/config.properties");
            configObject.store(propsOut, "UPDATED");
            propsOut.close();
            propsOut = null;
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setConfig_UserName(String USER_NAME){
        configObject.setProperty("USER_NAME", USER_NAME);
    }
    public void setConfig_PassWord(String PASS_WORD){
        configObject.setProperty("PASS_WORD", PASS_WORD);
    }
    public void setConfig_DialRule(String DIAL_RULE){
        configObject.setProperty("DIAL_RULE", DIAL_RULE);
    }
    public String getConfig_UserName(){
        return configObject.getProperty("USER_NAME");
    }
    public String getConfig_PassWord(){
        return configObject.getProperty("PASS_WORD");
    }
    public String getConfig_DialRule(){
        return configObject.getProperty("DIAL_RULE");
    }
}
