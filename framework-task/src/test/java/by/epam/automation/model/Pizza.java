package by.epam.automation.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Hashtable;
import java.util.List;
import java.util.NoSuchElementException;

public class Pizza {
    private Hashtable<Topping, WebElement> availableToppings = new Hashtable<>();
    private Hashtable<Topping, WebElement> selectedToppings = new Hashtable<>();
    private PizzaSize size;
    private float price;
    private WebDriver driver;
    private Logger logger = LogManager.getLogger();

    private final By priceLocator = By
            .xpath("//div[@class=\"sc-15fdqut-0 feBZgl\"]//button[contains(text(), \"Добавить\")]//span[@class=\"money__value\"]");

    @FindBy(xpath = "//div[@class=\"z6jeag-0 kxKIyD\"][1]")
    private WebElement sizeDiv;

    @FindBy(xpath = "//section[@class=\"sc-1keftj-0 iQYpYQ\"]/button")
    private List<WebElement> toppingButtons;

    public Pizza(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        parseSize();
        parsePrice();
        parseToppings();
    }

    private void parseSize() {
        int sizeIndex = Integer.parseInt(sizeDiv.findElement(By.xpath("div[1]")).getAttribute("offset")) + 1;
        size = PizzaSize.parseValue(sizeDiv.findElement(By.xpath(String.format("label[%d]", sizeIndex))).getText());
    }

    private void parsePrice() {
        price = Float.parseFloat(driver.findElement(priceLocator)
                .getText()
                .replace(",", "."));
    }

    private void parseToppings() {
        for (WebElement toppingButton: toppingButtons) {
            String name = toppingButton.findElement(By.xpath("h2[@class=\"title\"]")).getText();
            float price = Float.parseFloat(toppingButton.findElement(By.xpath("span/span[@class=\"money__value\"]"))
                    .getText()
                    .replace(",", "."));
            availableToppings.put(new Topping(name, price), toppingButton);
        }
    }

    public void addToppingToPizza(String toppingName) {
        try {
            Topping topping = getToppingFromTable(toppingName, availableToppings);
            price += topping.getPrice();
            Actions builder = new Actions(driver);
            builder.click(availableToppings.get(topping)).build().perform();
            selectedToppings.put(topping, availableToppings.get(topping));
            availableToppings.remove(topping);
        } catch (NoSuchElementException e) {
            logger.error(e.toString());
        }
    }

    public void removeToppingPizza(String toppingName) {
        try {
            Topping topping = getToppingFromTable(toppingName, availableToppings);
            price -= topping.getPrice();
            Actions builder = new Actions(driver);
            builder.click(availableToppings.get(topping));
            availableToppings.put(topping, selectedToppings.get(topping));
            selectedToppings.remove(topping);
        } catch (NoSuchElementException e) {
            logger.error(e.toString());
        }
    }

    private Topping getToppingFromTable(String toppingName, Hashtable<Topping, WebElement> toppings) throws NoSuchElementException{
        return toppings
                .keySet()
                .stream()
                .filter(t -> t.getName().equals(toppingName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("No such topping %s available", toppingName)));
    }
}
