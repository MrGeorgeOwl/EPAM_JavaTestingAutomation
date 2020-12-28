package by.epam.automation.provider;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PizzaDataProvider {

    private static final String PIZZA_FILE = "src/test/java/resources/provider/pizzas.csv";
    private static final String PIZZA_WITH_TOPPINGS_FILE = "src/test/java/resources/provider/pizzas_with_toppings.csv";

    @DataProvider
    public static Object[][] getPizzasToAddToCart() {
        return readPizzasWithPrice();
    }

    private static Object[][] readPizzasWithPrice() {
        try{
            List<String[]> pizzasWithDefaultPrice = readRowsFromFile(PIZZA_FILE);

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

    @DataProvider
    public static Object[][] getPizzasWithToppingsToAdd() {
        return readPizzasWithToppings();
    }

    private static Object[][] readPizzasWithToppings() {
        try {
            List<String[]> pizzaWithToppingsRows = readRowsFromFile(PIZZA_WITH_TOPPINGS_FILE);

            int rowsAmount = pizzaWithToppingsRows.size();
            List<String> pizzaNames = new ArrayList<>();
            List<List<String>> toppings = new ArrayList<>(rowsAmount);
            List<Float> expectedPrices = new ArrayList<>();

            for (int rowIndex = 0; rowIndex < rowsAmount; rowIndex++) {
                pizzaNames.add(pizzaWithToppingsRows.get(rowIndex)[0]);
                addToppings(toppings, rowIndex, pizzaWithToppingsRows.get(rowIndex)[1]);
                expectedPrices.add(Float.valueOf(pizzaWithToppingsRows.get(rowIndex)[2]));
            }

            Object[][] data = new Object[rowsAmount][3];
            for (int rowIndex = 0; rowIndex < rowsAmount; rowIndex++) {
                data[rowIndex][0] = pizzaNames.get(rowIndex);
                data[rowIndex][1] = toppings.get(rowIndex);
                data[rowIndex][2] = expectedPrices.get(rowIndex);
            }

            return data;
        } catch (IOException e) {
            e.printStackTrace();;
        }
        return null;
    }

    private static List<String[]> readRowsFromFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return reader
                .lines()
                .map(line -> line.split("\\|"))
                .collect(Collectors.toList());
    }

    private static void addToppings(List<List<String>> toppings, int rowIndex, String toppingsToAdd) {
        List<String> toppingsList = Arrays.asList(toppingsToAdd.split(","));
        toppings.add(new ArrayList<>());
        for(String topping: toppingsList) {
            toppings.get(rowIndex).add(topping);
        }
    }
}
