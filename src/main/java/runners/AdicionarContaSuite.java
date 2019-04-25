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
public class AdicionarContaSuite extends BaseRunner {

}
