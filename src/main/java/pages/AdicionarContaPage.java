package pages;

import core.BasePage;
import core.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static core.DriverFactory.getDriver;

public class AdicionarContaPage extends BasePage {

    private WebDriver driver = getDriver();
    public String url = Properties.baseUrl + "addConta";

    // Elements -------------------------------------------------

    public WebElement getNomeText(){
        return driver.findElement(By.id("nome"));
    }

    public WebElement getBtnSalvar(){
        return driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
    }

    public WebElement getErrorMessageNomeRequired(){
        return driver.findElement(By.xpath("//div[@class='alert alert-danger']"));
    }

    // Actions -------------------------------------------------

    private void setNome(String nome){
        getNomeText().sendKeys(nome);
    }

    private void clickSalvar(){
        getBtnSalvar().click();
    }

    // Behavior -------------------------------------------------

    public void adicionarConta(String nome){
        setNome(nome);
        clickSalvar();
    }

}
