package login;

import admin.Admin;
import deliverer.Deliverer;
import dispatcher.Dispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import user.User;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


/**
 * Login controller
 *
 * @author Daniel Zawadzki, s9482
 * @version 1.0
 */
public class LoginController implements Initializable {

    private static User USER;

    //Setting up login model
    LoginModel loginModel = new LoginModel();
    @FXML
    private Label messageLabel; // Selector to message label displaying status of logging in process.
    @FXML
    private Label statusLabel; // Selector to status label displaying status of connection with database.
    @FXML
    private TextField emailInput; // Selector to email input.
    @FXML
    private TextField passwordInput; // Selector to password input.
    @FXML
    private Button loginButton; // Selector to login button handling on click action.

    /**
     * Getter for User
     *
     * @return
     */
    public static Object getUSER() {
        return USER;
    }

    /**
     * Setter for User
     *
     * @param USER
     */
    public static void setUSER(User USER) {
        LoginController.USER = USER;
    }

    /**
     * Method required by Initializable interface
     *
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
        if (this.loginModel.checkDatabaseConnection()) {
            this.statusLabel.setText("Online");
        } else {
            this.statusLabel.setText("Offline");
        }
    }

    /**
     * Handler for logging in action. Authenticates the user and calls the appropriate view basing on user type
     *
     * @param e
     * @FXML
     */
    public void Login(ActionEvent e) {
        try {
            List<String> userArgs = this.loginModel.authenticate(this.emailInput.getText(), this.passwordInput.getText());
            if (userArgs != null) {

                /** Selecting login stage to close it on login success */
                Stage stage = (Stage) this.loginButton.getScene().getWindow();
                stage.close();

                /**
                 * Switch to create instance of USER class and display correct dashboard
                 */
                switch (userArgs.get(7)) {
                    case "Dispatcher":
                        this.setUSER(new Dispatcher(
                                Integer.valueOf(userArgs.get(0)),
                                userArgs.get(1),
                                userArgs.get(2),
                                userArgs.get(5),
                                userArgs.get(4),
                                userArgs.get(6)));
                        loginDispatcher();
                        break;
                    case "Admin":
                        this.setUSER(new Admin(
                                Integer.valueOf(userArgs.get(0)),
                                userArgs.get(1),
                                userArgs.get(2),
                                userArgs.get(5),
                                userArgs.get(4),
                                userArgs.get(6)));
                        loginAdmin();
                        break;
                    case "Deliverer":
                        this.setUSER(new Deliverer(
                                Integer.valueOf(userArgs.get(0)),
                                userArgs.get(1),
                                userArgs.get(2),
                                userArgs.get(5),
                                userArgs.get(4),
                                userArgs.get(6)));
                        loginDeliverer();
                    default:
                        break;
                }


            } else {
                this.messageLabel.setText("Incorrect email/password");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Dispatcher login.
     */
    public void loginDispatcher() {
        try {
            //Creating stage and loader
            Stage dispatcherStage = new Stage();
            FXMLLoader loader = new FXMLLoader();

            //Creating parent
            Parent root = (Parent) loader.load(getClass().getResource("/dispatcher/dispatcher.fxml"));

            //Creating scene and setting up stage
            Scene scene = new Scene(root);
            dispatcherStage.setScene(scene);
            dispatcherStage.setTitle("Dispatcher Dashboard");
            dispatcherStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Admin login.
     */
    public void loginAdmin() {
        try {
            //Creating stage and loader
            Stage adminStage = new Stage();
            FXMLLoader loader = new FXMLLoader();

            //Creating parent
            Parent root = (Parent) loader.load(getClass().getResource("/admin/admin.fxml"));

            //Creating scene and setting up stage
            Scene scene = new Scene(root);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Deliverer login.
     */
    public void loginDeliverer() {
        try {
            //Creating stage and loader
            Stage delivererStage = new Stage();
            FXMLLoader loader = new FXMLLoader();

            //Creating parent
            Parent root = (Parent) loader.load(getClass().getResource("/deliverer/deliverer.fxml"));

            //Creating scene and setting up stage
            Scene scene = new Scene(root);
            delivererStage.setScene(scene);
            delivererStage.setTitle("Deliverer Dashboard");
            delivererStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
