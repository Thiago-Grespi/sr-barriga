package runners;

import core.BaseRunner;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features/",
        glue = "tests"
)
public class RunAll extends BaseRunner {

    /*
    This Class is the beginning of the tests execution
    When you execute this class, all yours tests will be executed
    */

    //Here you insert codes that the execution of all tests need

}
