package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static core.DriverFactory.getDriver;

public class CriarMovimentacaoPage extends BasePage {

    private WebDriver driver = getDriver();
    public String url = "https://srbarriga.herokuapp.com/movimentacao";

    public void setTipoMovimentacao(String tipoMovimentacaoVisibleText){ //recebe a string exata do item que vai ser selecionado
        WebElement comboTipoMovimentacao = driver.findElement(By.id("tipo"));
        Select itemsTipoMovimentacao = new Select(comboTipoMovimentacao);
        itemsTipoMovimentacao.selectByVisibleText(tipoMovimentacaoVisibleText);
    }

    public String getSelectedTipoMovimentacao(){
        return driver.findElement(By.id("tipo")).getText();
    }

    public void setDataMovimentacao(String dataMovimentacao){
        driver.findElement(By.id("data_transacao")).sendKeys(dataMovimentacao);
    }

    public String getDataMovimentacaoFromInput(){
        return driver.findElement(By.id("data_transacao")).getText();
    }

    public void setDataPagamento(String dataPagamento){
        driver.findElement(By.id("data_pagamento")).sendKeys(dataPagamento);
    }

    public String getDataPagamentoFromInput(){
        return driver.findElement(By.id("data_pagamento")).getText();
    }

    public void setDescricao(String descricao){
        driver.findElement(By.id("decricao")).sendKeys(descricao);
    }

    public String getDescricaoFromInput(){
        return driver.findElement(By.id("descricao")).getText();
    }

    public void setInteressado(String interessado){
        driver.findElement(By.id("interessado")).sendKeys(interessado);
    }

    public String getInteressadoFromInput(){
        return driver.findElement(By.id("interessado")).getText();
    }

    public void setValor(Float valor){
        driver.findElement(By.id("valor")).sendKeys(valor.toString());
    }

    public String getValorFromInput(){
        return driver.findElement(By.id("valor")).getText();
    }

    public void setConta(String contaVisibleText){
        WebElement comboConta = driver.findElement(By.id("conta"));
        Select itemsComboConta = new Select(comboConta);
        itemsComboConta.selectByVisibleText(contaVisibleText);
    }

    public String getSelectedConta(){
        return driver.findElement(By.id("conta")).getText();
    }

    public void selectRadioPago(){
        driver.findElement(By.id("status_pago")).click();
    }

    public void selectRadioPendente(){
        driver.findElement(By.id("status_pendente")).click();
    }

    public void salvar(){
        driver.findElement(By.xpath("//button[@class='btn btn-primary'']")).click();
    }
}
