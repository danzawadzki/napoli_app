package client;

import address.Address;
import database.databaseHelpers;

import java.util.HashMap;

/**
 * Guest client class (for clients making online orders with out registration)
 *
 * @author Daniel Zawadzki, s9482
 * @version 1.0
 */
public class Client {
    /**
     * Class extension
     */
    private static HashMap<Integer, Client> clientHashMap = new HashMap<Integer, Client>();

    /**
     * Class attributes
     */
    private static int ID_COUNTER;
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String emailAddress;
    private Address address;

    /**
     * Constructor (with all fields)
     *
     * @param id           - client id
     * @param name         - client name
     * @param surname      - client surname
     * @param phoneNumber  - client phoneNumber
     * @param emailAddress - client email address
     * @param address      - client address
     */
    public Client(int id, String name, String surname, String phoneNumber, String emailAddress, Address address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;

        Client.clientHashMap.put(id, this);
    }

    /**
     * Constructor (with auto id)
     *
     * @param name         - client name
     * @param surname      - client surname
     * @param phoneNumber  - client phoneNumber
     * @param emailAddress - client email address
     * @param address      - client address
     */
    public Client(String name, String surname, String phoneNumber, String emailAddress, Address address) {
        this.setIdCounter();
        this.id = ID_COUNTER + 1;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;

        Client.clientHashMap.put(id, this);
    }

    /**
     * Constructor (with out email address)
     *
     * @param name        - client name
     * @param surname     - client surname
     * @param phoneNumber - client phoneNumber
     * @param address     - client address
     */
    public Client(String name, String surname, String phoneNumber, Address address) {
        this.setIdCounter();
        this.id = ID_COUNTER + 1;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.address = address;

        Client.clientHashMap.put(id, this);
    }

    /**
     * Getters and Setters
     */
    public static int getIdCounter() {
        return ID_COUNTER;
    }

    public static void setIdCounter() {
        if (!(Client.ID_COUNTER >= 0)) {
            ID_COUNTER = databaseHelpers.getIdCounter("client");
        }
    }

    public static HashMap<Integer, Client> getClientHashMap() {
        return clientHashMap;
    }

    public static void setClientHashMap(HashMap<Integer, Client> clientHashMap) {
        Client.clientHashMap = clientHashMap;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Custom toString func
     *
     * @Override
     */
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                "}";
    }
}
