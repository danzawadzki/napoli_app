package address;

import database.databaseHelpers;

import java.util.HashMap;

/**
 * Client address class
 *
 * @author Daniel Zawadzki, s9482
 * @version 1.0
 */
public class Address {

    /**
     * Class extension
     */

    private static HashMap<Integer, Address> addressHashMap = new HashMap<Integer, Address>();

    /**
     * Class attributes
     */
    private static int ID_COUNTER;
    private int id;
    private String streetName;
    private String streetNumber;
    private String flat;
    private String city;
    private String zipCode;
    private String annotation;

    /**
     * Constructor (with client annotation and flat number)
     *
     * @param id           - address id
     * @param streetName   - delivery street name
     * @param streetNumber - delivery street number
     * @param flat         - flat number (optional)
     * @param city         - city name
     * @param zipCode      - city code
     * @param annotation   - additional information for deliverer (entrance code, building color etc.)
     */
    public Address(int id, String streetName, String streetNumber, String flat, String city, String zipCode, String annotation) {
        this.id = id;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.flat = flat;
        this.city = city;
        this.zipCode = zipCode;
        this.annotation = annotation;

        this.addressHashMap.put(id, this);
    }

    /**
     * Constructor (with client annotation and flat number)
     *
     * @param streetName   - delivery street name
     * @param streetNumber - delivery street number
     * @param flat         - flat number (optional)
     * @param city         - city name
     * @param zipCode      - city code
     * @param annotation   - additional information for deliverer (entrance code, building color etc.)
     */
    public Address(String streetName, String streetNumber, String flat, String city, String zipCode, String annotation) {
        this.setIdCounter();
        this.id = ID_COUNTER + 1;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.flat = flat;
        this.city = city;
        this.zipCode = zipCode;
        this.annotation = annotation;

        this.addressHashMap.put(id, this);
    }

    /**
     * Constructor (with out client annotation)
     *
     * @param streetName   - delivery street name
     * @param streetNumber - delivery street number
     * @param flat         - flat number (optional)
     * @param city         - city name
     * @param zipCode      - city code
     */
    public Address(String streetName, String streetNumber, String flat, String city, String zipCode) {
        this.setIdCounter();
        this.id = ID_COUNTER + 1;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.flat = flat;
        this.city = city;
        this.zipCode = zipCode;

        this.addressHashMap.put(id, this);
    }

    /**
     * Constructor (with out annotation and flat number)
     *
     * @param streetName
     * @param streetNumber
     * @param city
     * @param zipCode
     */
    public Address(String streetName, String streetNumber, String city, String zipCode) {
        this.setIdCounter();
        this.id = ID_COUNTER + 1;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
        this.zipCode = zipCode;

        this.addressHashMap.put(id, this);
    }

    /**
     * Getters and Setters
     */
    public static int getIdCounter() {
        return ID_COUNTER;
    }

    public static void setIdCounter() {
        if (!(Address.ID_COUNTER >= 0)) {
            ID_COUNTER = databaseHelpers.getIdCounter("address");
        }
    }

    public static HashMap<Integer, Address> getAddressHashMap() {
        return addressHashMap;
    }

    public static void setAddressHashMap(HashMap<Integer, Address> addressHashMap) {
        Address.addressHashMap = addressHashMap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    /**
     * Custom toString func
     *
     * @Override
     */
    public String toString() {
        return "ClientAddress{" +
                "streetName='" + streetName + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", flat='" + flat + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", annotation='" + annotation + '\'' +
                '}';
    }
}
