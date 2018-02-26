package dispatcher;

import database.databaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import login.LoginController;
import reservation.Reservation;
import reservation.ReservationRespository;
import user.User;
import user.UserRespository;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Dispatcher role view controller
 *
 * @author Daniel Zawadzki, s9482
 * @version 1.0
 */
public class DispatcherController implements Initializable {

    /**
     * Selectors to FXML components
     */
    @FXML
    private TextField nameInput; // Selector to name text field
    @FXML
    private TextField surnameInput; // Selector to surname text field
    @FXML
    private TextField guestsAmountInput; // Selector to guests amount texr field
    @FXML
    private DatePicker dateInput; // Selector to date picker
    @FXML
    private TextField hourInput; // Selector to hour text field
    @FXML
    private TextField commentInput; // Selector to comment input
    @FXML
    private TableColumn idColumn; // Selector to reservation id column
    @FXML
    private TableColumn dateColumn; // Selector to reservation date column
    @FXML
    private TableColumn hourColumn; // Selector to reservation hour column
    @FXML
    private TableColumn nameColumn; // Selector to reservation client name
    @FXML
    private TableColumn surnameColumn; // Selector to reservation client surname
    @FXML
    private TableColumn guestsColumn; // Selector to reservation guests amount
    @FXML
    private TableColumn commentColumn; // Selector to reservation comment
    @FXML
    private ComboBox<User> dispatcherComboBox; // Selector to combobox element
    @FXML
    private TableView reservationsTable; //Selector to reservations table
    @FXML
    private Label loggedUserLabel; //Selector to logged user label

    /**
     * Declaring connection and class extension
     */
    private databaseConnection dbConnection;
    private ObservableList<Reservation> reservationObservableList;
    private Dispatcher user = (Dispatcher) LoginController.getUSER();

    /**
     * Function required by Initializable interface
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Setting up connection
        this.dbConnection = new databaseConnection();

        //Setting up label with info about dispatcher
        this.loggedUserLabel.setText(this.user.toString());

        //Setting up reservation and user extensions
        ReservationRespository.getReservations();
        UserRespository.getUsers();

        //Setting up combobox options
        ObservableList<User> options =
                FXCollections.observableArrayList(User.getUserHashMap().values());
        this.dispatcherComboBox.setItems(options);
    }

    /**
     * Handler to action adding reservation
     */
    @FXML
    private void addReservation(ActionEvent event) {
        //Inserting new repository record to database and class extension
        ReservationRespository.addReservation(
                this.nameInput.getText(),
                this.surnameInput.getText(),
                this.dateInput.getValue(),
                this.hourInput.getText(),
                this.guestsAmountInput.getText(),
                this.commentInput.getText(),
                this.user.getId()
        );

        //Updating view
        this.updateReservationsTable();
        this.clearReservationForm();

    }

    /**
     * Handler to action updating reservations list
     *
     * @param event
     */
    @FXML
    private void refreshReservationsTable(ActionEvent event) {
        this.updateReservationsTable();
        this.dispatcherComboBox.setValue(null);
    }

    /**
     * Setter for reservations table filter
     *
     * @param event
     */
    @FXML
    private void setReservationsTableFilter(ActionEvent event) {
        //Declaring filtered reservations list
        ArrayList<Reservation> filteredReservationArrayList = new ArrayList<Reservation>();

        //Iterating through reservations to check dispatchers id
        for (Reservation r : Reservation.getReservationArrayList()) {
            if (r.getDispatcher() == dispatcherComboBox.getValue()) {
                filteredReservationArrayList.add(r);
            }
        }

        //Setting up the pointer on filtered arrary list
        this.reservationObservableList = FXCollections.observableArrayList(filteredReservationArrayList);

        //Setting up the factories
        this.setCellFactories();
    }

    /**
     * Method to update reservations table content
     */
    private void updateReservationsTable() {
        //Selecting all reservations from database
        ReservationRespository.getReservations();

        //Setting up the pointer on reservation observable list
        this.reservationObservableList = FXCollections.observableArrayList(Reservation.getReservationArrayList());

        //Setting up the factories
        this.setCellFactories();
    }

    /**
     * Setter for table cell factories
     */
    private void setCellFactories() {
        //Setting up cell value and property value factories
        this.idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        this.dateColumn.setCellValueFactory(new PropertyValueFactory("date"));
        this.hourColumn.setCellValueFactory(new PropertyValueFactory("hour"));
        this.guestsColumn.setCellValueFactory(new PropertyValueFactory("guestsAmount"));
        this.nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        this.surnameColumn.setCellValueFactory(new PropertyValueFactory("surname"));
        this.commentColumn.setCellValueFactory(new PropertyValueFactory("comment"));
        this.reservationsTable.setItems(null);
        this.reservationsTable.setItems(this.reservationObservableList);
    }


    /**
     * Method to clear form
     */
    private void clearReservationForm() {
        this.nameInput.setText((""));
        this.surnameInput.setText("");
        this.guestsAmountInput.setText("");
        this.hourInput.setText("");
        this.commentInput.setText("");
    }

}
