import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static lv.acodemy.constants.Generic.GOOGLE_URL;

public class TestChrome {
    ChromeDriver driver;

    @BeforeMethod
    public void before() {
        driver = new ChromeDriver();
    }

    /*@BeforeTest
    public void prepareChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/aandrejeva/Desktop/acodemyqa/acodemyqa/chromedriver");
    }*/

    @AfterMethod
    public void tearDown() { //will close browser even if the result will be failed
        driver.close();
        driver.quit();
    }

    @Test(enabled = false)
    public void chromeTest() {

        driver.get(GOOGLE_URL);
        WebElement acceptButton = driver.findElement(By.xpath("//button//div[contains(text(), 'Accept all')]"));
        acceptButton.click();

        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("acodemy");
        searchField.sendKeys(Keys.ENTER);

        Assert.assertEquals(driver.getTitle(), "acodemy - Google Search");
    }
}
