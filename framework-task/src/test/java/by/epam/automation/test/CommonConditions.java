package by.epam.automation.test;

import by.epam.automation.driver.DriverSingleton;
import by.epam.automation.utils.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class CommonConditions {

    protected WebDriver driver;

    @BeforeMethod
    protected void setUp() {
        driver = DriverSingleton.getDriver();
    }

    @AfterMethod
    protected void teardown() {
        DriverSingleton.closeDriver();
    }
}
