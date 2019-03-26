package tests;

import core.BaseTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.Before;
import pages.ExamplePage01;

import static core.DriverFactory.getDriver;

public class ExampleTest01 extends BaseTest {

    /*
    Here is where the magic happens. This is ths class where you put your tests
    In the Tests Classes you don't interact with WebDriver, leave this to the Page Classes
     */

    private ExamplePage01 page1;

    @Before
    private void start(){
        /*
        here we get the driver and instantiate the page, that interact with the WebElements
        Again, in this Class you don't interact with WebDriver, this is Page's responsibility
         */
        page1 = new ExamplePage01(); // instantiating the Page Class, from where we get the elements manipulation
        getDriver().get(page1.url); //Once the WebDrive is created in DriverFactory Class, we send the url to access
    }

    /*
    From here, you'll implement all your steps from your feature file related to this test
     */

    @Given("this is a feature file step definition (.*)") //and at the end, in the "(.*)", we are receiving a parameter
    public void stepImplementationMethod(){
        Assert.assertEquals("Some expected text", page1.aPageMethodThatGetSomeText()); // and here is the test itself, made by asserts
    }

    @When("and here is another feature file step implementation (.*)")
    public void anotherStepImplementationMethod(String niceText){
        Assert.assertEquals(niceText, page1.anotherPageMethodThatGetSomeText());
    }

}
