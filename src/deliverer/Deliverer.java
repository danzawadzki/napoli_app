package deliverer;

import user.User;

/**
 * Deliverer user class inherited from User class
 *
 * @author Daniel Zawadzki, s9482
 * @version 1.0
 */
public class Deliverer extends User {

    /**
     * Constructor
     *
     * @param id           - database user key id
     * @param name         - user name
     * @param surname      - user surname
     * @param pesel        - user personal number
     * @param emailAddress - user email address
     * @param phoneNumber  - user phone number
     */
    public Deliverer(int id, String name, String surname, String pesel, String emailAddress, String phoneNumber) {
        super(id, name, surname, pesel, emailAddress, phoneNumber, "Deliverer");
    }
}
