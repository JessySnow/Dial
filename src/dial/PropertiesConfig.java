package dial;
import dial.model.Config;
import dial.model.User;

import java.io.FileNotFoundException;
import java.util.Properties;
import java.io.FileInputStream;

public class PropertiesConfig extends Config{
    private String configPath = System.getProperty("user.dir") + "\\resources\\config\\config.properties";
    private Properties configObject;

    PropertiesConfig(User user){
        super(user);
    }
    
}
