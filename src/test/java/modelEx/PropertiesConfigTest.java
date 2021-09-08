package modelEx;
import model.User;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class PropertiesConfigTest {
    private static User user = new User();
    private static PropertiesConfig propertiesConfig = new PropertiesConfig(user);

    @Test
    void testA_LoadFromDisk() {
        propertiesConfig.loadFromDisk();
        assertEquals("@test_rule", propertiesConfig.getConfig_DialRule(), "Rule get from disk should be @test_rule");
    }

    @Test
    void testB_InitUserWithConfig() {
        propertiesConfig.initUserWithConfig();
        assertEquals("test_user", user.getUserName(), "User's name init by config object should be test_user");
        assertEquals("test_password", user.getPassWord(), "User's password init by config object should be test_password");
        assertEquals("@test_rule", user.getRule(), "User's rule init by config object should be @test_rule");
    }

    @Test
    void testC_WriteToDisk() {
        propertiesConfig.setConfig_DialRule("@new_rule");
        propertiesConfig.setConfig_PassWord("new_password");
        propertiesConfig.setConfig_UserName("new_user");
        propertiesConfig.writeToDisk();

        // reload after write config to see if change has been made
        propertiesConfig.loadFromDisk();
        assertEquals("@new_rule", propertiesConfig.getConfig_DialRule(), "New config rule in the disk");
        assertEquals("new_user", propertiesConfig.getConfig_UserName(), "New config user_name in the disk ");
        assertEquals("new_password", propertiesConfig.getConfig_PassWord(), "New config password in the disk");

        // Recover config file
        propertiesConfig.setConfig_UserName("test_user");
        propertiesConfig.setConfig_DialRule("@test_rule");
        propertiesConfig.setConfig_PassWord("test_password");
        propertiesConfig.writeToDisk();
    }
}