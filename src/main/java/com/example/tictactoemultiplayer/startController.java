package com.example.tictactoemultiplayer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class startController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button EnterButton;

    @FXML
    private TextField ID_field;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        assert EnterButton != null : "fx:id=\"EnterButton\" was not injected: check your FXML file 'start.fxml'.";
        assert ID_field != null : "fx:id=\"ID_field\" was not injected: check your FXML file 'start.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'start.fxml'.";

    }

}


