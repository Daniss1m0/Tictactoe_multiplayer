package com.example.tictactoemultiplayer;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class resultsController {

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

    @FXML
    void initialize() {

        Age.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Age"));
        NickName.setCellValueFactory(new PropertyValueFactory<Player, String>("NickName"));
        Player_ID.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Player_ID"));
        Wins.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Wins"));

        loadDataFromDatabase();

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

    private void loadDataFromDatabase() {
        DataBaseHandler dbHandler = new DataBaseHandler();
        ObservableList<Player> playerList = FXCollections.observableArrayList();

        try {
            ResultSet resultSet = dbHandler.getPlayer(new Player());

            while (resultSet.next()) {
                int playerID = ((ResultSet) resultSet).getInt(Const.PLAYERS_ID);
                String nickName = resultSet.getString(Const.PLAYERS_NICKNAME);
                int age = resultSet.getInt(Const.PLAYERS_AGE);
                int wins = resultSet.getInt(Const.PLAYERS_WINS);

                Player player = new Player(playerID, nickName, age, wins);
                playerList.add(player);
            }

            Table.setItems(playerList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
