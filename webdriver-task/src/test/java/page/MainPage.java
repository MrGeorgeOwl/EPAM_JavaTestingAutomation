package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private WebDriver driver;
    private JavascriptExecutor executor;
    final private String url = "https://dodopizza.by/minsk";

    public MainPage(WebDriver driver) {
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
                .format("//*[@id=\"pizzas\"]//main/h2[text()='%s']/../..//button[@class=\"sc-91ilwk-0 hmteXa product-control\"]", name)));
    }

    public void addPizzaToCart(String name) {
        WebElement button = getAddingPizzaButton(name);
        executor.executeScript("arguments[0].click();", button);
        WebElement popupBuyButton = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div/div[2]/div[2]/button"));
        executor.executeScript("arguments[0].click();", popupBuyButton);
    }

    public void openPage() {
        driver.get(url);
    }

    public void quit() {
        driver.quit();
    }
}
