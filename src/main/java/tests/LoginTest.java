package tests;

import core.BaseTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pages.HomePage;
import pages.LoginPage;

import static core.DriverFactory.getDriver;
import static org.junit.Assert.*;

public class LoginTest extends BaseTest {

    /*
    Here is where the magic happens. This is ths class where you put your tests
    In the Tests Classes you don't interact with WebDriver, leave this to the Page Classes
     */

    private LoginPage loginPage;
    private HomePage homePage;

    @Before
    public void initialSetUp(){
        loginPage = new LoginPage();
        homePage = new HomePage();
        getDriver().get(loginPage.url);
        isPageReady();
    }

    private void isPageReady(){
        assertEquals(loginPage.url, getDriver().getCurrentUrl());
        assertTrue(loginPage.getEmailInput().isDisplayed());
        assertTrue(loginPage.getPasswordInput().isDisplayed());
        assertTrue(loginPage.getLoginButton().isDisplayed());
    }


    @Test
    public void loginWithSuccess(){
        loginPage.logIn("thiago.grespi90@gmail.com", "123456");
        assertEquals("https://srbarriga.herokuapp.com/logar", homePage.getCurrentURL());
        assertTrue(homePage.getWelcomeMessageText().isDisplayed());
        assertEquals("Bem vindo, Thiago Grespi Goulart!", homePage.getWelcomeMessageText().getText());
    }

    public void loginWithEmptyFields(){
        loginPage.logIn("","");
        assertTrue(loginPage.getErrorMessageEmailRequired().isDisplayed());
        assertEquals("Email é um campo obrigatório", loginPage.getErrorMessageEmailRequired().getText());
        assertTrue(loginPage.getErrorMessagePasswordRequired().isDisplayed());
        assertEquals("Senha é um campo obrigatório", loginPage.getErrorMessagePasswordRequired().getText());
    }

    public void loginWithIncorrectCredentials(){
        loginPage.logIn("thiago.grespi90@gmail.com","incorrect_pass");
        assertTrue(loginPage.getErrosMessageIncorrectCredentials().isDisplayed());
        assertEquals("Problemas com o login do usuário", loginPage.getErrosMessageIncorrectCredentials().getText());
    }
}
