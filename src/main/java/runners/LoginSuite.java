package runners;

import core.BaseRunner;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import tests.LoginTest;

@RunWith(Suite.class)
@SuiteClasses({
        LoginTest.class
})
public class LoginSuite extends BaseRunner {

}
