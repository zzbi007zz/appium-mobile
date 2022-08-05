package tests.testng;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.GregorianCalendar;

public class TestParameter2 {
    @Test
    @Parameters({"uuid","systemPort"})
    public void testParameter(String uuid, String systemPort){
        System.out.println(new GregorianCalendar().getTime());
        System.out.printf("Uuuid: %s | systemPort: %s\n", uuid, systemPort);
    }
}
