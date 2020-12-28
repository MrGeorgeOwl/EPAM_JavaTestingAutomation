package by.epam.automation.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends AbstractPage {
    protected String pageUrl = "https://dodopizza.by/minsk/cart";
    private final By priceLocator = By.cssSelector("span .money__value");
    private final By pizzasArticleLocator = By.xpath("//section[1]/article");
    private String removePizzaSvgString = "//section[1]/article[//h3[contains(text(), \"%s\")]]//*[local-name() = \"svg\"][@class=\"sc-157hvfs-7 ZGosY\"]";

    protected final int WAIT_TIMEOUT_SECONDS = 20;

    private Logger logger = LogManager.getLogger(this.getClass().getName());

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public float getPrice() {
        logger.info("Getting total price in cart");
        return Float.parseFloat(new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS).getSeconds())
                .until(d -> d.findElement(priceLocator)).getText().replace(",", "."));
    }

    public List<WebElement> getPizzas() {
        logger.info("Looking for pizzas");
        WebDriverWait pizzaWait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS).getSeconds());
        try {
            return pizzaWait
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(pizzasArticleLocator));
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public CartPage removePizzaFromCart(String pizzaName) {
        WebElement deleteSvg = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS).getSeconds())
                .until(d -> d.findElement(By.xpath(String.format(removePizzaSvgString, pizzaName))));
        Actions builder = new Actions(driver);
        builder.click(deleteSvg).build().perform();
        driver.navigate().refresh();
        return new CartPage(driver);
    }

    @Override
    public CartPage openPage() {
        logger.info("Open cart page");
        driver.get(pageUrl);
        return this;
    }
}
