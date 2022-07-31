package models.components.login;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class LoginFormComponent {

    private final AppiumDriver<MobileElement> appiumDriver;
    private final static By usernameSel= MobileBy.AccessibilityId("input-email");
    private final static By validUserPassword = MobileBy.id("android:id/alertTitle");
    private final static By incorrectEmail = MobileBy.xpath("//*[contains(@text,'Please enter a valid email address')]");
    private final static By incorrectPassword = MobileBy.xpath("//*[contains(@text,'Please enter at least 8 characters')]");
    private final static By passwordSel= MobileBy.AccessibilityId("input-password");
    private final static By loginBtnSel= MobileBy.AccessibilityId("button-LOGIN");

    public LoginFormComponent(AppiumDriver<MobileElement> appiumDriver)
    {

        this.appiumDriver = appiumDriver;
    }
    public void inputUsername(String usernameTxt) {
        if(!usernameTxt.isEmpty()) {
            MobileElement usernameElem = appiumDriver.findElement(usernameSel);
            usernameElem.clear();
            usernameElem.sendKeys(usernameTxt);
        }
    }
    public String getInvalidEmail() {
        return appiumDriver.findElement(incorrectEmail).getText();
    }
    public String getInvalidPassword() {
        return appiumDriver.findElement(incorrectPassword).getText();
    }
    public String validUserAndPassword() {
        return appiumDriver.findElement(validUserPassword).getText();
    }

    public void inputPassword(String passwordTxt) {
        if (!passwordTxt.isEmpty()) {
            MobileElement passwordElem = appiumDriver.findElement(passwordSel);
            passwordElem.clear();
            passwordElem.sendKeys(passwordTxt);
        }
    }
    public void clickOnLoginBtn() {
        appiumDriver.findElement(loginBtnSel).click();
    }



}
