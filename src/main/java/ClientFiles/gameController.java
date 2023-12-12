package ClientFiles;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class gameController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Ready1Button;

    @FXML
    private Button Ready2Button;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        backButton.setOnAction(event -> { //?
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = (Stage) backButton.getScene().getWindow();

                stage.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

}
