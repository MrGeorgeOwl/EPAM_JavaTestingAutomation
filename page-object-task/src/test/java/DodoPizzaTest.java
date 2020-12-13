import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import page.CartPage;
import page.MenuPage;

public class DodoPizzaTest {

    WebDriver driver;
    MenuPage mainPage;
    CartPage cartPage;

    @BeforeMethod
    private void setUp() {
        driver = setUpDriver();

        mainPage = new MenuPage(driver);
        cartPage = new CartPage(driver);
    }

    private WebDriver setUpDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-gpu");
        options.addArguments("window-size=1280,720");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        return new ChromeDriver(options);
    }

    @AfterMethod
    private void teardown() {
        driver.quit();
    }

    @Test
    public void testAddingPizzaToCartPrice() {
        mainPage.openPage();
        mainPage.addPizzaToCart("Нежный лосось");

        cartPage.openPage();
        Assert.assertEquals(cartPage.getPrice(), "25,90");
    }

    @Test
    public void testAddingPizzaToCartAmountOfPizzas() {
        mainPage.openPage();
        mainPage.addPizzaToCart("Нежный лосось");

        cartPage.openPage();
        Assert.assertEquals(cartPage.getPizzas().size(), 1);
    }
}
