package api_learning;

import driver.DriverFactory;
import driver.MobileCapabilityTypeEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.internal.CapabilityHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.util.NoSuchElementException;

public class HandleVariantBehaviour implements MobileCapabilityTypeEx {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);
        try{
            Capabilities caps = appiumDriver.getCapabilities();
            String platform = CapabilityHelpers.getCapability(caps,PLATFORM_NAME,String.class);
            System.out.println(platform);

        }catch(NoSuchElementException e){e.printStackTrace();}
    }
}
