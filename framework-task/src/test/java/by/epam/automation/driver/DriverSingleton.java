package by.epam.automation.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverSingleton {

    private static WebDriver driver;

    public DriverSingleton() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            switch (System.getProperty("browser")) {
                case "firefox": {
                    FirefoxOptions options = new FirefoxOptions();
                    options.addArguments("--disable-gpu");
                    options.addArguments("window-size=1280,720");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--headless");
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(options);
                }
                default: {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("disable-gpu");
                    options.addArguments("window-size=1280,720");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--headless");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);
                }
            }
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
