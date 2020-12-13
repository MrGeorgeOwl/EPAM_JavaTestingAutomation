package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver, "https://dodopizza.by/minsk/cart");
    }

    public String getPrice() {
        return new WebDriverWait(driver, Duration.ofSeconds(70).toSeconds())
                .until(d -> d.findElement(By.cssSelector("span .money__value"))).getText();
    }

    public List<WebElement> getPizzas() {
        WebDriverWait pizzaWait = new WebDriverWait(driver, Duration.ofSeconds(60).getSeconds());
        return pizzaWait
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//section[1]/article")));
    }
}
