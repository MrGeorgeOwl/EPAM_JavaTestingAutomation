import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import page.CartPage;
import page.MainPage;


public class DodoPizzaTest {

    WebDriver driver;
    MainPage mainPage;
    CartPage cartPage;

    @BeforeClass
    private void setUp() {
        System.setProperty("webdriver.chrome.driver", "/drivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-gpu");
        options.addArguments("window-size=1280,720");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);

        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
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
