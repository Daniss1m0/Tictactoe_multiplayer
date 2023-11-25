package com.example.tictactoemultiplayer;

import java.io.IOException;
import java.net.URL;
        import java.util.ResourceBundle;
        import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelloController {

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
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
                 Parent root = loader.load();

                 Scene scene = new Scene(root);

                 Stage stage = (Stage) StartButton.getScene().getWindow();

                 stage.setScene(scene);

             } catch (IOException e) {
                 e.printStackTrace();
             }
         });
    }

}
