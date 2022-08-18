package tests;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import platform.Platform;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BaseTest {

    private static final List<DriverFactory> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverThread;

    private String udid;
    private String systemPort;
    private String platformName;
    private String platformVersion;


    protected AppiumDriver<MobileElement> getDriver() {
        return driverThread.get().getDriver(Platform.valueOf(platformName), udid, systemPort, platformVersion);
    }

    @BeforeTest
    @Parameters({"udid", "systemPort","platformName","platformVersion"})
    public void initAppiumSession(String udid, String systemPort,String platform,@Optional("platformVersion") String platformVersion) {
        this.udid = udid;
        this.systemPort = systemPort;
        this.platformName = platformName;
        this.platformVersion = platformVersion;
       driverThread = ThreadLocal.withInitial(()-> {
            DriverFactory driverThread = new DriverFactory();
            driverThreadPool.add(driverThread);
            return driverThread;
       });
    }

    @AfterTest(alwaysRun = true)
    public void quitAppiumSession() {
       driverThread.get().quitAppiumDriver();
    }


    public void captureScreenShot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String testMethodName = result.getName();

            Calendar calendar = new GregorianCalendar();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH) + 1;
            int d = calendar.get(Calendar.DATE);
            int hr = calendar.get(Calendar.HOUR);
            int mm = calendar.get(Calendar.MINUTE);
            int sec = calendar.get(Calendar.SECOND);
            String takenTime = y + "_" + m + "_" + d + "_" + hr + "_" + mm + "_" + sec + "_";

            String fileName = testMethodName + "_" + takenTime + ".png";
            String fileLocation = System.getProperty("user.dir") + "/screenshots/" + fileName;

            File screenShotBase64 = getDriver().getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenShotBase64, new File(fileLocation));
                Path screenshotContentPath = Paths.get(fileLocation);
                InputStream inputStream = Files.newInputStream(screenshotContentPath);
                Allure.addAttachment(testMethodName, inputStream);
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
