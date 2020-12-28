package by.epam.automation.test;

import by.epam.automation.page.MenuPage;
import by.epam.automation.provider.PizzaDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class DodoPizzaTest extends CommonConditions{
    private Logger logger = LogManager.getLogger();

    @Test(dataProvider = "getPizzasToAddToCart", dataProviderClass = PizzaDataProvider.class)
    public void testAddingPizzaToCartPrice(String pizzaName, float expectedPrice) {
        float price = new MenuPage(driver)
                .openPage()
                .selectPizza(pizzaName)
                .addPizzaToCart()
                .goToCart()
                .getPrice();
        logger.info(String.format("Get price of added pizza: %s", price));
        Assert.assertEquals(price, expectedPrice, "The price of pizza is not correct");
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

    @Test(dataProvider = "getPizzasWithToppingsToAdd", dataProviderClass = PizzaDataProvider.class)
    public void testAddToppingsToPizza(String pizzaName, List<String> toppings, float expectedPrice) {
        float price = new MenuPage(driver)
                .openPage()
                .selectPizza(pizzaName)
                .addSeveralToppingsToPizza(toppings)
                .addPizzaToCart()
                .goToCart()
                .getPrice();
        Assert.assertEquals(price, expectedPrice, "Price is different");
    }

    @Test
    public void testRemoveToppingsFromPizza() {
        String pizzaName = "Нежный лосось";
        float price = new MenuPage(driver)
                .openPage()
                .selectPizza(pizzaName)
                .addToppingToPizza("Чеддер и пармезан")
                .addToppingToPizza("Маслины")
                .removeToppingFromPizza("Маслины")
                .addPizzaToCart()
                .goToCart()
                .getPrice();
        Assert.assertEquals(price, 28.70f, "Price is different");
    }
}
