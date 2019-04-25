package tests;

import com.google.gson.JsonObject;
import core.BaseTest;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.AdicionarContaPage;
import pages.HomePage;
import pages.ListarContasPage;
import pages.LoginPage;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.NoSuchElementException;

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
        JSONObject adicionarContaData = getJsonDataObject("AdicionarContaData", "valid");
        try{
            listarContasPage.removeConta((String)adicionarContaData.get("nome"));
        }
        catch (NoSuchElementException e){
//            e.printStackTrace();
        }
        catch (RuntimeException e){
//            e.printStackTrace();
        }
    }

    private void isPageReady() {
        assertEquals(adicionarContaPage.url, getDriver().getCurrentUrl());
        assertTrue(adicionarContaPage.getNomeText().isDisplayed());
        assertTrue(adicionarContaPage.getBtnSalvar().isDisplayed());
    }

    @Test
    public void adicionarContaWithSuccess(){
        JSONObject adicionarContaData = getJsonDataObject("AdicionarContaData", "valid");
        adicionarContaPage.adicionarConta((String)adicionarContaData.get("nome"));
        assertTrue(listarContasPage.getSuccesMessageContaAdicionada().isDisplayed());
        assertEquals(adicionarContaData.get("successMessage"), listarContasPage.getSuccesMessageContaAdicionada().getText());
        assertEquals(adicionarContaData.get("nome"), listarContasPage.getConta("Limpeza").getText());
    }

    @Test
    public void adicionarContaWithoutName(){
        JSONObject adicionarContaData = getJsonDataObject("AdicionarContaData", "empty");
        adicionarContaPage.adicionarConta((String)adicionarContaData.get("nome"));
        assertTrue(adicionarContaPage.getErrorMessageNomeRequired().isDisplayed());
        assertEquals(adicionarContaData.get("errorMessageNomeRequired"), adicionarContaPage.getErrorMessageNomeRequired().getText());
    }
}
