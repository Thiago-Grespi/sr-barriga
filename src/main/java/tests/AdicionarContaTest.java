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

import java.nio.charset.Charset;
import java.util.NoSuchElementException;

import static core.DriverFactory.getDriver;
import static org.junit.Assert.*;

public class AdicionarContaTest extends BaseTest {

    private AdicionarContaPage adicionarContaPage;
    private ListarContasPage listarContasPage;

    @Before
    public void initialSetUp(){
        adicionarContaPage = new AdicionarContaPage();
        listarContasPage = new ListarContasPage();
        getDriver().get(adicionarContaPage.url);
        isPageReady();
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
        System.out.println("adicionarContaWithSuccess");
        JSONObject adicionarContaData = getJsonDataObject("AdicionarContaData", "valid");
        adicionarContaPage.adicionarConta((String)adicionarContaData.get("nome"));
        assertTrue(listarContasPage.getSuccesMessageContaAdicionada().isDisplayed());

        String successMessage = encodingAdaption(adicionarContaData, "successMessage");
        assertEquals(successMessage, listarContasPage.getSuccesMessageContaAdicionada().getText());
//        assertEquals(adicionarContaData.get("successMessage"), listarContasPage.getSuccesMessageContaAdicionada().getText());

        assertEquals(adicionarContaData.get("nome"), listarContasPage.getConta("Limpeza").getText());
    }

    @Test
    public void adicionarContaWithoutName(){
        System.out.println("adicionarContaWithoutName");
        JSONObject adicionarContaData = getJsonDataObject("AdicionarContaData", "empty");
        adicionarContaPage.adicionarConta((String)adicionarContaData.get("nome"));
        assertTrue(adicionarContaPage.getErrorMessageNomeRequired().isDisplayed());

        String errorMessageNomeRequired = encodingAdaption(adicionarContaData, "errorMessageNomeRequired");
        assertEquals(errorMessageNomeRequired, adicionarContaPage.getErrorMessageNomeRequired().getText());
//        assertEquals(adicionarContaData.get("errorMessageNomeRequired"), adicionarContaPage.getErrorMessageNomeRequired().getText());
    }
}
