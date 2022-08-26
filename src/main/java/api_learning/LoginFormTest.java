package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

public class LoginFormTest {
    private static String NAV_LOGIN = "Login";
    private static String INPUT_EMAIL = "input-email";
    private static String INPUT_PASSWORD = "input-password";
    private static String LOGIN_BUTTON = "button-LOGIN";
    private static String EMAIL_ADDR = "tester@appium.com";
    private static String PASSWORD = "12345678";

    private static String SUCESS_ALERT = "android:id/alertTitle";

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = null;
        try {
            appiumDriver = DriverFactory.getDriver(Platform.android);

            MobileElement loginScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId(NAV_LOGIN));
            loginScreenBtnElem.click();

            MobileElement emailInputElem = appiumDriver.findElement(MobileBy.AccessibilityId(INPUT_EMAIL));
            MobileElement passwordInputElem = appiumDriver.findElement(MobileBy.AccessibilityId(INPUT_PASSWORD));
            MobileElement loginButtonElem = appiumDriver.findElement(MobileBy.AccessibilityId(LOGIN_BUTTON));

            emailInputElem.sendKeys(EMAIL_ADDR);
            passwordInputElem.sendKeys(PASSWORD);
            loginButtonElem.click();

            WebDriverWait wait = new WebDriverWait(appiumDriver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.id(SUCESS_ALERT)));

        } catch (Exception e) {
            e.printStackTrace();
        }
        appiumDriver.quit();
    }
}
