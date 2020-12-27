package by.epam.automation.test;

import by.epam.automation.page.MenuPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

public class DodoPizzaTest extends CommonConditions{
    private Logger logger = LogManager.getLogger();

    @Test
    public void testAddingPizzaToCartPrice() {
        String pizzaName = "Нежный лосось";
        String price = new MenuPage(driver)
                .openPage()
                .addPizzaToCart(pizzaName)
                .goToCart()
                .getPrice();
        logger.info(String.format("Get price of added pizza: %s", price));
        Assert.assertEquals(price, "25,90", "The price of pizza is not correct");
    }

    @Test
    public void testAddingPizzaToCartAmountOfPizzas() {
        String pizzaName = "Нежный лосось";
        int pizzaAmount = new MenuPage(driver)
                .openPage()
                .addPizzaToCart(pizzaName)
                .goToCart()
                .getPizzas()
                .size();
        Assert.assertEquals(pizzaAmount, 1, "There is more less then 1 pizza in cart");
    }

    @Test
    public void testDeletingPizzaOnCartPage() {
        String pizzaName = "Нежный лосось";
        int pizzaAmount = new MenuPage(driver)
                .openPage()
                .addPizzaToCart(pizzaName)
                .goToCart()
                .removePizzaFromCart(pizzaName)
                .getPizzas()
                .size();
        Assert.assertEquals(pizzaAmount, 0, "There is something in cart");
    }

    @Test
    public void testPizzaPriceChangesWithToppings() {

    }
}
