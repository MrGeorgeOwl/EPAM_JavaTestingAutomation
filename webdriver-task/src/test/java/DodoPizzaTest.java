import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;


public class DodoPizzaTest {

    WebDriver driver;
    JavascriptExecutor executor;

    @BeforeClass
    private void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-gpu");
        options.addArguments("window-size=1280,720");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        executor = (JavascriptExecutor)driver;
    }

    @AfterClass
    private void teardown() {
        driver.quit();
    }

    @AfterMethod
    private void clearCache() {
        driver.manage().deleteAllCookies();
    }

    @Test
    public void testAddingPizzaToCartPrice() {
        driver.get("https://dodopizza.by/minsk");
        WebElement buyButton = driver
                .findElement(By.xpath("//article[main[h2[contains(text(), \"Нежный лосось\")]]]//button[contains(text(), \"Выбрать\")]"));
        executor.executeScript("arguments[0].click();", buyButton);

        WebElement popupBuyButton = driver.findElement(By.xpath("//div[@class=\"sc-15fdqut-0 feBZgl\"]//button[contains(text(), \"Добавить\")]"));
        executor.executeScript("arguments[0].click();", popupBuyButton);

        driver.get("https://dodopizza.by/minsk/cart");

        WebElement price = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds())
                .until(d -> d.findElement(By.cssSelector("span .money__value")));
        Assert.assertEquals(price.getText(), "25,90");
    }

    @Test
    public void testAddingPizzaToCartAmountOfPizzas() {
        driver.get("https://dodopizza.by/minsk");
        WebElement buyButton = driver
                .findElement(By.xpath("//article[main[h2[contains(text(), \"Нежный лосось\")]]]//button[contains(text(), \"Выбрать\")]"));
        executor.executeScript("arguments[0].click();", buyButton);

        WebElement popupBuyButton = driver.findElement(By.xpath("//div[@class=\"sc-15fdqut-0 feBZgl\"]//button[contains(text(), \"Добавить\")]"));
        executor.executeScript("arguments[0].click();", popupBuyButton);

        driver.get("https://dodopizza.by/minsk/cart");
        WebDriverWait pizzaWait = new WebDriverWait(driver, Duration.ofSeconds(60).getSeconds());

        List<WebElement> pizzas = pizzaWait
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//section[1]/article")));

        Assert.assertEquals(pizzas.size(), 1);
    }
}
