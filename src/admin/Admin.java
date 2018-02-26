package admin;

import user.User;

/**
 * Admin user class inherited from User class
 *
 * @author Daniel Zawadzki, s9482
 * @version 1.0
 */
public class Admin extends User {

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
    public Admin(int id, String name, String surname, String pesel, String emailAddress, String phoneNumber) {
        super(id, name, surname, pesel, emailAddress, phoneNumber, "Admin");
    }

}
