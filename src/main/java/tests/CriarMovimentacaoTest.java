package tests;

import core.BaseTest;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import pages.CriarMovimentacaoPage;
import pages.HomePage;
import pages.LoginPage;
import java.nio.charset.Charset;

import java.util.Objects;

import static core.DriverFactory.getDriver;
import static org.junit.Assert.*;
import static pages.CriarMovimentacaoPage.*;
import static pages.CriarMovimentacaoPage.Situacoes.PAGO;
import static pages.CriarMovimentacaoPage.Situacoes.PENDENTE;

public class CriarMovimentacaoTest extends BaseTest{

    private CriarMovimentacaoPage criarMovimentacaoPage;
    private LoginPage loginPage;
    private HomePage homePage;

    @Before
    public void initialSetUp(){
        criarMovimentacaoPage = new CriarMovimentacaoPage();
        homePage = new HomePage();
        System.out.println("22222222222222");
//        loginForTests();
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

    private Situacoes setSituacao(String situacao){
        if(situacao.equals("PAGO")){
            return PAGO;
        }
        return PENDENTE;
    }

    @Test
    public void createMovimentacaoWithSuccess(){
        JSONObject movimentacaoJsonData = getJsonDataObject("CriarMovimentacaoData", "valid");
        criarMovimentacaoPage.createMovimentacao(
                (String) movimentacaoJsonData.get("tipoMovimentacao"),
                (String) movimentacaoJsonData.get("dataMovimentacao"),
                (String) movimentacaoJsonData.get("dataPagamento"),
                (String) movimentacaoJsonData.get("descricao"),
                (String) movimentacaoJsonData.get("interessado"),
                (String) movimentacaoJsonData.get("valor"),
                (String) movimentacaoJsonData.get("conta"),
                setSituacao((String) movimentacaoJsonData.get("situacao"))
        );
        assertEquals(homePage.url + "salvarMovimentacao", getDriver().getCurrentUrl());
        String successMessage = encodingAdaption(movimentacaoJsonData, "successMessage");
        assertEquals(successMessage , homePage.getSuccessMessageMovimentacaaoAdicionada().getText());
    }

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
