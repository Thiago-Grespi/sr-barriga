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
    JSONObject logInJsonData;

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
        assertEquals("https://srbarriga.herokuapp.com/logar", homePage.getCurrentURL());
        assertTrue(homePage.getWelcomeMessageText().isDisplayed());
        assertEquals("Bem vindo, Thiago Grespi Goulart!", homePage.getWelcomeMessageText().getText());
    }

    @Test
    public void loginWithEmptyFields(){
        logInJsonData = null;
        logInJsonData = getJsonDataObject("LogInData", "allEmpty");
        loginPage.logIn((String) logInJsonData.get("email"), (String) logInJsonData.get("pass"));
        assertTrue(loginPage.getErrorMessageEmailRequired().isDisplayed());
        assertEquals((String) logInJsonData.get("errorMessageEmailRequired"), loginPage.getErrorMessageEmailRequired().getText());
        assertTrue(loginPage.getErrorMessagePasswordRequired().isDisplayed());
        assertEquals((String) logInJsonData.get("errorMessageSenhaRequired"), loginPage.getErrorMessagePasswordRequired().getText());
    }

    @Test
    public void loginWithIncorrectCredentials(){
        logInJsonData = null;
        logInJsonData = getJsonDataObject("LogInData", "invalid");

        loginPage.logIn((String) logInJsonData.get("email"), (String) logInJsonData.get("pass"));
        assertTrue(loginPage.getErrosMessageIncorrectCredentials().isDisplayed());
        assertEquals((String) logInJsonData.get("errorMessageIncorrectCredentials"), loginPage.getErrosMessageIncorrectCredentials().getText());
    }
}
