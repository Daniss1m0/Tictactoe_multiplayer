package ClientFiles.Controllers;

import java.io.IOException;
import java.net.URL;
        import java.util.ResourceBundle;
        import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class menuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ExitButton;


    @FXML
    private Button ResultsButton;

    @FXML
    private Button SettingsButton;

    @FXML
    private Button StartButton;

    @FXML
    void initialize() {
         StartButton.setOnAction(event -> {
             try {
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClientFiles/start.fxml"));
                 Parent root = loader.load();

                 Scene scene = new Scene(root);

                 Stage stage = (Stage) StartButton.getScene().getWindow();

                 stage.setScene(scene);

             } catch (IOException e) {
                 e.printStackTrace();
             }
         });
        ResultsButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClientFiles/results.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = (Stage) ResultsButton.getScene().getWindow();

                stage.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        SettingsButton.setOnAction(event -> {
            //przyklad uzycia: Client.localClient.sendMessage();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClientFiles/settings.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = (Stage) SettingsButton.getScene().getWindow();

                stage.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        ExitButton.setOnAction(event -> {
            Stage stage = (Stage) ExitButton.getScene().getWindow();
            stage.close();
        });
    }

}
