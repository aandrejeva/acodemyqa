package lv.acodemy.acodemyShopPages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProductPage {

    private ChromeDriver driver;

    public ProductPage(ChromeDriver driver) {
        this.driver = driver;
    }

    private final By addToCartButton = By.name("add-to-cart");
    private final By productNumberField = By.xpath("//input[@title = 'Qty']");
    private final By viewCartButton = By.xpath("//*[@id='content']/div/div[1]/div/a");

    public By getProductNumberField() {
        return productNumberField;
    }

    public void clearProductQuantityField(By ProductNumberField) {
        driver.findElement(ProductNumberField).clear();
    }

    public void fillInProductQuantity(String productQuantity) {
        driver.findElement(productNumberField).sendKeys(productQuantity);
    }

    public void clickOnAddToCartButton() {
        driver.findElement(addToCartButton).click();
    }

    public void clickOnViewCartButton() {
        driver.findElement(viewCartButton).click();
    }
}
