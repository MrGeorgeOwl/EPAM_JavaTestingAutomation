package by.epam.automation.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected JavascriptExecutor executor;
    protected final String url;

    public AbstractPage(WebDriver driver, String url) {
        this.driver = driver;
        this.executor = (JavascriptExecutor) driver;
        this.url = url;
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    protected String getUrl() {
        return url;
    }

    public abstract <T extends AbstractPage> T openPage();

    protected void pressButtonWithExecutor(WebElement button) {
        executor.executeScript("arguments[0].click();", button);
    }
}
