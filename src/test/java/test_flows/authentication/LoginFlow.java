package test_flows.authentication;

import com.sun.xml.internal.ws.developer.StreamingAttachment;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.login.LoginFormComponent;
import models.pages.LoginScreen;
import org.apache.commons.validator.routines.EmailValidator;
import org.testng.Assert;
import test_flows.BaseFlow;


public class LoginFlow extends BaseFlow {

    private String username;
    private String password;

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver,String username,String password) {
        super(appiumDriver);
        this.username = username;
        this.password = password;
    }

    public void  login() {
        LoginScreen loginScreen = new LoginScreen(appiumDriver);
        LoginFormComponent loginFormComponent = loginScreen.loginFormComp();
        if(!username.isEmpty()) {
            loginFormComponent.inputUsername(username);
        }

        if(!password.isEmpty()) {
            loginFormComponent.inputPassword(password);
        }

        loginFormComponent.clickOnLoginBtn();
    }

    public void verifyLogin() {
        LoginScreen loginScreen = new LoginScreen(appiumDriver);
        LoginFormComponent loginFormComp  = loginScreen.loginFormComp();

        boolean isEmailValidate = EmailValidator.getInstance().isValid(username);
        boolean isPasswordValid = password.length() >=8;

        if(isEmailValidate && isPasswordValid) {
            verifyCorrectCred(loginFormComp);
        }
        if(!isEmailValidate) {
            verifyIncorrectMail(loginFormComp);
        }
        if(!isPasswordValid) {
            verifyIncorrectPassword(loginFormComp);
        }
    }

    @Step("Verify login with correct username&password")
    private void verifyCorrectCred(LoginFormComponent loginFormComponent) {
        System.out.println("Verify correct credential");
        String actualValidUserPassword = loginFormComponent.validUserAndPassword();
        String expectValidUserPassword = "Success";
        System.out.println("actualValidUserPassword "+actualValidUserPassword);
        System.out.println("expectValidUserPassword "+expectValidUserPassword);
        Assert.assertEquals(actualValidUserPassword,expectValidUserPassword);
    }
    @Step("Verify login with incorrect email")
    private void verifyIncorrectMail(LoginFormComponent loginFormComponent) {
        System.out.println("Verify incorrect Email");
        String actualInvalidEmail = loginFormComponent.getInvalidEmail();
//        String expectInvalidEmail = "Please enter a valid email addres";
        String expectInvalidEmail = "Please enter a valid email address";
        Assert.assertEquals(actualInvalidEmail,expectInvalidEmail,"[ERROR] Invalid email string" );

    }
    @Step("Verify login with incorrect password")
    private void verifyIncorrectPassword(LoginFormComponent loginFormComponent) {
        System.out.println("Verify incorrect Password");
        String actualInvalidPassword = loginFormComponent.getInvalidPassword();
        String expectedInvalidPassword = "Please enter at least 8 characters";
        Assert.assertEquals(actualInvalidPassword,expectedInvalidPassword,"[ERROR] Invalid password string" );
    }
}
