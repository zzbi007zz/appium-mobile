package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.LoginScreenMod02;
import platform.Platform;

public class LoginWithMod02 {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);
        try {
            LoginScreenMod02 loginWithMod02 = new LoginScreenMod02(appiumDriver);
            loginWithMod02.inputUsername("test@mail.com");
            loginWithMod02.inputPassword("12345678");
            loginWithMod02.clickOnLoginBtn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
