package core;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;
import static core.Properties.CLOSE_BROWSER_BETWEEN_TESTS;

public class BaseTest {

    // ========================= Properties ==================

    @Rule
    public TestName testName = new TestName();

    JSONParser parser = new JSONParser();


    // ========================= Before ==================

    @BeforeClass // indicates that this method needs to be executed before the class execution
    public static void doSomethingBeforeClassExecution() {
        // here you do things that ALL tests of the class need to run
    }

    @Before // indicates that this method needs to be executed before every @Test annotated method
    public void doSomethingBeforeEveryTest() {
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
        if (CLOSE_BROWSER_BETWEEN_TESTS) {
           killDriver();
        }
    }

    @AfterClass
    public static void doSomethingAfterClassExecution() {
        getDriver().quit();
    }

    // ========================= Methods ==================

    private void screenCapture() throws IOException {
        TakesScreenshot screenShot = (TakesScreenshot) getDriver();
        File screenImage = screenShot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenImage, new File("target" + File.separator + "screenshot" + File.separator + testName + ".jpg"));
    }

    protected JSONObject getJsonDataObject(String jsonFileName, String dataGroup) {
        jsonFileName = osAdaptFileName(jsonFileName);
        JSONObject jsonDataObject = null;
        try {
            Object jsonFileObject = parser.parse(new FileReader(System.getProperty("user.dir") + File.separator + "src" +
                    File.separator + "main" + File.separator + "java" + File.separator + "data" + File.separator + jsonFileName));
            jsonDataObject = (JSONObject) jsonFileObject;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert jsonDataObject != null;
        return (JSONObject) jsonDataObject.get(dataGroup);
    }

    private String osAdaptFileName(String baseFileName) {
        if (System.getProperty("os.name").startsWith("Windows")) {
            baseFileName += ".json";
        }
        return baseFileName;
    }
}
