package by.epam.automation.test;

import by.epam.automation.page.CartPage;
import by.epam.automation.page.MenuPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class DodoPizzaTest extends CommonConditions{

    @Test
    public void testAddingPizzaToCartPrice() {
        String price = new MenuPage(driver)
                .openPage()
                .addPizzaToCart()
                .get
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
