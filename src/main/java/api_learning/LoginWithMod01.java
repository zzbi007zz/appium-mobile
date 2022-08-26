package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.pages.LoginScreenMod01;
import platform.Platform;

public class LoginWithMod01 {


    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);
        try {
            MobileElement navLoginScreen = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreen.click();

            LoginScreenMod01 loginScreenMod01 = new LoginScreenMod01(appiumDriver);
            loginScreenMod01.usernameSel().sendKeys("test@mail.com");
            loginScreenMod01.passwordSel().sendKeys("12345678");
            loginScreenMod01.loginBtnSel().click();

        } catch(Exception e)
        {e.printStackTrace();
        }
    }
}
