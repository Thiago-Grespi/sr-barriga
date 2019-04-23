package runners;

import core.BaseRunner;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import tests.AdicionarContaTest;
import tests.LoginTest;

@RunWith(Suite.class)
@SuiteClasses({
        AdicionarContaTest.class,
})
public class LoginSuite extends BaseRunner {
    /*
    This is an alternative method to run your tests suits when u don't want/have the scenarios described in Cucumber Feature
     */
}
