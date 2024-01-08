package ClientFiles.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ClientFiles.Client;
import ReplicatedClasses.CommandClass;
import ReplicatedClasses.Commands;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * The `startController` class controls the behavior of the start view in the application.
 */
public class startController {

    @FXML
    private Button EnterButton;

    @FXML
    private TextField ID_field;

    @FXML
    private Button backButton;
    /**
     * Initializes the start view.
     */
    @FXML
    void initialize() {
        backButton.setOnAction(event -> { //?
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClientFiles/menu.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = (Stage) backButton.getScene().getWindow();

                stage.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        EnterButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClientFiles/game.fxml"));

                Client.localClient.roomId=Long.parseLong(ID_field.getText());

                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = (Stage) EnterButton.getScene().getWindow();

                stage.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}


