package ClientFiles.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import ClientFiles.Client;
import ReplicatedClasses.CommandClass;
import ReplicatedClasses.Commands;
import ReplicatedClasses.Player;
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
    private TableView<Player> Table; // jest tylko jedna tablica wiec moze byc static

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

    public static resultsController singletone;

    @FXML
    void initialize() {

        Age.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Age"));
        NickName.setCellValueFactory(new PropertyValueFactory<Player, String>("NickName"));
        Player_ID.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Player_ID"));
        Wins.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Wins"));

        // wysyla wiadomosc do bazy danych
        Client.localClient.sendMessage(new CommandClass(Commands.GetResultsCommand));
        // w funkcji Client.listenForMessage odbiera wiadomosc i wykonuje odpowiednie operacje

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

        singletone=this;

    }

    public void loadDataFromDatabase(List<Player> playerList) { // metoda statyczna zeby mogla byc wywolana z klasy Client
        //DataBaseHandler dbHandler = new DataBaseHandler();
        ObservableList<Player> fxCollectionsPlrList = FXCollections.observableArrayList();

        for(Player plr: playerList){
            fxCollectionsPlrList.add(plr);
        }
        Table.setItems(fxCollectionsPlrList);
    }

}
