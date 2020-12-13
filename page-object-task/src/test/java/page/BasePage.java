package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

abstract public class AbstractBasePage {

    private WebDriver driver;
    private JavascriptExecutor executor;
    private final String url;

    public AbstractBasePage(WebDriver driver, String url) {
        this.driver = driver;
        this.executor = (JavascriptExecutor) driver;
        this.url = url;
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

    public void openPage() {
        driver.get(url);
    }

    public void quit() {
        driver.quit();
    }
}
