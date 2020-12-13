package page;

import org.openqa.selenium.*;

public class MenuPage {
    private WebDriver driver;
    private JavascriptExecutor executor;
    final private String url = "https://dodopizza.by/minsk";

    public MenuPage(WebDriver driver) {
        this.driver = driver;
        this.executor = (JavascriptExecutor)driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public WebElement getAddingPizzaButton(String name) {
        return driver.findElement(By.xpath(String
                .format("//article[main[h2[contains(text(), \"%s\")]]]//button[contains(text(), \"Выбрать\")]", name)));
    }

    public void addPizzaToCart(String name) {
        WebElement button = getAddingPizzaButton(name);
        executor.executeScript("arguments[0].click();", button);
        WebElement popupBuyButton = driver.findElement(By.xpath("//div[@class=\"sc-15fdqut-0 feBZgl\"]//button"));
        executor.executeScript("arguments[0].click();", popupBuyButton);
    }

    public void openPage() {
        driver.get(url);
    }

    public void quit() {
        driver.quit();
    }
}
