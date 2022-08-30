package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import platform.Platform;

public class UiSelector {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);
        try {
            MobileElement navFormBtnElemnet
                    = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
            navFormBtnElemnet.click();

            MobileElement inputField =
                    appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().descriptionContains(\"text-input\")"));
            System.out.println(inputField.getText());

            MobileElement inputField2 =
                    appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().descriptionContains(\"input-text-result\")"));
            System.out.println(inputField2.isDisplayed()); //Verify is field visible

            MobileElement switchElem =
                    appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Select an item...\")"));
            System.out.println(switchElem.getText().toString()); //print text of dropdown list

            MobileElement activeButton =
                    appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().descriptionContains(\"button-Active\")"));
            activeButton.click();

            MobileElement activeMessage =
                    appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"This button is active\")"));
            System.out.println(activeMessage.getText().toString());

            MobileElement okButton =
                    appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"android:id/button1\")"));
            System.out.println(okButton.getText().toString());

            MobileElement cancelButton =
                    appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"android:id/button2\")"));
            System.out.println(cancelButton.getText().toString());
            cancelButton.click();

            MobileElement inActiveButton =
                    appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().descriptionContains(\"button-Inactive\")"));
            System.out.println(inActiveButton.isDisplayed()); // verify button is visible

        } catch (Exception e) {
            e.printStackTrace();
        }
        appiumDriver.quit();
    }
}


