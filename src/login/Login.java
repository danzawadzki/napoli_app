package login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Login class extending Application
 *
 * @author Daniel Zawadzki, s9482
 * @version 1.0
 */
public class Login extends Application {

    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Setting up loggin stage on application start
     *
     * @param stage
     * @throws Exception
     */
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Restaurant App");
        stage.show();

        /**
         * Trwałość danych klas spoza przypadków użycia (klasa address oraz klasa dish)
         */
        //Stworzenie instancji klasy Address oraz dodanie jej rekordu do bazy danych
        //Address a = AddressRepository.addAddress("Marszałkowska", "43a", "205", "Warszawa", "00-000", "Wejscie od frontu.");

        //Pobranie rekordu z bazy danych i na jego podstawie stworzenie instancji w aplikacji
        //System.out.println(AddressRepository.getAddress(3).toString());

        //Stworzenie instancji klasy Dish oraz dodanie jej rekordu do bazy danych
        //Dish d = DishRepository.addDish("Potrawa testowa", "Opis testowy", 10.99, "test1, test2, test3, test4");

        //Pobranie rekordu z bazy danych i na jego podstawie stworzenie instancji w aplikacji
        //System.out.println(DishRepository.getDish(d.getId()).toString());
    }
}
