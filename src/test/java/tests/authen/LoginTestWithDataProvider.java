package tests.authen;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import platform.Platform;
import test_flows.authentication.LoginFlow;

import java.util.ArrayList;
import java.util.List;

public class LoginTestWithDataProvider {

    @Test(dataProvider = "LoginCrdentialData")
    public void testLogin(LoginCred loginCred) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);
        try {
            LoginFlow loginFlow = new LoginFlow(appiumDriver,loginCred.getEmail(),loginCred.getPasssword());
            loginFlow.gotoLoginScreen();
            loginFlow.login();
            loginFlow.verifyLogin();
        }catch (Exception e) {
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

        @Override
        public String toString() {
            return "LoginCred{" +
                    "email='" + email + '\'' +
                    ", passsword='" + passsword + '\'' +
                    '}';
        }
    }

    @DataProvider
    public LoginCred[] LoginCrdentialData() {
        LoginCred loginCred01 = new LoginCred("user123", "12345678");
        LoginCred loginCred02 = new LoginCred("test@mail.com", "1234567");
        LoginCred loginCred03 = new LoginCred("test@mail.com", "12345678");
        return new LoginCred[]{loginCred01,loginCred02,loginCred03};
    }
}
