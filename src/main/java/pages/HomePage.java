package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static core.DriverFactory.getDriver;

public class HomePage extends BasePage {

    private WebDriver driver = getDriver(); // Here we have the driver, got from the DriverFactory Class
    public String url = "https://srbarriga.herokuapp.com"; //the url of this page

    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }

    public WebElement getWelcomeMessageText(){
        return driver.findElement(By.xpath("//*[@class='alert alert-success']"));
    }

    public WebElement getSuccesMessageContaAdicionada(){
        return driver.findElement(By.xpath("//div[@class='alert alert-success']"));
    }

    public List<WebElement> getContasList(){
        return driver.findElements(By.xpath("//*[@id=\"tabelaContas\"]/tbody"));
    }

}
