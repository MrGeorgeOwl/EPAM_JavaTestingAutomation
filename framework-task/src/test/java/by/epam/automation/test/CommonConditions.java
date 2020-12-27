package by.epam.automation.test;

import by.epam.automation.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

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
