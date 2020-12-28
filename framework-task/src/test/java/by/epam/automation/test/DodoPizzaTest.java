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
        float price = new MenuPage(driver)
                .openPage()
                .selectPizza(pizzaName)
                .addPizzaToCart()
                .goToCart()
                .getPrice();
        logger.info(String.format("Get price of added pizza: %s", price));
        Assert.assertEquals(price, 25.90f, "The price of pizza is not correct");
    }

    @Test
    public void testAddingPizzaToCartAmountOfPizzas() {
        String pizzaName = "Нежный лосось";
        int pizzaAmount = new MenuPage(driver)
                .openPage()
                .selectPizza(pizzaName)
                .addPizzaToCart()
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
                .selectPizza(pizzaName)
                .addPizzaToCart()
                .goToCart()
                .removePizzaFromCart(pizzaName)
                .getPizzas()
                .size();
        Assert.assertEquals(pizzaAmount, 0, "There is something in cart");
    }

    @Test
    public void testAddToppingsToPizza() {
        String pizzaName = "Нежный лосось";
        float price = new MenuPage(driver)
                .openPage()
                .selectPizza(pizzaName)
                .addToppingToPizza("Чеддер и пармезан")
                .addPizzaToCart()
                .goToCart()
                .getPrice();
        Assert.assertEquals(price, 28.70f, "Price is different");
    }
}
