package by.epam.automation.test;

import by.epam.automation.page.MenuPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class DodoPizzaTest extends CommonConditions{

    @Test
    public void testAddingPizzaToCartPrice() {
        logger.info("Starting test for pizza price");
        String price = new MenuPage(driver)
                .openPage()
                .addPizzaToCart("Нежный лосось")
                .goToCart()
                .getPrice();
        Assert.assertEquals(price, "25,90");
    }

    @Test
    public void testAddingPizzaToCartAmountOfPizzas() {
        logger.info("Starting test for checking amount of pizzas in cart");
        int pizzaAmount = new MenuPage(driver)
                .openPage()
                .addPizzaToCart("Нежный лосось")
                .goToCart()
                .getPizzas()
                .size();
        Assert.assertEquals(pizzaAmount, 1);
    }
}
