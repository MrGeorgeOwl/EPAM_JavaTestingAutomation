package by.epam.automation.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.reporters.jq.Main;

import java.time.Duration;
import java.util.List;

public class CartPage extends AbstractPage {

    private final By price = By.cssSelector("span .money__value");
    private final By pizzas = By.xpath("//section[1]/article");
    private Logger logger = LogManager.getLogger(this.getClass().getName());

    public CartPage(WebDriver driver) {
        super(driver, "https://dodopizza.by/minsk/cart");
    }

    public String getPrice() {
        logger.info("Getting total price in cart");
        return new WebDriverWait(driver, Duration.ofSeconds(70).getSeconds())
                .until(d -> d.findElement(price)).getText();
    }

    public List<WebElement> getPizzas() {
        logger.info("Looking for pizzas");
        WebDriverWait pizzaWait = new WebDriverWait(driver, Duration.ofSeconds(60).getSeconds());
        return pizzaWait
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(pizzas));
    }

    @Override
    public CartPage openPage() {
        logger.info("Open cart page");
        driver.get(url);
        return this;
    }
}
