package ClientFiles;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ReplicatedClasses.Player;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class gameController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane Map;

    @FXML
    private Label Turn;

    static int player = 0;
    private boolean gameEnded = false;

    @FXML
    public Button restartButton;

    @FXML
    private Button Ready1Button;

    @FXML
    private Button Ready2Button;

    @FXML
    private Button backButton;

    private Player p1;
    private Player p2;
    private Board board;

    @FXML
    void initialize() {
        p1 = new Player('X');
        p2 = new Player('O');
        board = new Board();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++) {
                ImageView imageView = new ImageView(getURL("empty.png"));
                final int col = i;
                final int row = j;
                imageView.setOnMouseClicked(evt -> handleMove(row, col) );
                Map.setHgap(10);
                Map.setVgap(10);
                Map.add(imageView, i, j);
            }
        }
        Turn.setText("Player X turn");


        backButton.setOnAction(event -> {
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

    private void handleMove(int row, int col) {
        if (!gameEnded) {
            Player currentPlayer = (player == 0) ? p1 : p2;

            if (board.isEmpty(row, col)) {
                currentPlayer.play(row, col, board);
                player = (player == 0) ? 1 : 0;

                if (board.detectwin() != null) {
                    Turn.setText("Player " + currentPlayer.getMark() + " is win");
                    String[] position = board.detectwin();
                    for (String pos : position) {
                        int x = Integer.parseInt(pos.split(",")[0]);
                        int y = Integer.parseInt(pos.split(",")[1]);
                        currentPlayer.setMark((player == 0) ? '2' : '1');
                        currentPlayer.play(x, y, board);
                    }
                    player = -1;
                    gameEnded = true;
                } else {
                    if (board.isFull()) {
                        Turn.setText("Game over");
                        player = -1;
                        gameEnded = true;
                    } else {
                        Turn.setText("Player " + ((player == 0) ? "O" : "X") + " turn");
                    }
                }

                update(board.getGrid());
            }
        }
    }

    public void handleRestart() {
        player = 0;
        gameEnded = false;
        Turn.setText("Player X turn");
        board.reset();
        p1.setMark('X');
        p2.setMark('O');
        update(board.getGrid());
    }
    
    public static Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }
    private String getURL(String name) {
        File f = new File(name);
        String absolute = f.getAbsolutePath();
        absolute = absolute.substring(0, absolute.length() - name.length());
        return "file:" + File.separator + absolute + "src" + File.separator + "main" +
                File.separator + "java" + File.separator + "ClientFiles" + File.separator + "Images" + File.separator + name;
    }

    void update(char[][] grid) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                switch (grid[i][j]) {
                    case 'X': {
                        ImageView imv = (ImageView) getNodeByRowColumnIndex(i, j,Map);
                        imv.setImage(new Image(getURL("x_blue.png")));
                    }
                    break;
                    case 'O': {
                        ImageView imv = (ImageView) getNodeByRowColumnIndex(i, j,Map);
                        imv.setImage(new Image(getURL("o_red.png")));
                    }
                    break;
                    case '.': {
                        ImageView imv = (ImageView) getNodeByRowColumnIndex(i, j,Map);
                        imv.setImage(new Image(getURL("empty.png")));
                    }
                    break;
                    case '1': {
                        ImageView imv = (ImageView) getNodeByRowColumnIndex(i, j,Map);
                        imv.setImage(new Image(getURL("x_yellow.png")));
                    }
                    break;
                    case '2': {
                        ImageView imv = (ImageView) getNodeByRowColumnIndex(i, j,Map);
                        imv.setImage(new Image(getURL("o_yellow.png")));
                    }
                    break;
                }
            }
        }
    }
}
