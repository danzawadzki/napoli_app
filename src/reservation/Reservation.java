package reservation;

import database.databaseHelpers;
import user.User;

import java.util.ArrayList;

/**
 * Login class extending Application
 *
 * @author Daniel Zawadzki, s9482
 * @version 1.0
 */
public class Reservation {

    // Class extension
    private static ArrayList<Reservation> reservationArrayList = new ArrayList<Reservation>();

    // Class attributes
    private static int ID_COUNTER;
    private int id;
    private String name;
    private String surname;
    private String date;
    private String hour;
    private String guestsAmount;
    private String comment;
    private User dispatcher;


    /**
     * Constructor (with additional comment)
     *
     * @param name         - client name
     * @param surname      - client surname
     * @param date         - reservation date in format dd/mm/yyyy
     * @param hour         - reservation hour in format hh/mm
     * @param guestsAmount - expected amount of guests
     * @param comment      - additional comment
     * @param dispatcherId - id of dispatcher, who added reservation
     */
    public Reservation(String name, String surname, String date, String hour, String guestsAmount, String comment, int dispatcherId) {
        this.setIdCounter();
        this.id = this.ID_COUNTER + 1;
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.hour = hour;
        this.guestsAmount = guestsAmount;
        this.comment = comment;
        this.dispatcher = User.getUserHashMap().get(dispatcherId);

    }

    /**
     * Constructor (with out additional comment)
     *
     * @param name         - client name
     * @param surname      - client surname
     * @param date         - reservation date in format dd/mm/yyyy
     * @param hour         - reservation hour in format hh/mm
     * @param guestsAmount - expected amount of guests
     * @param dispatcherId - id of dispatcher, who added reservation
     */
    public Reservation(String name, String surname, String date, String hour, String guestsAmount, int dispatcherId) {
        this.setIdCounter();
        this.id = this.ID_COUNTER + 1;
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.hour = hour;
        this.guestsAmount = guestsAmount;
        this.dispatcher = User.getUserHashMap().get(dispatcherId);
        this.comment = null;

        this.addToReservationArrayList(this);
    }

    /**
     * Constructor (with out additional comment)
     *
     * @param name         - client name
     * @param surname      - client surname
     * @param date         - reservation date in format dd/mm/yyyy
     * @param hour         - reservation hour in format hh/mm
     * @param guestsAmount - expected amount of guests
     * @param dispatcherId - id of dispatcher, who added reservation
     */
    public Reservation(int id, String name, String surname, String date, String hour, String guestsAmount, String comment, int dispatcherId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.hour = hour;
        this.guestsAmount = guestsAmount;
        this.dispatcher = User.getUserHashMap().get(dispatcherId);
        this.comment = comment;

        this.addToReservationArrayList(this);
        Reservation.setIdCounter();
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
        if (!(Reservation.ID_COUNTER >= 0)) {
            ID_COUNTER = databaseHelpers.getIdCounter("reservations");
        }
    }

    /**
     * Getter for reservations list
     *
     * @return
     */
    public static ArrayList<Reservation> getReservationArrayList() {
        return reservationArrayList;
    }

    /**
     * Setter for reservations list
     *
     * @param reservationArrayList
     */
    public static void setReservationArrayList(ArrayList<Reservation> reservationArrayList) {
        Reservation.reservationArrayList = reservationArrayList;
    }

    /**
     * Method to add new reservation to the class extension
     *
     * @param r - new reservation
     */
    public static void addToReservationArrayList(Reservation r) {
        Reservation.reservationArrayList.add(r);
    }

    /**
     * Method to check if class extension already contains this reservation
     *
     * @param id
     * @return
     */
    public static boolean checkReservationArrayList(int id) {
        for (Reservation r : Reservation.getReservationArrayList()) {
            if (r.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Priting reservations list
     *
     * @return
     */
    public static void printReservationList() {
        System.out.println("Printing reservations list:");
        System.out.println("==================================");
        for (Reservation r : reservationArrayList) {
            System.out.println(r.toString());
        }
    }

    /**
     * Getter for reservation id
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for reservation id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for client name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for client name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for client surname
     *
     * @return
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Setter for client surname
     *
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Getter for reservation date
     *
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter for resevation date
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Getter for reservation hour
     *
     * @return
     */
    public String getHour() {
        return hour;
    }

    /**
     * Setter for reservation hour
     *
     * @param hour
     */
    public void setHour(String hour) {
        this.hour = hour;
    }

    /**
     * Getter for guests amount
     *
     * @return
     */
    public String getGuestsAmount() {
        return guestsAmount;
    }

    /**
     * Setter for guestes amount
     *
     * @param guestsAmount
     */
    public void setGuestsAmount(String guestsAmount) {
        this.guestsAmount = guestsAmount;
    }

    /**
     * Getter for reservation comment
     *
     * @return
     */
    public String getComment() {
        return comment;
    }

    /**
     * Setter for reservation comment
     *
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Getter for dispatcher id
     *
     * @return
     */
    public User getDispatcher() {
        return dispatcher;
    }

    /**
     * Setter for dispatcher id
     *
     * @param dispatcher
     */
    public void setDispatcher(User dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * Custom toString method for reservation objects
     *
     * @return
     * @Override
     */
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", date='" + date + '\'' +
                ", hour='" + hour + '\'' +
                ", guestsAmount='" + guestsAmount + '\'' +
                ", comment='" + comment + '\'' +
                ", dispatcherId='" + //dispatcher.toString() + '\'' +
                '}';
    }
}
