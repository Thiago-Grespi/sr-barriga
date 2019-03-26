package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    /*
    Here is where the WebDriver is born and dies
    The only one responsibility of this class is to manipulate the WebDriver
     */

    private static WebDriver driver;

    private DriverFactory() {}

    public static WebDriver getDriver(){
        if(driver == null){

            switch (Properties.browsers){
                case CHROME: driver = new ChromeDriver(); break;
                case FIREFOX: driver = new FirefoxDriver(); break;
            }

            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void killDriver(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }
}
