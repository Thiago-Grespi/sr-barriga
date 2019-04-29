package pages;

import core.BasePage;
import core.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static core.DriverFactory.getDriver;

public class HomePage extends BasePage {

    private WebDriver driver = getDriver(); // Here we have the driver, got from the DriverFactory Class
    public String url = Properties.baseUrl; //the url of this page

    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }

    public WebElement getWelcomeMessageText(){
        return driver.findElement(By.xpath("//*[@class='alert alert-success']"));
    }

    public WebElement getSuccessMessageMovimentacaaoAdicionada(){
        return driver.findElement(By.xpath("//*[@class='alert alert-success']"));
    }

}
