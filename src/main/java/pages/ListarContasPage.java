package pages;

import core.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static core.DriverFactory.getDriver;

public class ListarContasPage {

    private WebDriver driver = getDriver();
    public String url = Properties.baseUrl + "contas";

    // Elements -------------------------------------

    public WebElement getContasTable(){
        return driver.findElement(By.id("tabelaContas"));
    }

    public List<WebElement> getContasTableRows(){
        return getContasTable().findElements(By.xpath("./tr"));
    }

    public WebElement getConta(String contaName){
        WebElement conta = null;
        System.out.print("++++++++++++++++++++++" + contaName);
        for(WebElement contaRow : getContasTableRows()){
            if(contaRow.getText().equals(contaName)){
                conta = contaRow;
            }
        }
        return conta;
    }

    public WebElement getContaEditButton(String contaName){
        return getConta(contaName).findElement(By.xpath("./td[2]/a"));
    }

    public WebElement getContaRemoveButton(String contaName){
        return getConta(contaName).findElement(By.xpath("./td[2]/a[2]"));
    }

    public WebElement getSuccesMessageContaAdicionada(){
        return driver.findElement(By.xpath("//div[@class='alert alert-success']"));
    }


    // Actions ---------------------------------------




    // Behavior ----------------------------------------



}
