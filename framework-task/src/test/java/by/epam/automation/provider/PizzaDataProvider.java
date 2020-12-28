package by.epam.automation.provider;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PizzaDataProvider {

    private static final String PIZZA_FILE = "src/test/java/resources/provider/pizzas.csv";

    @DataProvider
    public static Object[][] getPizzasToAddToCart() {
        return readPizzasWithPrice();
    }

    private static Object[][] readPizzasWithPrice() {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(PIZZA_FILE));
            List<String[]> pizzasWithDefaultPrice = reader
                    .lines()
                    .map(line -> line.split(","))
                    .collect(Collectors.toList());

            int rowsAmount = pizzasWithDefaultPrice.size();
            List<String> pizzaNames = new ArrayList<>();
            List<Float> pizzaDefaultPrices = new ArrayList<>();

            for (int i = 0; i < rowsAmount; i++) {
                pizzaNames.add(pizzasWithDefaultPrice.get(i)[0]);
                pizzaDefaultPrices.add(Float.valueOf(pizzasWithDefaultPrice.get(i)[1]));
            }

            Object[][] data = new Object[rowsAmount][2];

            for (int i = 0; i < rowsAmount; i++) {
                data[i][0] = pizzaNames.get(i);
                data[i][1] = pizzaDefaultPrices.get(i);
            }

            return data;
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
