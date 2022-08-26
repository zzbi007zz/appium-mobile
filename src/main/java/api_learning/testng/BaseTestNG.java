package api_learning.testng;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseTestNG {

    @BeforeSuite
    public void beforeSuit(){
        System.out.println(this.getClass().getSimpleName() +" before suite");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println(this.getClass().getSimpleName() +" before Test");
    }
}
