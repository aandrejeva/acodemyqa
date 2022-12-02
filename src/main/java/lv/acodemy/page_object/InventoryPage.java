package lv.acodemy.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventoryPage {
    private final By item = By.className("inventory_item");
    private final By titleElement = By.className("title");
    private final By productLabelList = By.xpath("//div[@class='inventory_item_label']//a");

    private WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getTitleElement() {
        return driver.findElement(titleElement);
    }

    public int itemElementCount() {
        return driver.findElements(item).size();
    }

    public List<WebElement> productLabels() {
        return driver.findElements(productLabelList);
    }

    public void clickOnProductByLabel(String label) {
        for (int i = 0; i < productLabels().size(); i++) {
            if (productLabels().get(i).getText().equals(label)) {
                productLabels().get(i).click();
            }
        }
    }
}
