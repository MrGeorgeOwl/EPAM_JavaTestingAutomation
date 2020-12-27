package by.epam.automation.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

public class MenuPage extends AbstractPage {

    private final String addPizzaButton = "//article[main[h2[contains(text(), \"%s\")]]]//button[contains(text(), \"Выбрать\")]";
    private final By popupBuyButton = By.xpath("//div[@class=\"sc-15fdqut-0 feBZgl\"]//button[contains(text(), \"Добавить\")]");
    private Logger logger = LogManager.getLogger(this.getClass().getName());

    public MenuPage(WebDriver driver) {
        super(driver, "https://dodopizza.by/minsk");
    }

    private WebElement getAddingPizzaButton(String name) {
        return driver.findElement(By.xpath(String
                .format(addPizzaButton, name)));
    }

    public MenuPage addPizzaToCart(String name) {
        logger.info(String.format("Adding pizza %s to cart", name));
        WebElement button = getAddingPizzaButton(name);
        pressButtonWithExecutor(button);
        WebElement popupBuyButton = driver.findElement(this.popupBuyButton);
        pressButtonWithExecutor(popupBuyButton);
        return new MenuPage(this.driver);
    }

    public CartPage goToCart() {
        logger.info("Going to cart page from menu page");
        return new CartPage(driver).openPage();
    }

    @Override
    public MenuPage openPage() {
        logger.info("Opening menu page");
        driver.get(url);
        return this;
    }
}
