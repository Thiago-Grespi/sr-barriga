package tests;

import core.BaseTest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import pages.HomePage;
import pages.LoginPage;

import static core.DriverFactory.getDriver;
import static org.junit.Assert.*;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private JSONObject logInJsonData;

    @Before
    public void initialSetUp(){
        loginPage = new LoginPage();
        homePage = new HomePage();
        getDriver().get(loginPage.url);
        isPageReady();
    }

    private void isPageReady(){
        assertEquals(loginPage.url, getDriver().getCurrentUrl());
        assertTrue(loginPage.getEmailText().isDisplayed());
        assertTrue(loginPage.getPasswordText().isDisplayed());
        assertTrue(loginPage.getLoginButton().isDisplayed());
    }

    @Test
    public void loginWithSuccess(){
        logInJsonData = null;
        logInJsonData = getJsonDataObject("LogInData", "valid");
        loginPage.logIn((String) logInJsonData.get("email"), (String) logInJsonData.get("pass"));
        assertEquals(homePage.url + "logar", homePage.getCurrentURL());
        assertTrue(homePage.getWelcomeMessageText().isDisplayed());
        assertEquals(getJsonDataProperty(logInJsonData, "welcomeMessage"), homePage.getWelcomeMessageText().getText());
    }

    @Test
    public void loginWithEmptyFields(){
        logInJsonData = null;
        logInJsonData = getJsonDataObject("LogInData", "allEmpty");
        loginPage.logIn((String) logInJsonData.get("email"), (String) logInJsonData.get("pass"));
        assertTrue(loginPage.getErrorMessageEmailRequired().isDisplayed());
        assertEquals(getJsonDataProperty(logInJsonData, "errorMessageEmailRequired"),
                loginPage.getErrorMessageEmailRequired().getText());
        assertTrue(loginPage.getErrorMessagePasswordRequired().isDisplayed());
        assertEquals(getJsonDataProperty(logInJsonData, "errorMessageSenhaRequired"),
                loginPage.getErrorMessagePasswordRequired().getText());
    }

    @Test
    public void loginWithIncorrectCredentials(){
        logInJsonData = null;
        logInJsonData = getJsonDataObject("LogInData", "invalid");
        loginPage.logIn((String) logInJsonData.get("email"), (String) logInJsonData.get("pass"));
        assertTrue(loginPage.getErrosMessageIncorrectCredentials().isDisplayed());
        assertEquals(getJsonDataProperty(logInJsonData, "errorMessageIncorrectCredentials"),
                loginPage.getErrosMessageIncorrectCredentials().getText());
    }
}
