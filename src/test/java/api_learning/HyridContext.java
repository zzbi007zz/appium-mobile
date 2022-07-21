package api_learning;

import context.Contexts;
import context.WaitMoreThanOneContext;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.util.*;

public class HyridContext {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try{
            By webviewNavBtnSel = MobileBy.AccessibilityId("Webview");
            MobileElement webviewNavBtnElem = appiumDriver.findElement(webviewNavBtnSel);
            webviewNavBtnElem.click();

            WebDriverWait wait = new WebDriverWait(appiumDriver,15L);
            wait.until(new WaitMoreThanOneContext(appiumDriver));

            //print context
            for (String contextHandle : appiumDriver.getContextHandles()) {
                System.out.println(contextHandle);
            }
            //switch to webview
            appiumDriver.context(Contexts.WEB_VIEW);

            WebElement navToggleBtn = appiumDriver.findElementByCssSelector(".navbar__toggle");
            navToggleBtn.click();
            List<MobileElement> menuItemsElem = appiumDriver.findElementsByCssSelector(".menu__list li a");
            Map<String, String> menuItemDataMap = new HashMap<>();
            List<MenuItemData> menuItemDataList = new ArrayList<>();

            if (menuItemsElem.isEmpty()) {
                throw new RuntimeException("There is no item found in the list");
            }
            for (MobileElement menuItemElem : menuItemsElem) {
                String itemText = menuItemElem.getText();
                String itemURL = menuItemElem.getAttribute("href");
//                menuItemDataList.add(new MenuItemData(itemText,itemURL));

                if (itemText.isEmpty()) {
                    menuItemDataMap.put("Github",itemURL);
                    menuItemDataList.add(new MenuItemData("Github",itemURL));
                } else {
                    menuItemDataMap.put(itemText, itemURL);
                    menuItemDataList.add(new MenuItemData(itemText,itemURL));

                }
            }
            //Verification 
//            for (String itemText: menuItemDataMap.keySet()){
//                System.out.println("ItemText: " +itemText);
//                System.out.println("ItemURL: " + menuItemDataMap.get(itemText));
//            }

            for (MenuItemData menuItemData : menuItemDataList) {
                System.out.println(menuItemData);
            }

            //switch to native
            appiumDriver.context(Contexts.NATIVE);

//            Thread.sleep(1000);

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        appiumDriver.quit();
    }

    public static class MenuItemData {
        private String name;
        private String href;

        public MenuItemData(String name, String href) {
            this.name = name;
            this.href = href;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        @Override
        public String toString() {
            return "MenuItemData{" +
                    "name='" + name + '\'' +
                    ", href='" + href + '\'' +
                    '}';
        }
    }


}
