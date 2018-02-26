package order;

import client.Client;
import database.databaseHelpers;
import dish.Dish;

import java.util.ArrayList;

/**
 * Client order class
 *
 * @author Daniel Zawadzki, s9482
 * @version 1.0
 */
public class Order {

    /**
     * Declaring class fields
     */
    private static int ID_COUNTER = -1;
    /**
     * Declaring class extension
     */
    private ArrayList<Order> ordersArrayList = null;
    private int id;
    private Client client;
    private ArrayList<Dish> dishArrayList;
    private String placementDate;
    private String deliveryHour = "ASAP"; //Default value of delivery is ASAP
    private String annotation;

    /**
     * Constructor (with all fields)
     *
     * @param client        - instance of Client class
     * @param dishArrayList - arrayList with instances of menu dish class
     * @param placementDate - auto generated by DB date of order placement
     * @param deliveryHour  - client preferred delivery time
     * @param annotation    - additional note (example: the degree of meat roasting)
     */
    public Order(Client client, ArrayList<Dish> dishArrayList, String placementDate, String deliveryHour, String annotation) {
        this.setIdCounter();
        this.id = ID_COUNTER + 1;
        this.client = client;
        this.dishArrayList = dishArrayList;
        this.placementDate = placementDate;
        this.deliveryHour = deliveryHour;
        this.annotation = annotation;

        //Checking if class extension already exists
        if (ordersArrayList == null) {
            this.ordersArrayList = new ArrayList<Order>();
        }

        //Adding this instance of Order class to the class extension
        this.ordersArrayList.add(this);
    }

    /**
     * Constructor (with all fields)
     *
     * @param client        - instance of Client class
     * @param dishArrayList - arrayList with instances of menu dish class
     * @param placementDate - auto generated by DB date of order placement
     * @param deliveryHour  - client preferred delivery time
     * @param annotation    - additional note (example: the degree of meat roasting)
     */
    public Order(int id, Client client, ArrayList<Dish> dishArrayList, String placementDate, String deliveryHour, String annotation) {
        this.id = id;
        this.client = client;
        this.dishArrayList = dishArrayList;
        this.placementDate = placementDate;
        this.deliveryHour = deliveryHour;
        this.annotation = annotation;

        //Checking if class extension already exists
        if (ordersArrayList == null) {
            this.ordersArrayList = new ArrayList<Order>();
        }

        //Adding this instance of Order class to the class extension
        this.ordersArrayList.add(this);
    }

    /**
     * Constructor (with out annotation and with default ASAP delivery hour)
     *
     * @param client        - instance of Client class
     * @param dishArrayList - arrayList with instances of menu dish class
     * @param placementDate - auto generated by DB date of order placement
     */
    public Order(Client client, ArrayList<Dish> dishArrayList, String placementDate) {
        this.client = client;
        this.dishArrayList = dishArrayList;
        this.placementDate = placementDate;

        //Checking if class extension already exists
        if (ordersArrayList == null) {
            this.ordersArrayList = new ArrayList<Order>();
        }

        //Adding this instance of Order class to the class extension
        this.ordersArrayList.add(this);
    }

    /**
     * Getters and Setters
     */
    public static int getIdCounter() {
        return ID_COUNTER;
    }

    public static void setIdCounter() {
        if (Order.ID_COUNTER == -1) {
            ID_COUNTER = databaseHelpers.getIdCounter("order");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ArrayList<Dish> getDishArrayList() {
        return dishArrayList;
    }

    public void setDishArrayList(ArrayList<Dish> dishArrayList) {
        this.dishArrayList = dishArrayList;
    }

    public String getPlacementDate() {
        return placementDate;
    }

    public void setPlacementDate(String placementDate) {
        this.placementDate = placementDate;
    }

    public String getDeliveryHour() {
        return deliveryHour;
    }

    public void setDeliveryHour(String deliveryHour) {
        this.deliveryHour = deliveryHour;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public ArrayList<Order> getOrdersArrayList() {
        return ordersArrayList;
    }

    public void setOrdersArrayList(ArrayList<Order> ordersArrayList) {
        this.ordersArrayList = ordersArrayList;
    }
}