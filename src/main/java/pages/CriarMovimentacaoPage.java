package pages;

import core.BasePage;
import core.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static core.DriverFactory.getDriver;

public class CriarMovimentacaoPage extends BasePage {

    private WebDriver driver = getDriver();
    public String url = Properties.baseUrl + "movimentacao";
    public enum Situacoes { PAGO, PENDENTE }

    // Elements -----------------------------------------------------------

    public WebElement getTipoMovimentacaoCombo(){
        return driver.findElement(By.id("tipo"));
    }

    public Select getTipoMovimentacaoItems() {
        return new Select(getTipoMovimentacaoCombo());
    }

    public WebElement getDataMovimentacaoDate(){
        return driver.findElement(By.id("data_transacao"));
    }

    public WebElement getDataPagamentoDate(){
        return driver.findElement(By.id("data_pagamento"));
    }

    public WebElement getDescricaoText(){
        return driver.findElement(By.id("descricao"));
    }

    public WebElement getInteressadoText(){
        return driver.findElement(By.id("interessado"));
    }

    public WebElement getValorText(){
        return driver.findElement(By.id("valor"));
    }

    public WebElement getContaCombo(){
        return driver.findElement(By.id("conta"));
    }

    public Select getContaItems(){
        return new Select(getContaCombo());
    }

    public WebElement getSituacaoPagoRadio(){
        return driver.findElement((By.id("status_pago")));
    }

    public WebElement getSituacaoPendenteRadio(){
        return driver.findElement(By.id("status_pendente"));
    }

    public WebElement getSalvarButton(){
        return driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
    }

    public WebElement getErrorMessageDataMovimentacaoRequired(){
        return driver.findElement(By.xpath("/html/body/div[1]/ul/li"));
    }

    public WebElement getErrorMessageDataPagamentoRequired(){
        return driver.findElement(By.xpath("/html/body/div[1]/ul/li[2]"));
    }

    public WebElement getErrorMessageDescricaoRequired(){
        return driver.findElement(By.xpath("/html/body/div[1]/ul/li[3]"));
    }

    public WebElement getErrorMessageInteressadoRequired(){
        return driver.findElement(By.xpath("/html/body/div[1]/ul/li[4]"));
    }

    public WebElement getErrorMessageValorRequired(){
        return driver.findElement(By.xpath("/html/body/div[1]/ul/li[5]"));
    }

    public WebElement getErrorMessageValorMustBeNumber(){
        return driver.findElement(By.xpath("/html/body/div[1]/ul/li[6]"));
    }

    // Actions --------------------------------------------------------

    public void setTipoMovimentacao(String tipoMovimentacaoVisibleText){ //recebe a string literal(visível) do item que vai ser selecionado
        getTipoMovimentacaoItems().selectByVisibleText(tipoMovimentacaoVisibleText);
    }

    public void setDataMovimentacao(String dataMovimentacao){
        getDataMovimentacaoDate().sendKeys(dataMovimentacao);
    }

    public void setDataPagamento(String dataPagamento){
        getDataPagamentoDate().sendKeys(dataPagamento);
    }

    public void setDescricao(String descricao){
        getDescricaoText().sendKeys(descricao);
    }

    public void setInteressado(String interessado){
        getInteressadoText().sendKeys(interessado);
    }

    public void setValor(String valor){
        getValorText().sendKeys(valor);
    }

    public void setConta(String contaVisibleText){
        getContaItems().selectByVisibleText(contaVisibleText);
    }

    public void setSituacao(String situacao){
        if (situacao.equals("PAGO")){
            getSituacaoPagoRadio().click();
        }
        getSituacaoPendenteRadio().click();
    }

    public void clickSalvar(){
        getSalvarButton().click();
    }

    // Behavior -----------------------------------------------------------

    public void createMovimentacao(
            String tipoMovimentacao,
            String dataMovimentacao,
            String dataPagamento,
            String descricao,
            String interessado,
            String valor,
            String contaVisibleText,
            String situacao
            ){
        setTipoMovimentacao(tipoMovimentacao);
        setDataMovimentacao(dataMovimentacao);
        setDataPagamento(dataPagamento);
        setDescricao(descricao);
        setInteressado(interessado);
        setValor(valor);
        setConta(contaVisibleText);
        setSituacao(situacao);
        clickSalvar();
    }

}
