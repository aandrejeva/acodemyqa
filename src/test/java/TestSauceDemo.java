import io.github.bonigarcia.wdm.WebDriverManager;
import lv.acodemy.page_object.InventoryPage;
import lv.acodemy.page_object.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static lv.acodemy.constants.Generic.SAUCE_URL;

public class TestSauceDemo {
    ChromeDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;

    @BeforeMethod(description = "Preconditions")
    public void initialize() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.chromedriver().browserVersion("108");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); //won't open the webpage

        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        driver.get(SAUCE_URL);
    }

    @Test(description = "Happy path: Test authorization with standard user")
    public void authorizedTest() {
        loginPage.authorize("standard_user", "secret_sauce");
        Assert.assertEquals(inventoryPage.itemElementCount(), 6);
    }

    @Test(description = "Failure: Test authorization error message with incorrect password")
    public void invalidCredentialTest() {
        loginPage.authorize("standard_user", "incorrect_password");//incorrect password
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(description = "Open product from the product list")
    public void openProductTest() {
        loginPage.authorize("standard_user", "secret_sauce");
        Assert.assertEquals(inventoryPage.getTitleElement().getText(), "PRODUCTS");
        inventoryPage.clickOnProductByLabel("Sauce Labs Bolt T-Shirt");
        System.out.println();
    }

    @AfterMethod
    public void tearDown() { //will close browser even if the result will be failed
        driver.close();
        driver.quit();
    }
}
