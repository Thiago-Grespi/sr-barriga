package tests;

import core.BaseTest;
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
        getDriver().get(loginPage.url);
        loginPage.logIn("thiago.grespi90@gmail.com", "123456");
        getDriver().get(adicionarContaPage.url);
        isPageReady();
    }

    @After
    public void testFinalization(){

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
