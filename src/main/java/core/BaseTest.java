package core;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;
import static core.Properties.CLOSE_BROWSER_BETWEEN_TESTS;

public class BaseTest {

    /*
    Here you can add some behavior that tests class will share.
    Below, we have some methods with some punctual behavior to help and example what you can do
    */


    // ========================= Properties ==================

    @Rule
    public TestName testName = new TestName();


    // ========================= Before ==================

    @BeforeClass // indicates that this method needs to be executed before the class execution
    public static void doSomethingBeforeClassExecution(){
        // here you do things that ALL tests of the class need to run
    }

    @Before // indicates that this method needs to be executed before every @Test annotated method
    public void doSomethingBeforeEveryTest(){
        /*
        * here you can do some initialization code
        * data mass control
        * WebDriver start
        */
    }

    // ========================= After ==================

    @After // indicates that this method needs to be executed after every @Test annotated method
    public void doSomethingAfterEveryTest() throws IOException {

        screenCapture();
        if(CLOSE_BROWSER_BETWEEN_TESTS){
            killDriver();
        }
    }

    @AfterClass // indicates that this method needs to be executed after the class execution
    public static void doSomethingAfterClassExecution(){
        // here you do things that you need after execution of all tests of the class
    }

    // ========================= Methods ==================

    private void screenCapture() throws IOException {
        TakesScreenshot screenShot = (TakesScreenshot) getDriver();
        File screenImage = screenShot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenImage, new File("target" + File.separator + "screenshot" + File.separator + testName + ".jpg"));
    }
}
