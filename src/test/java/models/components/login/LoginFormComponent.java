package models.components.login;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LoginFormComponent {
    private final AppiumDriver<MobileElement> appiumDriver;

    public LoginFormComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }


}
