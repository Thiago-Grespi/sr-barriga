package tests;

import core.BaseTest;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import pages.ListarResumoMensalPage;
import pages.LoginPage;

import static core.DriverFactory.getDriver;
import static org.junit.Assert.*;


public class ListarResumoMensalTest extends BaseTest {

    private ListarResumoMensalPage listarResumoMensalPage;
    private LoginPage loginPage;

    @Before
    public void initialSetUp(){
        listarResumoMensalPage = new ListarResumoMensalPage();
        loginPage = new LoginPage();
        login();
        getDriver().get(listarResumoMensalPage.url);
        isPageReady();
    }

    private void login(){
        getDriver().get(loginPage.url);
        JSONObject logInJsonData = null;
        logInJsonData = getJsonDataObject("LogInData", "valid");
        loginPage.logIn((String) logInJsonData.get("email"), (String) logInJsonData.get("pass"));
    }

    private void isPageReady() {
        assertEquals(listarResumoMensalPage.url, getDriver().getCurrentUrl());
        assertTrue(listarResumoMensalPage.getMesMovimentacaoCombo().isDisplayed());
        assertTrue(listarResumoMensalPage.getAnoMovimentacaoCombo().isDisplayed());
        assertTrue(listarResumoMensalPage.getBuscarButton().isDisplayed());
    }

    @Test
    public void checarMesAtualPreenchido(){
        JSONObject movimentacaoJsonData = getJsonDataObject("ListarResumoMensalData", "current");
        assertEquals(movimentacaoJsonData.get("mes"), listarResumoMensalPage.getMesMovimentacaoItems().getFirstSelectedOption().getText());
    }

    @Test
    public void checarAnoAtualPreenchido(){
        JSONObject movimentacaoJsonData = getJsonDataObject("ListarResumoMensalData", "current");
        assertEquals(movimentacaoJsonData.get("ano"), listarResumoMensalPage.getAnoMovimentacaoItems().getFirstSelectedOption().getText());
    }

    @Test
    public void listarMovimentacaoWithSuccess(){
        JSONObject movimentacaoJsonData = getJsonDataObject("ListarResumoMensalData", "list");
        listarResumoMensalPage.listarMovimentacaoMensal((String) movimentacaoJsonData.get("mes"), (String) movimentacaoJsonData.get("ano"));
        assertEquals(movimentacaoJsonData.get("descricaoMovimentacao"), listarResumoMensalPage.getMovimentacaoDescricaoColumn().getText());
        assertEquals(movimentacaoJsonData.get("dataPagamento"), listarResumoMensalPage.getMovimentacaoDataPagamentoColumn().getText());
        assertEquals(movimentacaoJsonData.get("conta"), listarResumoMensalPage.getMovimentacaoContaColumn().getText());
        assertEquals(movimentacaoJsonData.get("valor"), listarResumoMensalPage.getMovimentacaoValorColumn().getText());
        assertEquals(movimentacaoJsonData.get("situacao"), listarResumoMensalPage.getMovimentacaoSituacaoColumn().getText());
    }

    @Test
    public void removerMovimentacaoWithSuccess(){
        JSONObject movimentacaoJsonData = getJsonDataObject("ListarResumoMensalData", "remove");
        listarResumoMensalPage.listarMovimentacaoMensal((String) movimentacaoJsonData.get("mes"), (String) movimentacaoJsonData.get("ano"));
        listarResumoMensalPage.removerMovimentacao(movimentacaoJsonData.get("descricaoMovimentacao").toString());
        assertTrue(listarResumoMensalPage.getSuccesMessageMovimentacaoRemovida().isDisplayed());
        assertEquals(movimentacaoJsonData.get("successMessage"), listarResumoMensalPage.getSuccesMessageMovimentacaoRemovida().getText());
        assertNull(listarResumoMensalPage.getMovimentacao(movimentacaoJsonData.get("descricaoMovimentacao").toString()));
    }

}
