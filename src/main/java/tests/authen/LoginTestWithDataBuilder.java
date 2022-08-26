package tests.authen;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import platform.Platform;
import test_data.DataObjectBuilder;
import test_data.models.LoginCred;
import test_flows.authentication.LoginFlow;

public class LoginTestWithDataBuilder {

    @Test(dataProvider = "loginCredData")
    public void testLogin(LoginCred loginCred) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);
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

    @DataProvider
    public LoginCred[] loginCredData() {
       String filePath = "src/main/java/test_data/authen/LoginCreds.json";
       return DataObjectBuilder.buildDataObject(filePath, LoginCred[].class);
//        LoginCred loginCred01 = new LoginCred("user123", "12345678");
//        LoginCred loginCred02 = new LoginCred("test@mail.com", "1234567");
//        LoginCred loginCred03 = new LoginCred("test@mail.com", "12345678");
//        return new LoginCred[]{loginCred01,loginCred02,loginCred03};
    }
}
