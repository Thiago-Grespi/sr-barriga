package pages;

import core.BasePage;
import core.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static core.DriverFactory.getDriver;

public class ListarResumoMensalPage extends BasePage {

    private WebDriver driver = getDriver();
    public String url = Properties.baseUrl + "extrato";

    // Elements -----------------------------------------------------------
    public WebElement getMesMovimentacaoCombo() {
        return driver.findElement(By.id("mes"));
    }

    public Select getMesMovimentacaoItems() {
        return new Select(getMesMovimentacaoCombo());
    }

    public WebElement getAnoMovimentacaoCombo() {
        return driver.findElement(By.id("ano"));
    }

    public Select getAnoMovimentacaoItems() {
        return new Select(getAnoMovimentacaoCombo());
    }

    public WebElement getBuscarButton() {
        return driver.findElement(By.xpath("//input[@class='btn btn-primary']"));
    }

    public WebElement getMovimentacaoTable() {
        return driver.findElement(By.id("tabelaExtrato"));
    }

    public List<WebElement> getMovimentacaoTableRows() {
        return getMovimentacaoTable().findElements(By.xpath("./tbody/tr"));
    }

    public WebElement getMovimentacao(String movimentacaoDescricao) {
        for (WebElement movimentacaoRow : getMovimentacaoTableRows()) {
            if (movimentacaoRow.getText().substring(0, movimentacaoDescricao.length()).equals(movimentacaoDescricao)) {
                return movimentacaoRow;
            }
        }
        return null;
    }

    public WebElement getMovimentacaoRemoveLink(String descricaoMovimentacao) {
        return getMovimentacao(descricaoMovimentacao).findElement(By.xpath("./td[6]/a"));
    }

    public WebElement getSuccesMessageMovimentacaoRemovida() {
        return driver.findElement(By.xpath("//div[@class='alert alert-success']"));
    }

    public WebElement getMovimentacaoDescricaoColumn() {
        return driver.findElement(By.cssSelector("tabelaExtrato tr:nth-child(1), td:nth-child(1)"));
    }

    public WebElement getMovimentacaoDataPagamentoColumn() {
        return driver.findElement(By.cssSelector("tabelaExtrato tr:nth-child(1), td:nth-child(2)"));
    }

    public WebElement getMovimentacaoContaColumn() {
        return driver.findElement(By.cssSelector("tabelaExtrato tr:nth-child(1), td:nth-child(3)"));
    }

    public WebElement getMovimentacaoValorColumn() {
        return driver.findElement(By.cssSelector("tabelaExtrato tr:nth-child(1), td:nth-child(4)"));
    }

    public WebElement getMovimentacaoSituacaoColumn() {
        return driver.findElement(By.cssSelector("tabelaExtrato tr:nth-child(1), td:nth-child(5)"));
    }

    // Actions -----------------------------------------------------------
    public void setMesMovimentacao(String mesMovimentacaoVisibleText) {
        getMesMovimentacaoItems().selectByVisibleText(mesMovimentacaoVisibleText);
    }

    public void setAnoMovimentacao(String anoMovimentacaoVisibleText) {
        getAnoMovimentacaoItems().selectByVisibleText(anoMovimentacaoVisibleText);
    }

    public void clickBuscar() {
        getBuscarButton().click();
    }

    public void clickRemove(String descricaoMovimentacao) {
        getMovimentacaoRemoveLink(descricaoMovimentacao).click();
    }

    // Behavior -----------------------------------------------------------
    public void listarMovimentacaoMensal(String mesMovimentacaoVisibleText, String anoMovimentacaoVisibleText) {
        setMesMovimentacao(mesMovimentacaoVisibleText);
        setAnoMovimentacao(anoMovimentacaoVisibleText);
        clickBuscar();
    }

    public void removerMovimentacao(String descricaoMovimentacao) {
        //listarMovimentacaoMensal(mesMovimentacaoVisibleText, anoMovimentacaoVisibleText);
        clickRemove(descricaoMovimentacao);
    }

}
