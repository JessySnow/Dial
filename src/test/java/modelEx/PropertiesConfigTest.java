package modelEx;
import model.User;
import modelEx.PropertiesConfig;

import static org.junit.jupiter.api.Assertions.*;

class PropertiesConfigTest {
    private static User user = new User();
    private static PropertiesConfig propertiesConfig = new PropertiesConfig(user);

    @org.junit.jupiter.api.Test
    void testLoadFromDisk() {
        propertiesConfig.loadFromDisk();
        assertEquals("@test", propertiesConfig.getConfig_DialRule(), "Rule get from disk should be @test");
    }

    @org.junit.jupiter.api.Test
    void testInitUserWithConfig() {
        propertiesConfig.initUserWithConfig();
        assertEquals("test_user", user.getUserName(), "User's name init by config object should be test_user");
        assertEquals("test_password", user.getPassWord(), "User's password init by config object should be test_password");
        assertEquals("@test", user.getRule(), "User's rule init by config object should be @test");
    }

    @org.junit.jupiter.api.Test
    void testWriteToDisk() {
        user.setUserName("new_user");
        user.setRule("@new_rule");
        user.setPassWord("new_password");
        propertiesConfig.writeToDisk();

        // reload after write config to see if change has been made
        propertiesConfig.loadFromDisk();
        assertEquals("@new_rule", propertiesConfig.getConfig_DialRule(), "New config rule in the disk");
        assertEquals("new_user", propertiesConfig.getConfig_UserName(), "New config user_name in the disk ");
        assertEquals("new_password", propertiesConfig.getConfig_PassWord(), "New config password in the disk");
    }
}