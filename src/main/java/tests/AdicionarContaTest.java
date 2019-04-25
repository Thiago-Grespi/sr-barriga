package tests;

import core.BaseTest;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.AdicionarContaPage;
import pages.HomePage;
import pages.ListarContasPage;
import pages.LoginPage;

import java.security.PrivateKey;

import static core.DriverFactory.getDriver;
import static org.junit.Assert.*;

public class AdicionarContaTest extends BaseTest {

    private AdicionarContaPage adicionarContaPage;
    private LoginPage loginPage;
    private ListarContasPage listarContasPage;

    @Before
    public void initialSetUp(){
        adicionarContaPage = new AdicionarContaPage();
        listarContasPage = new ListarContasPage();
        loginPage = new LoginPage();
        login();
        getDriver().get(adicionarContaPage.url);
        isPageReady();
    }

    private void login(){
        getDriver().get(loginPage.url);
        JSONObject logInJsonData = null;
        logInJsonData = getJsonDataObject("LogInData", "valid");
        loginPage.logIn((String) logInJsonData.get("email"), (String) logInJsonData.get("pass"));
    }

    @After
    public void testFinalization(){
        listarContasPage.removeConta("Limpeza");
    }

    private void isPageReady() {
        assertEquals(adicionarContaPage.url, getDriver().getCurrentUrl());
        assertTrue(adicionarContaPage.getNomeText().isDisplayed());
        assertTrue(adicionarContaPage.getBtnSalvar().isDisplayed());
    }

    @Test
    public void adicionarContaWithSuccess(){
        adicionarContaPage.adicionarConta("Limpeza");
        assertTrue(listarContasPage.getSuccesMessageContaAdicionada().isDisplayed());
        assertEquals("Conta adicionada com sucesso!", listarContasPage.getSuccesMessageContaAdicionada().getText());
        assertEquals("Limpeza", listarContasPage.getConta("Limpeza").getText());
    }



}
