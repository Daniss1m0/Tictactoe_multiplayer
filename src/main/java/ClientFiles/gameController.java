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

    @FXML
    private Button Ready1Button;

    @FXML
    private Button Ready2Button;

    @FXML
    private Button backButton;

    static int player = 0;

    @FXML
    void initialize() {
        Player p1 = new Player('X');
        Player p2 = new Player('O');
        Player.board = new Board();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++) {
                ImageView imageView = new ImageView(getURL("empty.png"));
                final int col = i;
                final int row = j;
                imageView.setOnMouseClicked(evt -> {
                    if(player == 0){
                        if(Player.board.isEmpty(row,col)){
                            p1.play(row, col);
                            player = 1;

                            if(Player.board.dectectwin() != null) {
                                Turn.setText("player X is win");
                                String position[] = Player.board.dectectwin();
                                for(String pos : position) {
                                    int x = Integer.parseInt(pos.split(",")[0]);
                                    int y = Integer.parseInt(pos.split(",")[1]);
                                    p1.setMark('1');
                                    p1.play(x, y);
                                }
                                player = -1;
                            }
                            else{
                                if(Player.board.isFull()) {
                                    Turn.setText("game over");
                                    player = -1;
                                }
                                else {
                                    Turn.setText("player 0 turn");
                                }
                            }
                        }
                    }
                    else {
                        if(player != -1)
                            if(Player.board.isEmpty(row,col)){
                                p2.play(row, col);
                                player = 0;

                                if(Player.board.dectectwin() != null) {
                                    Turn.setText("player 0 is win");
                                    String position[] = Player.board.dectectwin();
                                    for(String pos : position) {
                                        int x = Integer.parseInt(pos.split(",")[0]);
                                        int y = Integer.parseInt(pos.split(",")[1]);
                                        p1.setMark('2');
                                        p1.play(x, y);
                                    }
                                    player = -1;
                                }
                                else{
                                    if(Player.board.isFull()) {
                                        Turn.setText("game over");
                                        player = -1;
                                    }
                                    else {
                                        Turn.setText("player X turn");
                                    }
                                }
                            }
                    }
                    update(Player.board.grid);
                });

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

    public static Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if (gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }
    public String getURL(String name){
        File f = new File(name);
        String absolute = f.getAbsolutePath();
        absolute = absolute.substring(0,absolute.length() - name.length());
        return "file:\\" + absolute + "src\\main\\java\\ClientFiles\\Images\\" + name;
    }

    void update(char[][] grid) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                switch (grid[i][j]) {
                    case 'X': {
                        ImageView imv = (ImageView) getNodeByRowColumnIndex(i, j,Map);
                        imv.setImage(new Image(getURL("x_blue.png")));
                    }
                    ;
                    break;
                    case 'O': {
                        ImageView imv = (ImageView) getNodeByRowColumnIndex(i, j,Map);
                        imv.setImage(new Image(getURL("o_red.png")));
                    }
                    ;
                    break;
                    case '.': {
                        ImageView imv = (ImageView) getNodeByRowColumnIndex(i, j,Map);
                        imv.setImage(new Image(getURL("empty.png")));
                    }
                    ;
                    break;
                    case '1': {
                        ImageView imv = (ImageView) getNodeByRowColumnIndex(i, j,Map);
                        imv.setImage(new Image(getURL("x_yellow.png")));
                    }
                    ;
                    break;
                    case '2': {
                        ImageView imv = (ImageView) getNodeByRowColumnIndex(i, j,Map);
                        imv.setImage(new Image(getURL("o_yellow.png")));
                    }
                    ;
                    break;
                }
            }
        }
    }
}
