package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import platform.Platform;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

public class TakingScreenShot {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);
        try {
            MobileElement navLoMobileElement = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoMobileElement.click();

            File base64ScreenShotData = appiumDriver.getScreenshotAs(OutputType.FILE);
            String fileLocation = System.getProperty("user.dir").concat("/screenshots").concat("LightScreen.png");
            FileUtils.copyFile(base64ScreenShotData,new File(fileLocation));

            //An Area
            MobileElement loginPhoneElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login-screen"));
            fileLocation = System.getProperty("user.dir").concat("/screenshots").concat("loginForm.png");
            base64ScreenShotData = appiumDriver.getScreenshotAs(OutputType.FILE);


        } catch (Exception e) {

        }
    }
}
