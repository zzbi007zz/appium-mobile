package tests.testng;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class TestNGHooks01 extends BaseTestNG{


    @BeforeClass
    public void beforeClass(){
        System.out.println(this.getClass().getSimpleName() +" before class");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println(this.getClass().getSimpleName() +" before Method");
    }

    @Test(priority = 1, dependsOnMethods = {"test01"})
    public void test02() {
        System.out.println(this.getClass().getSimpleName() + "Test 02");
    }
    @Test (priority = 2)
    public void test01() {
        System.out.println(this.getClass().getSimpleName() + "Test 01");
    }

//    @Test
//    public void test03() {
//        System.out.println(this.getClass().getSimpleName() + "Test 03");
//        String expectedResult = "a";
//        String actualResult = "b";
////        Assert.assertEquals(actualResult,expectedResult);
////        Assert.assertEquals(actualResult,expectedResult,"[ERROR] NOT MATCH");
//
//        SoftAssert softAssert= new SoftAssert();
//        softAssert.assertEquals(actualResult,expectedResult);
//        softAssert.assertEquals(actualResult,expectedResult,"[ERROR] NOT MATCh 1");
//        softAssert.assertEquals(actualResult,expectedResult,"[ERROR] NOT MATCH!!!");
//        softAssert.assertAll();
//
//    }
    @AfterMethod
    public void afterMethod () {
        System.out.println(this.getClass().getSimpleName() + "After method");
    }
}
