package api_learning;

import driver.AppPackages;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.internal.CapabilityHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.time.Duration;
import java.util.NoSuchElementException;

public class HandleMultiApp {
    private static String NAV_LOGIN = "Login";
    private static String INPUT_EMAIL = "input-email";
    private static String INPUT_PASSWORD = "input-password";
    private static String LOGIN_BUTTON = "button-LOGIN";
    private static String EMAIL_ADDR = "tester@appium.com";
    private static String PASSWORD = "12345678";

    private static String SUCESS_ALERT = "android:id/alertTitle";

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);
        try{
            MobileElement loginScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId(NAV_LOGIN));
            loginScreenBtnElem.click();

            MobileElement emailInputElem = appiumDriver.findElement(MobileBy.AccessibilityId(INPUT_EMAIL));
            MobileElement passwordInputElem = appiumDriver.findElement(MobileBy.AccessibilityId(INPUT_PASSWORD));
            MobileElement loginButtonElem = appiumDriver.findElement(MobileBy.AccessibilityId(LOGIN_BUTTON));

            emailInputElem.sendKeys(EMAIL_ADDR);
            passwordInputElem.sendKeys(PASSWORD);
            loginButtonElem.click();

            // Put app to background till we call it back
            appiumDriver.runAppInBackground(Duration.ofSeconds(-1));

            //switch into another app
            appiumDriver.activateApp(AppPackages.SETTING);

            //Navigate to network list
            By wifiLabelSel = MobileBy.xpath("//*[@text='Network & internet']");
            appiumDriver.findElement(wifiLabelSel).click();

            By wifiStatusSel = MobileBy.id("android:id/switch_widget");
            By wifiToggleSel = MobileBy.className("android.widget.Switch");
            String wifiStatus= appiumDriver.findElement(wifiStatusSel).getText().trim();
            boolean isWifiOn = wifiStatus.equalsIgnoreCase("on");
            if(isWifiOn) appiumDriver.findElement(wifiStatusSel).click();

            appiumDriver.activateApp((AppPackages.WDIO));
            WebDriverWait wait = new WebDriverWait(appiumDriver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.id(SUCESS_ALERT)));

        }catch(NoSuchElementException e){e.printStackTrace();}
        appiumDriver.quit();
    }

}

