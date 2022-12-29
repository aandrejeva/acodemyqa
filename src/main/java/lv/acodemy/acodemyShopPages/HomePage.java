package lv.acodemy.acodemyShopPages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class HomePage {

    private ChromeDriver driver;

    public HomePage(ChromeDriver driver) {
        this.driver = driver;
    }

    private By productListByLabel = By.xpath("//h2[@class='woocommerce-loop-product__title']");

    public void selectProductFromList(String productName) {
        List<WebElement> products = driver.findElements(productListByLabel);
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getText().equals(productName)) {
                products.get(i).click();
                break;
            }
        }
    }
}
