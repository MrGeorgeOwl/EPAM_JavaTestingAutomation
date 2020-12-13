package page;

import org.openqa.selenium.*;

public class MenuPage extends BasePage {

    public MenuPage(WebDriver driver) {
        super(driver, "https://dodopizza.by/minsk");
    }

    public WebElement getAddingPizzaButton(String name) {
        return driver.findElement(By.xpath(String
                .format("//article[main[h2[contains(text(), \"%s\")]]]//button[contains(text(), \"Выбрать\")]", name)));
    }

    public void addPizzaToCart(String name) {
        WebElement button = getAddingPizzaButton(name);
        pressButtonWithExecutor(button);
        WebElement popupBuyButton = driver.findElement(By.xpath("//div[@class=\"sc-15fdqut-0 feBZgl\"]//button[contains(text(), \"Добавить\")]"));
        pressButtonWithExecutor(popupBuyButton);
    }
}
