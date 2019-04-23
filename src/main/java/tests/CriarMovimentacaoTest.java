package tests;

import core.BaseTest;
import org.junit.Before;
import org.junit.Test;
import pages.CriarMovimentacaoPage;
import pages.HomePage;
import pages.LoginPage;

import static core.DriverFactory.getDriver;
import static org.junit.Assert.*;

public class CriarMovimentacaoTest {

    private CriarMovimentacaoPage criarMovimentacaoPage;
    private LoginPage loginPage;

    @Before
    public void initialSetUp(){
        criarMovimentacaoPage = new CriarMovimentacaoPage();
//        loginPage = new LoginPage();
//        loginPage.logIn();
        getDriver().get(criarMovimentacaoPage.url);
        isPageReady();
    }

    private void isPageReady(){
        assertEquals(criarMovimentacaoPage.url, getDriver().getCurrentUrl());
        assertTrue(criarMovimentacaoPage.getTipoMovimentacaoCombo().isDisplayed());
        assertTrue(criarMovimentacaoPage.getDataMovimentacaoDate().isDisplayed());
        assertTrue(criarMovimentacaoPage.getDataPagamentoDate().isDisplayed());
        assertTrue(criarMovimentacaoPage.getDescricaoText().isDisplayed());
        assertTrue(criarMovimentacaoPage.getInteressadoText().isDisplayed());
        assertTrue(criarMovimentacaoPage.getValorText().isDisplayed());
        assertTrue(criarMovimentacaoPage.getContaCombo().isDisplayed());
        assertTrue(criarMovimentacaoPage.getSituacaoPagoRadio().isDisplayed());
        assertTrue(criarMovimentacaoPage.getSituacaoPendenteRadio().isDisplayed());
        assertTrue(criarMovimentacaoPage.getSalvarButton().isDisplayed());
        assertTrue(criarMovimentacaoPage.getSituacaoPendenteRadio().isSelected());
    }

//    @Test
//    public void createMovimentacaoWithSuccess(){
//
//    }
//
//    @Test
//    public void createMovimentacaoWithAllEmptyField(){
//
//    }
//
//    @Test
//    public void createMovimetacaoWithOneEmptyFieldPerTry(){
//
//    }
}
