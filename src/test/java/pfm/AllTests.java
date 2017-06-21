package pfm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import entities.AllEntitiesTests;

@RunWith(Suite.class)
@SuiteClasses({AllEntitiesTests.class,/*AllControllersTests.class, AllDaosIntegrationTests.class, AllControllersIntegrationTests.class*/})
public class AllTests {

}
