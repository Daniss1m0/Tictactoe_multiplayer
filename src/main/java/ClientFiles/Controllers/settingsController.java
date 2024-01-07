package ClientFiles.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ClientFiles.Client;
import ClientFiles.DBConnection.DataBaseHandler;
import ReplicatedClasses.CommandClass;
import ReplicatedClasses.Commands;
import ReplicatedClasses.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

public class settingsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Spinner<Integer> Spinner;

    @FXML
    private TextField NickName_field;

    @FXML
    private Button SaveButton;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(3,99);
        valueFactory.setValue(3);
        Spinner.setValueFactory(valueFactory);

        SaveButton.setOnAction(event -> {
            DataBaseHandler dbHandler = new DataBaseHandler();

            String NickName = NickName_field.getText().trim();
            Integer Age = Spinner.getValue();

            if(!NickName.equals("") && !Age.equals("")){

                CommandClass newCommand=new CommandClass(Commands.registerPlayer);
                newCommand.setPlayerName(NickName);
                newCommand.setPlayerAge(Age);
                Client.localClient.sendMessage(newCommand);
                Client.localClient.playerName=NickName;
            }
            else
                System.out.println("Error!");

        });
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

    }

}
