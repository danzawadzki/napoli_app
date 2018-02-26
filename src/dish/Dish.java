package dish;

import database.databaseHelpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Menu dish class - represents single menu position
 *
 * @author Daniel Zawadzki, s9482
 * @version 1.0
 */
public class Dish {
    /**
     * Declaring class extension
     */
    private static HashMap<Integer, Dish> dishHashMap = new HashMap<Integer, Dish>();

    /**
     * Declaring class fields
     */
    private static int ID_COUNTER = -1;
    private int id;
    private String name;
    private String description;
    private Double price;
    private ArrayList<String> components;

    /**
     * Constructor (with out auto id)
     *
     * @param id              - dish id
     * @param name            - dish name
     * @param description     - dish description
     * @param price           - dish price
     * @param componentsComma - dish component products as comma separated string (example: "eggs, milk, flour, salt")
     */
    public Dish(int id, String name, String description, Double price, String componentsComma) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.components = new ArrayList<>(Arrays.asList(componentsComma.split(", ")));

        //Adding instance to the class extension
        Dish.dishHashMap.put(id, this);
    }

    /**
     * Constructor (all fields are required)
     *
     * @param name            - dish name
     * @param description     - dish description
     * @param price           - dish price
     * @param componentsComma - dish component products as comma separated string (example: "eggs, milk, flour, salt")
     */
    public Dish(String name, String description, Double price, String componentsComma) {
        this.setIdCounter();
        this.id = this.ID_COUNTER + 1;
        this.name = name;
        this.description = description;
        this.price = price;
        this.components = new ArrayList<>(Arrays.asList(componentsComma.split(", ")));

        //Adding instance to the class extension
        Dish.dishHashMap.put(id, this);
    }

    /**
     * Getters and Setterss
     */
    public static HashMap<Integer, Dish> getDishHashMap() {
        return dishHashMap;
    }

    public static void setDishHashMap(HashMap<Integer, Dish> dishHashMap) {
        Dish.dishHashMap = dishHashMap;
    }

    public static int getIdCounter() {
        return ID_COUNTER;
    }

    public static void setIdCounter() {
        if (Dish.ID_COUNTER == -1) {
            ID_COUNTER = databaseHelpers.getIdCounter("dish");
        }
    }

    /**
     * Adding new component to the dish
     *
     * @param component - component name
     */
    public void addComponent(String component) {
        if (!this.checkComponents(component)) {
            this.components.add(component);
        }
    }

    /**
     * Checking if dish already contains component
     *
     * @param component - component name
     * @return
     */
    public boolean checkComponents(String component) {
        for (String c : this.components) {
            if (c.equals(component)) {
                return true;
            }
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ArrayList<String> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<String> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", components=" + components +
                '}';
    }
}
