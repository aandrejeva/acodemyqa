//import org.openqa.selenium.By;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.time.Duration;
//
//import static lv.acodemy.constants.Generic.GOOGLE_URL;
//
//public class TestWaits {
//
//    ChromeDriver driver;
//    WebDriverWait wait;
//
//    @BeforeMethod
//    public void before() {
//        driver = new ChromeDriver();
//        //1) implicitly wait
//        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//will wait 10 sec till it fails
//        //2) wait for loading page
//        //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
//        //3) explicit wait
//        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//    }
//
//    @Test
//    public void testWaiter() {
//        driver.get(GOOGLE_URL);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("element_that_is_not_in_place")));
//    }
//
//    @AfterMethod
//    public void after() {
//        driver.close();
//        driver.quit();
//    }
//}
