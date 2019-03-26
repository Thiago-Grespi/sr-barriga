package runners;

import core.BaseRunner;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import tests.ExampleTest01;
import tests.ExampleTest02;

@RunWith(Suite.class)
@SuiteClasses({
        ExampleTest01.class,
        ExampleTest02.class
        //Here you can add all the test classes you need to run for a test suit
})
public class ExampleSuiteRunner extends BaseRunner {

    /*
    This is an alternative method to run your tests suits when u don't want/have the scenarios described in Cucumber Feature
     */
}
