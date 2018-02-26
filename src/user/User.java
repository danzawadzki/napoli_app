package user;

import database.databaseHelpers;

import java.util.HashMap;

/**
 * Dispatcher user class inherited from User class
 *
 * @author Daniel Zawadzki, s9482
 * @version 1.0
 */
public abstract class User {

    //Class extension
    private static HashMap<Integer, User> UserHashMap = new HashMap<Integer, User>();

    //Class fields
    private static int ID_COUNTER;
    private int id;
    private String name;
    private String surname;
    private String pesel;
    private String emailAddress;
    private String phoneNumber;
    private String type;

    /**
     * Constructor
     *
     * @param id           - database user key id
     * @param name         - user name
     * @param surname      - user surname
     * @param pesel        - user personal number
     * @param emailAddress - user email address
     * @param phoneNumber  - user phone number
     * @param type         - database user type {Admin, Dispatcher, Deliverer}
     */
    public User(int id, String name, String surname, String pesel, String emailAddress, String phoneNumber, String type) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.type = type;

        //Adding user to the class extension
        if (!User.getUserHashMap().containsKey(id)) {
            User.UserHashMap.put(id, this);
        }
    }

    /**
     * Constructor
     *
     * @param id           - database user key id
     * @param name         - user name
     * @param surname      - user surname
     * @param pesel        - user personal number
     * @param emailAddress - user email address
     * @param phoneNumber  - user phone number
     * @param type         - database user type {Admin, Dispatcher, Deliverer}
     */
    public User(String name, String surname, String pesel, String emailAddress, String phoneNumber, String type) {
        this.setIdCounter();
        this.id = ID_COUNTER + 1;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.type = type;

        //Adding user to the class extension
        if (!User.getUserHashMap().containsKey(id)) {
            User.UserHashMap.put(id, this);
        }
    }

    /**
     * Getter for reservation id counter
     */
    public static int getIdCounter() {
        return ID_COUNTER;
    }

    /**
     * Setter for reservation counter
     */
    public static void setIdCounter() {
        if (!(User.ID_COUNTER >= 0)) {
            ID_COUNTER = databaseHelpers.getIdCounter("users");
        }
    }

    /**
     * Getter for users list
     *
     * @return
     */
    public static HashMap<Integer, User> getUserHashMap() {
        return UserHashMap;
    }

    /**
     * Setter for users list
     *
     * @param userHashMap
     */
    public static void setUserHashMap(HashMap<Integer, User> userHashMap) {
        UserHashMap = userHashMap;
    }

    /**
     * Method printing all users from UserHashMap
     */
    public static void printUserHashMap() {
        System.out.println("\n" +
                "Printing users list:\n" +
                "==================================");
        for (User u : UserHashMap.values()) {
            System.out.println(u.toString());
        }
    }

    /**
     * Getter for user id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for user id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for user name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for user name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for user surname
     *
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Setter for user surname
     *
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Getter for user pesel
     *
     * @return pesel
     */
    public String getPesel() {
        return pesel;
    }

    /**
     * Setter for user pesel
     *
     * @param pesel
     */
    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    /**
     * Getter for user email address
     *
     * @return email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Setter for user email address
     *
     * @param emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Getter for user phone number
     *
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Setter for user phone number
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Getter for user type
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for user type
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Custom toString method
     *
     * @return
     * @Override
     */
    public String toString() {
        return "id=" + id +
                ", name=" + name + ' ' + surname +
                ", pesel=" + pesel +
                ", email=" + emailAddress +
                ", phone=" + phoneNumber +
                ", type=" + type;
    }


}
