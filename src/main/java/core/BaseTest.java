package core;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.LoginPage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;
import static core.Properties.CLOSE_BROWSER_BETWEEN_TESTS;

public class BaseTest {




    // ========================= Properties ==================

    @Rule
    public TestName testName = new TestName();

    JSONParser parser = new JSONParser();

    private LoginPage loginPage = new LoginPage();


    // ========================= Before ==================

    @BeforeClass // indicates that this method needs to be executed before the class execution
    public static void doSomethingBeforeClassExecution(){
        // here you do things that ALL tests of the class need to run
    }

    @Before // indicates that this method needs to be executed before every @Test annotated method
    public void doSomethingBeforeEveryTest(){
        loginForTests();
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

    protected JSONObject getJsonDataObject(String jsonFileName, String dataGroup) {
        jsonFileName = osAdaptFileName(jsonFileName);
        JSONObject jsonDataObject = null;
        try {
            Object jsonFileObject = parser.parse(new FileReader(System.getProperty("user.dir") + File.separator + "src" +
                    File.separator + "main" + File.separator + "java" + File.separator + "data" + File.separator + jsonFileName));
            jsonDataObject = (JSONObject) jsonFileObject;
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert jsonDataObject != null;
        return (JSONObject) jsonDataObject.get(dataGroup);
    }

    private String osAdaptFileName(String baseFileName){
        if(System.getProperty("os.name").startsWith("Windows")){
            baseFileName += ".json";
        }
        return baseFileName;
    }

    private void loginForTests(){
        getDriver().get(loginPage.url);
        JSONObject logInJsonData = null;
        logInJsonData = getJsonDataObject("LogInData", "valid");
        loginPage.logIn((String) logInJsonData.get("email"), (String) logInJsonData.get("pass"));
    }

    protected String getJsonDataProperty(JSONObject jsonData, String jsonProperty){
        return new String(jsonData.get(jsonProperty).
                toString().getBytes(Charset.forName("ISO-8859-1")), Charset.forName("UTF-8"));
    }

}
