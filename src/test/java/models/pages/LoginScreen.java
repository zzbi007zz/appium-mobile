package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavComponent;
import models.components.login.LoginFormComponent;
import org.openqa.selenium.By;

public class LoginScreen {
    private final AppiumDriver<MobileElement> appiumDriver;
    private final static By usernameSel= MobileBy.AccessibilityId("input-email");
    private final static By passwordSel= MobileBy.AccessibilityId("input-password");
    private final static By loginBtnSel= MobileBy.AccessibilityId("button-LOGIN");

    public LoginScreen(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public LoginFormComponent loginFormComp() {
        return new LoginFormComponent(appiumDriver);
    }

    public BottomNavComponent bottomNavComponent() {
        return  new BottomNavComponent(appiumDriver);
    }

    public MobileElement inputUsername() {
        return appiumDriver.findElement(usernameSel);
    }

    public MobileElement inputPassword() {
        return appiumDriver.findElement(passwordSel);
    }

    public MobileElement clickOnLoginBtn() {
        return appiumDriver.findElement(loginBtnSel);
    }

}
