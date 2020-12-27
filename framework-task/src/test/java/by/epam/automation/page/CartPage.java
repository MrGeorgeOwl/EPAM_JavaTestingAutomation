package by.epam.automation.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends AbstractPage {
    private final By priceLocator = By.cssSelector("span .money__value");
    private final By pizzasArticleLocator = By.xpath("//section[1]/article");
    private String removePizzaSvgString = "//section[1]/article[//h3[contains(text(), \"%s\")]]//*[local-name() = \"svg\"][@class=\"sc-157hvfs-7 ZGosY\"]";
    protected final int WAIT_TIMEOUT_SECONDS = 20;

    private Logger logger = LogManager.getLogger(this.getClass().getName());

    public CartPage(WebDriver driver) {
        super(driver, "https://dodopizza.by/minsk/cart");
    }

    public String getPrice() {
        logger.info("Getting total price in cart");
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS).getSeconds())
                .until(d -> d.findElement(priceLocator)).getText();
    }

    public List<WebElement> getPizzas() {
        logger.info("Looking for pizzas");
        WebDriverWait pizzaWait = new WebDriverWait(driver, Duration.ofSeconds(60).getSeconds());
        return pizzaWait
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(pizzasArticleLocator));
    }

    @Override
    public CartPage openPage() {
        logger.info("Open cart page");
        driver.get(url);
        return this;
    }
}
