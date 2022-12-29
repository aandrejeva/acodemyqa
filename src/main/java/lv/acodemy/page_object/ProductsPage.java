package lv.acodemy.page_object;

import lv.acodemy.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage {
    private final WebDriver driver = DriverManager.getInstance();

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
