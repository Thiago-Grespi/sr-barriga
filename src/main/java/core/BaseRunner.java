package core;

import org.junit.AfterClass;

import static core.DriverFactory.killDriver;
import static core.Properties.CLOSE_BROWSER_AFTER_ALL_TESTS;

public class BaseRunner {

    /*
    Here is the space you can use to put some code that all runners will need
     */

    @AfterClass // finish the WebDriver instance after the end of all tests
    public static void finishAll(){
        if(CLOSE_BROWSER_AFTER_ALL_TESTS ){
            killDriver();
        }
    }
}
