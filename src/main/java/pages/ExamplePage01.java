package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static core.DriverFactory.getDriver;

public class ExamplePage01 extends BasePage {

    /*
    Here you will manipulate your website page. For this you send commands to Selenium, which send commands to
    WebDriver, which has the responsibility of controlling the browser.
     */

    private WebDriver driver = getDriver(); // Here we have the driver, got from the DriverFactory Class
    public String url = "thispage.com"; //the url of this page

    public String aPageMethodThatGetSomeText(){
        return driver.findElement(By.id("element-id")).getText();
    }

    public void typeSomeTextIntoInput(String niceText){
        driver.findElement(By.className("item-class")).sendKeys(niceText);
    }

    public String anotherPageMethodThatGetSomeText(){
        return driver.findElement(By.xpath("path/to/element")).getText();
    }

}
