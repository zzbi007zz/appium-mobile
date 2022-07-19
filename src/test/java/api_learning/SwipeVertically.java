package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.time.Duration;
import java.util.NoSuchElementException;

public class SwipeVertically {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);
        try {
            MobileElement swipeButtonElem = appiumDriver.findElement(MobileBy.AccessibilityId("Swipe"));
            swipeButtonElem.click();
            //Wait for element visible
            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
            wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Swipe horizontal\")")));

            Dimension windowSize = appiumDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            int xStartpoint = 50 * screenWidth / 100;
            int xEndpoint = 50 * screenWidth / 100;

            int yStartpoint = 30 * screenHeight / 100;
            int yEndPoint = 10 * screenHeight / 100;

            PointOption startPoint = new PointOption<>().withCoordinates(xStartpoint, yStartpoint);
            PointOption endPoint = new PointOption<>().withCoordinates(xEndpoint, yEndPoint);

            // Swipe up
            TouchAction touchAction = new TouchAction(appiumDriver);
            touchAction.
                    press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                    .moveTo(endPoint)
                    .release()
                    .perform();
            //Swipe down
            touchAction.
                    longPress(endPoint)
                    .moveTo(startPoint)
                    .release()
                    .perform();

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }


}
