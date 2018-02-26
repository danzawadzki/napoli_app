package menu;

import database.databaseHelpers;
import dish.Dish;

import java.util.ArrayList;

/**
 * Menu class - represents different type of menu (children, italian, fit etc.)
 *
 * @author Daniel Zawadzki, s9482
 * @version 1.0
 */
public class Menu {

    private static ArrayList<Menu> menuAllArrayList; // Array list with all menus
    /**
     * Declaring class fields
     */
    private static int ID_COUNTER = -1;
    private int id;
    private String name;
    private String description;
    private ArrayList<Dish> dishArrayList; // Array list with all dishes connected to this menu

    /**
     * Constructor (all fields are required)
     *
     * @param name          - menu name
     * @param description   - menu description
     * @param dishArrayList - list of dishes
     */
    public Menu(int id, String name, String description, ArrayList<Dish> dishArrayList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dishArrayList = dishArrayList;

        // Creating class extension if it doesn't exists yet
        if (menuAllArrayList == null) {
            menuAllArrayList = new ArrayList<Menu>();
        }

        //Adding menu to the class extension
        menuAllArrayList.add(this);
    }

    /**
     * Constructor (all fields are required)
     *
     * @param name          - menu name
     * @param description   - menu description
     * @param dishArrayList - list of dishes
     */
    public Menu(String name, String description, ArrayList<Dish> dishArrayList) {
        this.setIdCounter();
        this.id = this.ID_COUNTER + 1;
        this.name = name;
        this.description = description;
        this.dishArrayList = dishArrayList;

        // Creating class extension if it doesn't exists yet
        if (menuAllArrayList == null) {
            menuAllArrayList = new ArrayList<Menu>();
        }

        //Adding menu to the class extension
        menuAllArrayList.add(this);
    }

    /**
     * Getters & Setters
     */
    public static int getIdCounter() {
        return ID_COUNTER;
    }

    public static void setIdCounter() {
        if (Menu.ID_COUNTER == -1) {
            ID_COUNTER = databaseHelpers.getIdCounter("menu");
        }
    }

    public static ArrayList<Menu> getMenuAllArrayList() {
        return menuAllArrayList;
    }

    public static void setMenuAllArrayList(ArrayList<Menu> menuAllArrayList) {
        Menu.menuAllArrayList = menuAllArrayList;
    }

    /**
     * Adding new dish to the menu
     *
     * @param dish
     */
    public void addDish(Dish dish) {
        this.dishArrayList.add(dish);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Dish> getDishArrayList() {
        return dishArrayList;
    }

    public void setDishArrayList(ArrayList<Dish> dishArrayList) {
        this.dishArrayList = dishArrayList;
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

    public ArrayList<Dish> getMenuDishArrayList() {
        return dishArrayList;
    }

    public void setMenuDishArrayList(ArrayList<Dish> dishArrayList) {
        this.dishArrayList = dishArrayList;
    }
}
