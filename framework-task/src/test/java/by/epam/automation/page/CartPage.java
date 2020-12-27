package by.epam.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage {

    private By price = By.cssSelector("span .money__value");
    private By pizzas = By.xpath("//section[1]/article");

    public CartPage(WebDriver driver) {
        super(driver, "https://dodopizza.by/minsk/cart");
    }

    public String getPrice() {
        return new WebDriverWait(driver, Duration.ofSeconds(70).getSeconds())
                .until(d -> d.findElement(price)).getText();
    }

    public List<WebElement> getPizzas() {
        WebDriverWait pizzaWait = new WebDriverWait(driver, Duration.ofSeconds(60).getSeconds());
        return pizzaWait
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(pizzas));
    }
}
