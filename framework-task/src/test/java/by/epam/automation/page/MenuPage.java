package by.epam.automation.page;

import by.epam.automation.model.Pizza;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class MenuPage extends AbstractPage {
    protected String pageUrl = "https://dodopizza.by/minsk";
    private final String addPizzaButtonString = "//article[main[h2[contains(text(), \"%s\")]]]//button[contains(text(), \"Выбрать\")]";

    @FindBy(xpath = "//div[@class=\"sc-15fdqut-0 feBZgl\"]//button[contains(text(), \"Добавить\")]")
    private WebElement popupBuyButton;

    private Pizza selectedPizza;
    private Logger logger = LogManager.getLogger(this.getClass().getName());

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    public MenuPage(WebDriver driver, Pizza pizza) {
        super(driver);
        selectedPizza = pizza;
    }

    private WebElement getAddingPizzaButton(String name) {
        return driver.findElement(By.xpath(String
                .format(addPizzaButtonString, name)));
    }

    public Pizza getSelectedPizza() {
        return selectedPizza;
    }

    public MenuPage addPizzaToCart() {
        pressButtonWithExecutor(popupBuyButton);
        return new MenuPage(this.driver);
    }

    public MenuPage selectPizza(String name) {
        WebElement button = getAddingPizzaButton(name);
        pressButtonWithExecutor(button);
        selectedPizza = new Pizza(driver);
        return new MenuPage(this.driver, selectedPizza);
    }

    public CartPage goToCart() {
        logger.info("Going to cart page from menu page");
        return new CartPage(driver).openPage();
    }

    public MenuPage addToppingToPizza(String topping) {
        selectedPizza.addToppingToPizza(topping);
        return new MenuPage(driver, selectedPizza);
    }

    public MenuPage removeToppingFromPizza(String topping) {
        selectedPizza.removeToppingPizza(topping);
        return new MenuPage(driver, selectedPizza);
    }

    @Override
    public MenuPage openPage() {
        logger.info("Opening menu page");
        driver.get(pageUrl);
        return this;
    }
}
