package page;

import org.openqa.selenium.*;

public class MenuPage extends BasePage {

    private final String addPizzaButton = "//article[main[h2[contains(text(), \"%s\")]]]//button[contains(text(), \"Выбрать\")]";
    private By popupBuyButton = By.xpath("//div[@class=\"sc-15fdqut-0 feBZgl\"]//button[contains(text(), \"Добавить\")]");

    public MenuPage(WebDriver driver) {
        super(driver, "https://dodopizza.by/minsk");
    }

    public WebElement getAddingPizzaButton(String name) {
        return driver.findElement(By.xpath(String
                .format(addPizzaButton, name)));
    }

    public void addPizzaToCart(String name) {
        WebElement button = getAddingPizzaButton(name);
        pressButtonWithExecutor(button);
        WebElement popupBuyButton = driver.findElement(this.popupBuyButton);
        pressButtonWithExecutor(popupBuyButton);
    }
}
