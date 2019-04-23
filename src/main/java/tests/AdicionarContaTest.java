package tests;

import core.BaseTest;
import org.junit.Before;
import org.junit.Test;
import pages.AdicionarContaPage;
import pages.HomePage;
import pages.LoginPage;

import java.security.PrivateKey;

import static core.DriverFactory.getDriver;
import static org.junit.Assert.*;

public class AdicionarContaTest extends BaseTest {

    private AdicionarContaPage adicionarContaPage;
    private LoginPage loginPage;
    private HomePage homePage;

    @Before
    public void initialSetUp(){
        adicionarContaPage = new AdicionarContaPage();
        homePage = new HomePage();
        loginPage = new LoginPage();
        getDriver().get(loginPage.url);
        loginPage.logIn("thiago.grespi90@gmail.com", "123456");
        getDriver().get(adicionarContaPage.url);
        isPageReady();
    }

    private void isPageReady() {
        assertEquals(adicionarContaPage.url, getDriver().getCurrentUrl());
        assertTrue(adicionarContaPage.getNomeText().isDisplayed());
        assertTrue(adicionarContaPage.getBtnSalvar().isDisplayed());
    }

    @Test
    public void adicionarContaWithSuccess(){
        adicionarContaPage.adicionarConta("Limpeza");
        assertTrue(homePage.getSuccesMessageContaAdicionada().isDisplayed());
        assertEquals("Conta adicionada com sucesso!", homePage.getSuccesMessageContaAdicionada().getText());
        assertTrue(homePage.getContasList().contains("Limpeza"));
    }



}
