package globalsolution.citizentech.trilhamagica;

import io.cucumber.junit.platform.engine.Constants;
import io.cucumber.junit.platform.engine.Cucumber;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@Cucumber
@SelectClasspathResource("feature")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "globalsolution.citizentech.trilhamagica.steps")
public class TrilhamagicaApplicationTests {
}