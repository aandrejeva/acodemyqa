import lv.acodemy.acodemyShopPages.CartPage;
import lv.acodemy.acodemyShopPages.HomePage;
import lv.acodemy.acodemyShopPages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static lv.acodemy.constants.Generic.HOME_URL;

public class TestAcodemyShop {

    ChromeDriver driver;
    private WebDriverWait wait;
    HomePage homePage;
    CartPage cartPage;
    ProductPage productPage;

    @BeforeMethod
    public void initialize() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        productPage = new ProductPage(driver);
        driver.get(HOME_URL);
    }

    @Test(description = "Test valid coupon code")
    public void testOneValidCouponCode() {
        String validCouponeCode = "easy_discount";

        homePage.selectProductFromList("Polo");
        productPage.clearProductQuantityField(productPage.getProductNumberField());
        productPage.fillInProductQuantity("2");
        productPage.clickOnAddToCartButton();
        productPage.clickOnViewCartButton();
        cartPage.fillInCouponCode(validCouponeCode);
        cartPage.clickOnApplyCouponButton();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@role='alert']")));
        Assert.assertEquals(cartPage.getSuccessMessage(), "Coupon code applied successfully.");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"post-7\"]/div/div/div[2]/div/table/tbody/tr[4]/td/strong/span/bdi")));
        Assert.assertEquals(cartPage.getSumAfterDiscount(), "38,00 €");
        cartPage.clearCard();
    }

    @Test(description = "Test invalid coupon code")
    public void testInvalidCouponCode() {
        String invalidCouponeCode = "invalid";

        homePage.selectProductFromList("Album");
        productPage.clearProductQuantityField(productPage.getProductNumberField());
        productPage.fillInProductQuantity("2");
        productPage.clickOnAddToCartButton();
        productPage.clickOnViewCartButton();
        cartPage.fillInCouponCode(invalidCouponeCode);
        cartPage.clickOnApplyCouponButton();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@role='alert']")));
        Assert.assertEquals(cartPage.getErrorMessage(), "Coupon \"" + invalidCouponeCode + "\" does not exist!");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"post-7\"]/div/div/div[2]/div/table/tbody/tr[2]/td/strong/span/bdi")));
        Assert.assertEquals(cartPage.getSumAfterDiscount(), "30,00 €");
        cartPage.clearCard();
    }

    @Test(description = "Test expired coupon code")
    public void testExpiredCouponCode() {
        String expiredCouponCode = "expired";

        homePage.selectProductFromList("Belt");
        productPage.clearProductQuantityField(productPage.getProductNumberField());
        productPage.fillInProductQuantity("3");
        productPage.clickOnAddToCartButton();
        productPage.clickOnViewCartButton();
        cartPage.fillInCouponCode(expiredCouponCode);
        cartPage.clickOnApplyCouponButton();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@role='alert']")));
        Assert.assertEquals(cartPage.getErrorMessage(), "This coupon has expired.");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"post-7\"]/div/div/div[2]/div/table/tbody/tr[3]/td/strong/span/bdi")));
        Assert.assertEquals(cartPage.getSumAfterDiscount(), "165,00 €");
        cartPage.clearCard();
    }

    @Test(description = "Test two valid coupon codes which can be used together")
    public void testTwoCouponCodesThatCanBeUsedTogether() {
        String firstCouponCode = "easy_discount";
        String secondCouponCode = "additional_discount";

        homePage.selectProductFromList("Belt");
        productPage.clickOnAddToCartButton();
        productPage.clickOnViewCartButton();
        cartPage.fillInCouponCode(firstCouponCode);
        cartPage.clickOnApplyCouponButton();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@role='alert']")));
        Assert.assertEquals(cartPage.getSuccessMessage(), "Coupon code applied successfully.");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"post-7\"]/div/div/div[2]/div/table/tbody/tr[4]/td/strong/span/bdi")));
        Assert.assertEquals(cartPage.getSumAfterDiscount(), "52,25 €");

        cartPage.fillInCouponCode(secondCouponCode);
        cartPage.clickOnApplyCouponButton();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@role='alert']")));
        Assert.assertEquals(cartPage.getSuccessMessage(), "Coupon code applied successfully.");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"post-7\"]/div/div/div[2]/div/table/tbody/tr[5]/td/strong/span/bdi")));
        Assert.assertEquals(cartPage.getSumAfterDiscount(), "46,75 €");
        cartPage.clearCard();
    }

    @Test(description = "Test two valid coupon codes which can't be used together, the last coupon is applied")
    public void testTwoCouponCodesThatCantBeUsedTogether() throws InterruptedException {
        String firstCouponCode = "acodemy10off";
        String secondCouponCode = "acodemy20off";

        homePage.selectProductFromList("Album");
        productPage.clickOnAddToCartButton();
        productPage.clickOnViewCartButton();
        cartPage.fillInCouponCode(firstCouponCode);
        cartPage.clickOnApplyCouponButton();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@role='alert']")));
        Assert.assertEquals(cartPage.getSuccessMessage(), "Coupon code applied successfully.");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"post-7\"]/div/div/div[2]/div/table/tbody/tr[3]/td/strong/span/bdi")));
        Assert.assertEquals(cartPage.getSumAfterDiscount(), "13,50 €");

        cartPage.fillInCouponCode(secondCouponCode);
        cartPage.clickOnApplyCouponButton();
        Assert.assertEquals(cartPage.getSuccessMessage(), "Coupon code applied successfully.");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"post-7\"]/div/div/div[2]/div/table/tbody/tr[3]/td/strong/span/bdi")));
        Assert.assertEquals(cartPage.getSumAfterDiscount(), "12,00 €");
        cartPage.clearCard();
    }

    @Test(description = "Test remove coupon code")
    public void testRemoveCouponCode() {
        String validCouponeCode = "easy_discount";

        homePage.selectProductFromList("Polo");
        productPage.clickOnAddToCartButton();
        productPage.clickOnViewCartButton();
        cartPage.fillInCouponCode(validCouponeCode);
        cartPage.clickOnApplyCouponButton();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@role='alert']")));
        Assert.assertEquals(cartPage.getSuccessMessage(), "Coupon code applied successfully.");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td/a[text()='[Remove]']")));
        cartPage.clickOnRemoveButton();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"post-7\"]/div/div/div[2]/div/table/tbody/tr[3]/td/strong/span/bdi")));
        Assert.assertEquals(cartPage.getSumAfterDiscount(), "20,00 €");
        cartPage.clearCard();
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}

