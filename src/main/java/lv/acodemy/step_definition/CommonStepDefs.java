package lv.acodemy.step_definition;

import io.cucumber.java.en.Given;
import lv.acodemy.utils.DriverManager;
import org.openqa.selenium.WebDriver;

public class CommonStepDefs {
    private final WebDriver driver = DriverManager.getInstance();

    @Given("user opens {string}")
    public void userOpens(String url) {
        driver.get(url);


    }
}
