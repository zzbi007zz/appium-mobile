package tests.authen;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.Test;
import platform.Platform;
import test_flows.authentication.LoginFlow;

import java.util.ArrayList;
import java.util.List;

public class LoginTest {
    @Test
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);
        List<LoginCred> loginCreds = new ArrayList<>();
        loginCreds.add(new LoginCred("user123", "12345678"));
        loginCreds.add(new LoginCred("test@mail.com", "1234567"));
        loginCreds.add(new LoginCred("test@mail.com", "12345678"));

        try {
            for (LoginCred loginCred : loginCreds) {
                LoginFlow loginFlow = new LoginFlow(appiumDriver,loginCred.getEmail(),loginCred.getPasssword());
                loginFlow.gotoLoginScreen();
                loginFlow.login();
                loginFlow.verifyLogin();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        appiumDriver.quit();
    }

    private static class LoginCred {
        private String email;
        private String passsword;

        public LoginCred(String email,String password) {
            this.email = email;
            this.passsword = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPasssword() {
            return passsword;
        }
    }
}
