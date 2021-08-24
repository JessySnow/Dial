package dial.test;
import dial.model.User;
import dial.PropertiesConfig;

public class ConfigTEST {
    public static void main(String[] args) {
        User user = new User();
        PropertiesConfig props = new PropertiesConfig(user);
        props.loadFromDisk();
        props.initUserWithConfig();

        System.out.println(props.getConfig_DialRule());
        System.out.println(props.getConfig_PassWord());
        System.out.println(props.getConfig_UserName());

        System.out.println(user.getUserName());
        System.out.println(user.getPassWord());
        System.out.println(user.getRule());

        props.setConfig_DialRule("CMCC");
        props.setConfig_UserName("Jessy");
        props.setConfig_PassWord("7878");

        props.writeToDisk();
        props.initUserWithConfig();
        System.out.println(user.getRule());
        System.out.println(user.getPassWord());
    }
}
