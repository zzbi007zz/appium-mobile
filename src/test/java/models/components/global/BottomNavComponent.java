package models.components.global;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class BottomNavComponent {

    private final AppiumDriver<MobileElement> appiumDriver;

    public BottomNavComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }


}
