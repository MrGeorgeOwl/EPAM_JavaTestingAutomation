package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private JavascriptExecutor executor;
    final private String url = "https://dodopizza.by/minsk/cart";

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.executor = (JavascriptExecutor)driver;
    }

    public void openPage() {
        driver.get(url);
    }

    public String getPrice() {
        return new WebDriverWait(driver, Duration.ofSeconds(70).toSeconds())
                .until(d -> d.findElement(By.cssSelector("span .money__value"))).getText();
    }

    public List<WebElement> getPizzas() {
        WebDriverWait pizzaWait = new WebDriverWait(driver, Duration.ofSeconds(60).getSeconds());
        return pizzaWait
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"react-app\"]/main/section[1]/article")));
    }
}
