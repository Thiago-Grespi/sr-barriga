package runners;

import core.BaseRunner;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.ListarResumoMensalTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ListarResumoMensalTest.class
})
public class ListarResumoMensalSuite extends BaseRunner {
}
