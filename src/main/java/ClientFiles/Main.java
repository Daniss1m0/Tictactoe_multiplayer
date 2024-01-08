package ClientFiles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Main class for Tic Tac Toe multiplayer.
 * Extends the Application class of the JavaFX language
 */
public class Main extends Application {
    /**
     * The starting point of the JavaFX application.
     *
     * @param stage The primary stage for the application, where scenes are set.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Tictactoe-multiplayer");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * The main method to launch the JavaFX application.
     *
     * @param args Command line arguments (not used in our project).
     */
    public static void main(String[] args) {
        Client.startClient();
        launch();
    }
}