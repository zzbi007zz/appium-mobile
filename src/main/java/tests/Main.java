package tests;

import com.google.common.reflect.ClassPath;
import driver.MobileCapabilityTypeEx;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import platform.Platform;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;

public class Main implements MobileCapabilityTypeEx {

    public static void main(String[] args) throws IOException {

        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        List<Class<?>> testClasses = new ArrayList<>();

        for (ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
            String classInfoName = info.getName();
            boolean startWithTestDot = classInfoName.startsWith("tests.");
            boolean isBaseTestClass = classInfoName.startsWith("tests.BaseTest");
            boolean isMainTestClass = classInfoName.startsWith("tests.Main");

            if (startWithTestDot && !isBaseTestClass && !isMainTestClass){
                testClasses.add(info.load());
            }
        }

        //Get platform
        String platformName = System.getProperty("platform");
//        String platformName = System.getenv("platform");
        if(platformName == null) {
            throw new IllegalArgumentException("Please provide platform via -Dplatform");
        }
        try {
            Platform.valueOf(platformName);
        } catch (Exception e) {
            throw new IllegalArgumentException("We do not support platform " + platformName + ":  "+ Arrays.toString(Platform.values()));
        }

        //Devices under test
        List<String> iPhoneDevicelist = Arrays.asList("iphone 13","iphone 12");
        List<String> androidDevicelist = Arrays.asList("emulator-5554","3300a7eea5f993b3");

        List<String> deviceList = platformName.equalsIgnoreCase("ios") ? iPhoneDevicelist : androidDevicelist;

        //Assign test classes into devices
        final int testNumbEachDevice = testClasses.size() / deviceList.size();
        Map<String,List<Class<?>>> desireCaps = new HashMap<>();

        for (int deviceIndex = 0; deviceIndex < deviceList.size(); deviceIndex++) {
            int startIndex = deviceIndex * testNumbEachDevice;
            boolean isLastDevice = deviceIndex == deviceList.size()-1;
            int endIndex = isLastDevice ? testClasses.size() : (startIndex + testNumbEachDevice);
            List<Class<?>> subTestList = testClasses.subList(startIndex, endIndex);
            desireCaps.put(deviceList.get(deviceIndex),subTestList);
        }
        //
       TestNG testNG = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("Regression");

        List<XmlTest> allTest = new ArrayList<>();
        for (String devicevName : desireCaps.keySet()) {
            XmlTest test = new XmlTest(suite);
            test.setName(devicevName);
            List<XmlClass> xmlClasses = new ArrayList<>();
            List<Class<?>> dedicatedClasses = desireCaps.get(devicevName);
            for (Class<?> dedicatedClass : dedicatedClasses) {
                xmlClasses.add(new XmlClass(dedicatedClass.getName()));
            }
            test.setXmlClasses(xmlClasses);
            test.addParameter(UDID,devicevName);
            test.addParameter(PLATFORM_NAME,platformName);
            test.addParameter(PLATFORM_VERSION,"15.0");
            test.addParameter(SYSTEM_PORT, String.valueOf(new SecureRandom().nextInt(1000) + 8300));
            allTest.add(test);

        }
        suite.setTests(allTest);
        suite.setParallel(XmlSuite.ParallelMode.TESTS);
        suite.setThreadCount(10);

        System.out.println(suite.toXml());

        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);

        testNG.setXmlSuites(suites);
        testNG.run();

    }
}
