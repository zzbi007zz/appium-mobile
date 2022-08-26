package api_learning.testng;

import org.testng.annotations.*;

public class TestNGHooks02 extends BaseTestNG{

    @BeforeClass
    public void beforeClass(){
        System.out.println(this.getClass().getSimpleName() +" before class");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println(this.getClass().getSimpleName() +" before Method");
    }

    @Test
    public void test01() {
        System.out.println(this.getClass().getSimpleName() + "Test 01");
    }
    @Test
    public void test02() {
        System.out.println(this.getClass().getSimpleName() + "Test 02");
    }
    @Test
    public void test03() {
        System.out.println(this.getClass().getSimpleName() + "Test 03");
    }

    @AfterMethod
    public void afterMethod () {
        System.out.println(this.getClass().getSimpleName() + "After method");
    }
}
