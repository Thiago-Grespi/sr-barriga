package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

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
                case CHROME:
                    if(System.getProperty("os.name").startsWith("Windows")){
                        String chromedriverPath = System.getProperty("user.home") + File.separator + "drivers" + File.separator + "chromedriver.exe";
                        System.setProperty("webdriver.chrome.driver", chromedriverPath);
                    }
                    driver = new ChromeDriver(); break;
                case FIREFOX:
                    driver = new FirefoxDriver(); break;
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
