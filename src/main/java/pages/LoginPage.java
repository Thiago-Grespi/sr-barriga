package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static core.DriverFactory.getDriver;

public class LoginPage extends BasePage {

    private WebDriver driver = getDriver();
    public String url = "https://srbarriga.herokuapp.com/login";


    // Elements -----------------------------------------------------------

    public WebElement getEmailInput(){
        return driver.findElement(By.id("email"));
    }

    public WebElement getPasswordInput(){
        return driver.findElement(By.id("senha"));
    }

    public WebElement getLoginButton(){
        return driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
    }

    public WebElement getErrorMessagePasswordRequired(){
        return driver.findElement(By.xpath("/html/body/div[2]"));
    }

    public WebElement getErrorMessageEmailRequired(){
        return driver.findElement(By.xpath("/html/body/div[1]"));
    }

    public WebElement getErrosMessageIncorrectCredentials(){
        return driver.findElement(By.xpath("/html/body/div[1]"));
    }

    // Actions --------------------------------------------------------

    public void setEmail(String email){
        getEmailInput().sendKeys(email);
    }

    public void setPassword(String pass){
        getPasswordInput().sendKeys(pass);
    }

    public void clicklogIn(){
        getLoginButton().click();
    }

    // Behavior -----------------------------------------------------------

    public void logIn(String email, String pass){
        setEmail(email);
        setPassword(pass);
        clicklogIn();
    }

}