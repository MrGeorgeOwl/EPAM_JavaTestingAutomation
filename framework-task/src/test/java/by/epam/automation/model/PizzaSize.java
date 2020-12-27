package by.epam.automation.model;

import java.util.Hashtable;

public enum PizzaSize {
    SMALL("Маленькая"),
    MEDIUM("Средняя"),
    LARGE("Большая");

    private static final Hashtable<String, PizzaSize> stringToSize = new Hashtable<String, PizzaSize>() {
        {
            put("Маленькая", SMALL);
            put("Средняя", MEDIUM);
            put("Большая", LARGE);
        }
    };

    private final String sizeName;

    PizzaSize(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getSizeName() {
        return sizeName;
    }

    public static PizzaSize parseValue(String value) {
        return stringToSize.get(value);
    }
}
