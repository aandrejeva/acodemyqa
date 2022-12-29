package lv.acodemy.hooks;

import io.cucumber.java.After;
import lv.acodemy.utils.DriverManager;

public class Hooks {
    @After
    public void afterTest() {
        DriverManager.closeBrowser();
    }
}
