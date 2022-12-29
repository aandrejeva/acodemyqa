package lv.acodemy.acodemyShopPages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class CartPage {
    private ChromeDriver driver;

    public CartPage(ChromeDriver driver) {
        this.driver = driver;
    }

    private final By applyCouponButton = By.name("apply_coupon");
    private final By couponCodeField = By.name("coupon_code");
    private final By removeCouponButton = By.xpath("//td/a[text()='[Remove]']");
    private final By errorMessage = By.xpath("//ul[@role='alert']/li");
    private final By successMessage = By.xpath("//div[@role='alert']");
    private final By sumAfterDiscount = By.xpath("//tr/td/strong/span/bdi");
    private final By clearIcon = By.xpath("//a[@class='remove']");

    public void fillInCouponCode(String couponCode) {
        driver.findElement(couponCodeField).sendKeys(couponCode);
    }

    public void clickOnApplyCouponButton() {
        driver.findElement(applyCouponButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }

    public void clickOnRemoveButton() {
        driver.findElement(removeCouponButton).click();
    }

    public String getSumAfterDiscount() {
        return driver.findElement(sumAfterDiscount).getText();
    }

    public void clearCard() {
        driver.findElement(clearIcon).click();
    }
}
