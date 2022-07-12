package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import platform.Platform;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static platform.Platform.ANDROID;

public class DriverFactory implements MobileCapabilityTypeEx {

    public static AppiumDriver<MobileElement> getDriver(Platform platform){
        AppiumDriver<MobileElement> appiumDriver = null;

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(PLATFORM_NAME,"Android");
        desiredCapabilities.setCapability(AUTOMATION_NAME,"uiautomator2");
        desiredCapabilities.setCapability(UDID,"LMV409N0831cfe7");
        desiredCapabilities.setCapability(APP_PACKAGE,"com.wdiodemoapp");
        desiredCapabilities.setCapability(APP_ACTIVITY,"com.wdiodemoapp.MainActivity");
        URL appiumServer = null;
        try {
            appiumServer = new URL("http://localhost:4723/wd/hub");
        }
        catch(Exception e){
            e.printStackTrace();
        };

        if (appiumServer == null)
            throw new RuntimeException("Runtime construct the appium server url");


        switch (platform){
            case ANDROID:
                appiumDriver = new AndroidDriver<>(appiumServer, desiredCapabilities);
                break;
            case IOS:
                appiumDriver = new IOSDriver<>(appiumServer, desiredCapabilities);
                break;

        }
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return appiumDriver;
    }
}
