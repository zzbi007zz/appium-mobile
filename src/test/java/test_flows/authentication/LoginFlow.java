package test_flows.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.login.LoginFormComponent;
import models.pages.LoginScreen;
import org.apache.commons.validator.routines.EmailValidator;
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

    private void verifyCorrectCred(LoginFormComponent loginFormComponent) {
        System.out.println("Verify correct credential");
        String actualValidUserPassword = loginFormComponent.validUserAndPassword();
        String expectValidUserPassword = "Success";
        System.out.println("actualValidUserPassword "+actualValidUserPassword);
        System.out.println("expectValidUserPassword "+expectValidUserPassword);

    }

    private void verifyIncorrectMail(LoginFormComponent loginFormComponent) {
        System.out.println("Verify incorrect Email");
        String actualInvalidEmail = loginFormComponent.getInvalidEmail();
        String expectInvalidEmail = "Please enter a valid email address";
        System.out.println("actualInvalidPassword: "+ actualInvalidEmail);
        System.out.println("expectedInvalidPassword: "+ expectInvalidEmail);
    }

    private void verifyIncorrectPassword(LoginFormComponent loginFormComponent) {
        System.out.println("Verify incorrect Password");
        String actualInvalidPassword = loginFormComponent.getInvalidPassword();
        String expectedInvalidPassword = "Please enter at least 8 characters";
        System.out.println("actualInvalidPassword: "+ actualInvalidPassword);
        System.out.println("expectedInvalidPassword: "+ expectedInvalidPassword);
    }
}
