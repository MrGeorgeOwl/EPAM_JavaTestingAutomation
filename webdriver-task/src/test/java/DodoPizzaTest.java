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
        WebElement buyButton = driver.findElement(By.xpath("//*[@id=\"pizzas\"]/article[2]/footer/button"));
        executor.executeScript("arguments[0].click();", buyButton);

        WebElement popupBuyButton = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div/div[2]/div[2]/button"));
        executor.executeScript("arguments[0].click();", popupBuyButton);

        WebElement cartButton = driver.findElement(By.xpath("//*[@id=\"react-app\"]/nav/div/div[2]/div[2]/button"));
        executor.executeScript("arguments[0].click();", cartButton);

        WebElement price = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds())
                .until(d -> d.findElement(By.cssSelector("span .money__value")));
        Assert.assertEquals(price.getText(), "25,90");
    }

    @Test
    public void testAddingPizzaToCartAmountOfPizzas() {
        driver.manage().deleteAllCookies();
        driver.get("https://dodopizza.by/minsk");
        WebElement buyButton = driver.findElement(By.xpath("//*[@id=\"pizzas\"]/article[2]/footer/button"));
        executor.executeScript("arguments[0].click();", buyButton);

        WebElement popupBuyButton = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div/div[2]/div[2]/button"));
        executor.executeScript("arguments[0].click();", popupBuyButton);

        WebElement cartButton = driver.findElement(By.xpath("//*[@id=\"react-app\"]/nav/div/div[2]/div[2]/button"));
        executor.executeScript("arguments[0].click();", cartButton);
        WebDriverWait pizzaWait = new WebDriverWait(driver, Duration.ofSeconds(60).getSeconds());

        List<WebElement> pizzas = pizzaWait
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"react-app\"]/main/section[1]/article")));

        Assert.assertEquals(pizzas.size(), 1);
    }
}
