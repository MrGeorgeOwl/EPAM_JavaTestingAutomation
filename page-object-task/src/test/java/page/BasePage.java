package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

    protected WebDriver driver;
    protected JavascriptExecutor executor;
    protected final String url;

    public BasePage(WebDriver driver, String url) {
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

    public void pressButtonWithExecutor(WebElement button) {
        executor.executeScript("arguments[0].click();", button);
    }
}
