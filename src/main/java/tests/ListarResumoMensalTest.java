package tests;

import core.BaseTest;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import pages.ListarResumoMensalPage;

import static core.DriverFactory.getDriver;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class ListarResumoMensalTest extends BaseTest {

    private ListarResumoMensalPage listarResumoMensalPage;

    @Before
    public void initialSetUp(){
        listarResumoMensalPage = new ListarResumoMensalPage();
        getDriver().get(listarResumoMensalPage.url);
        isPageReady();
    }

    private void isPageReady() {
        assertEquals(listarResumoMensalPage.url, getDriver().getCurrentUrl());
        assertTrue(listarResumoMensalPage.getMesMovimentacaoCombo().isDisplayed());
        assertTrue(listarResumoMensalPage.getAnoMovimentacaoCombo().isDisplayed());
        assertTrue(listarResumoMensalPage.getBuscarButton().isDisplayed());
    }

    @Test
    public void listarMovimentacaoWithSuccess(){
        JSONObject movimentacaoJsonData = getJsonDataObject("ListarResumoMensalData", "list");
        assertEquals(getJsonDataProperty(movimentacaoJsonData,"mes"), listarResumoMensalPage.getMesMovimentacaoItems().getFirstSelectedOption().getText());
        assertEquals(getJsonDataProperty(movimentacaoJsonData,"ano"), listarResumoMensalPage.getAnoMovimentacaoItems().getFirstSelectedOption().getText());
        listarResumoMensalPage.listarMovimentacaoMensal((String) movimentacaoJsonData.get("mes"), (String) movimentacaoJsonData.get("ano"));
        assertEquals(getJsonDataProperty(movimentacaoJsonData,"descricaoMovimentacao"), listarResumoMensalPage.getMovimentacaoDescricaoColumn().getText());
        assertEquals(getJsonDataProperty(movimentacaoJsonData,"dataPagamento"), listarResumoMensalPage.getMovimentacaoDataPagamentoColumn().getText());
        assertEquals(getJsonDataProperty(movimentacaoJsonData, "conta"), listarResumoMensalPage.getMovimentacaoContaColumn().getText());
        assertEquals(getJsonDataProperty(movimentacaoJsonData,"valor"), listarResumoMensalPage.getMovimentacaoValorColumn().getText());
        assertEquals(getJsonDataProperty(movimentacaoJsonData, "situacao"), listarResumoMensalPage.getMovimentacaoSituacaoColumn().getText());
    }

    @Test
    public void removerMovimentacaoWithSuccess(){
        JSONObject movimentacaoJsonData = getJsonDataObject("ListarResumoMensalData", "remove");
        listarResumoMensalPage.listarMovimentacaoMensal((String) movimentacaoJsonData.get("mes"), (String) movimentacaoJsonData.get("ano"));
        listarResumoMensalPage.removerMovimentacao(movimentacaoJsonData.get("descricaoMovimentacao").toString());
        assertTrue(listarResumoMensalPage.getSuccesMessageMovimentacaoRemovida().isDisplayed());
        assertEquals(getJsonDataProperty(movimentacaoJsonData,"successMessage"), listarResumoMensalPage.getSuccesMessageMovimentacaoRemovida().getText());
        assertNull(listarResumoMensalPage.getMovimentacao(movimentacaoJsonData.get("descricaoMovimentacao").toString()));
    }

}