import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import lv.acodemy.utils.DriverManager;
import org.testng.annotations.AfterTest;

@CucumberOptions(tags = "",
        features = {"src/test/resources/features"},
        glue = {"lv.acodemy.step_definition"},
        plugin = {"html:target/report.html"})

public class TestCucumber extends AbstractTestNGCucumberTests {

    @AfterTest
    public void after() {
        DriverManager.closeBrowser();
    }

}