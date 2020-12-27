package by.epam.automation.page;

import org.openqa.selenium.*;

public class MenuPage extends AbstractPage {

    private final String addPizzaButton = "//article[main[h2[contains(text(), \"%s\")]]]//button[contains(text(), \"Выбрать\")]";
    private final By popupBuyButton = By.xpath("//div[@class=\"sc-15fdqut-0 feBZgl\"]//button[contains(text(), \"Добавить\")]");

    public MenuPage(WebDriver driver) {
        super(driver, "https://dodopizza.by/minsk");
    }

    private WebElement getAddingPizzaButton(String name) {
        return driver.findElement(By.xpath(String
                .format(addPizzaButton, name)));
    }

    public MenuPage addPizzaToCart(String name) {
        WebElement button = getAddingPizzaButton(name);
        pressButtonWithExecutor(button);
        WebElement popupBuyButton = driver.findElement(this.popupBuyButton);
        pressButtonWithExecutor(popupBuyButton);
        return new MenuPage(this.driver);
    }

    public CartPage goToCart() {
        return new CartPage(driver).openPage();
    }

    @Override
    public MenuPage openPage() {
        driver.get(url);
        return this;
    }
}
