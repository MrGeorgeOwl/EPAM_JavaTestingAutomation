package by.epam.automation.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected JavascriptExecutor executor;
    protected String pageUrl;
    protected final int WAIT_TIMEOUT_SECONDS = 10;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.executor = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    protected String getUrl() {
        return pageUrl;
    }

    public abstract <T extends AbstractPage> T openPage();

    protected void pressButtonWithExecutor(WebElement button) {
        executor.executeScript("arguments[0].click();", button);
    }
}
