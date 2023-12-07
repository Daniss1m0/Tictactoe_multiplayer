package com.example.tictactoemultiplayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class settingsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Age_field; //zmienic

    @FXML
    private TextField NickName_field;

    @FXML
    private Button SaveButton;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        SaveButton.setOnAction(event -> {
            DataBaseHandler dbHandler = new DataBaseHandler();

            String NickName = NickName_field.getText().trim();
            String Age = Age_field.getText().trim();

            if(!NickName.equals("") && !Age.equals("")){
                save(NickName, Age);

                int ageValue = Integer.parseInt(Age);

                Player player = new Player(NickName,ageValue);
                dbHandler.signUpPlayer(player);
            }
            else
                System.out.println("Error!");

        });
        backButton.setOnAction(event -> { //?
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = (Stage) backButton.getScene().getWindow();

                stage.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private void save(String nickName, String age) {

    }

}
