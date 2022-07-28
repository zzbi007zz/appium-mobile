package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavComponent;
import models.components.login.LoginFormComponent;
import models.pages.LoginScreen;
import platform.Platform;

public class LoginWithComponents {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);
        try {
            LoginScreen loginScreen = new LoginScreen(appiumDriver);
            BottomNavComponent bottomNavComponent = loginScreen.bottomNavComponent();
            LoginFormComponent loginFormComponent = loginScreen.loginFormComp();

            bottomNavComponent.clickOnLoginIcon();
            loginFormComponent.inputUsername("test@mail.com");
            loginFormComponent.inputPassword("12345678");
            loginFormComponent.clickOnLoginBtn();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
