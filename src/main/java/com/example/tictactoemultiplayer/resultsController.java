package com.example.tictactoemultiplayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class resultsController { //implements Initializable ?

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Player> Table;

    @FXML
    private TableColumn<Player, Integer> Age;

    @FXML
    private TableColumn<Player, String> NickName;

    @FXML
    private TableColumn<Player, Integer> Player_ID;

    @FXML
    private TableColumn<Player, Integer> Wins;

    @FXML
    private Button backButton;

    //poniewaz na razie nie mamy bazy...
    ObservableList<Player> list = FXCollections.observableArrayList(
            new Player(1,"Daniil",18,20),
            new Player(2,"Karol",19,11),
            new Player(3,"Sebastian",20,2000)
    );

    @FXML
    void initialize() {

        Age.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Age"));
        NickName.setCellValueFactory(new PropertyValueFactory<Player, String>("NickName"));
        Player_ID.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Player_ID"));
        Wins.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Wins"));

        Table.setItems(list); //baza danych tu bedzie

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

}
