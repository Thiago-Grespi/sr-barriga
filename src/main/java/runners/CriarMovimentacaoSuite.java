package runners;

import core.BaseRunner;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import tests.CriarMovimentacaoTest;

@RunWith(Suite.class)
@SuiteClasses({
        CriarMovimentacaoTest.class,
})
public class CriarMovimentacaoSuite extends BaseRunner {

}
