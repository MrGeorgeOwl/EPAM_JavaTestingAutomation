package by.epam.automation.test;

import by.epam.automation.driver.DriverSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class CommonConditions {

    protected WebDriver driver;
    protected Logger logger = LogManager.getLogger();

    @BeforeMethod
    protected void setUp() {
        driver = DriverSingleton.getDriver();
    }

    @AfterMethod
    protected void teardown() {
        DriverSingleton.closeDriver();
    }
}
