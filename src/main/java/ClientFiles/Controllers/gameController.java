package ClientFiles.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ClientFiles.Board;
import ClientFiles.Client;
import ReplicatedClasses.CommandClass;
import ReplicatedClasses.Commands;
import ReplicatedClasses.Input_From_Server;
import ReplicatedClasses.Player;
import javafx.application.Platform;
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
/**
 * The `gameController` class controls the behavior of the game view in the Tic Tac Toe application.
 * It manages the game board, player turns, and interactions between the client and server.
 */
public class gameController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane Map;

    @FXML
    private Label Turn;

    @FXML private Label plr1Text;
    @FXML private Label plr2Text;

    private int player = 0;
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

    private boolean plr1Ready=false;
    private boolean plr2Ready=false;

    private String plr1Name;
    private String plr2Name;

    private boolean isPlr1Locally;

    private boolean kolejPlr1=true;

    public static gameController localGameController;

    /**
     * Sets whether the local player is Player 1 or Player 2.
     *
     * @param is True if the local player is Player 1, false otherwise.
     */
    public void setIsPlr1Locally(boolean is)
    {
        this.isPlr1Locally=is;
    }
    /**
     * Sets the name for Player 1.
     *
     * @param name The name of Player 1.
     */
    public void setPlr1Name(String name)
    {
        System.out.println("USTAWIA IMIE GRACZA 1: "+name);
        this.plr1Name=name;
        plr1Text.setText(name);
        setIsPlr1Locally(true);
        System.out.println("set plr1 text: "+name);
    }
    /**
     * Sets the name for Player 2.
     *
     * @param name The name of Player 2.
     */
    public void setPlr2Name(String name)
    {
        System.out.println("USTAWIA IMIE GRACZA 2: "+name);
        this.plr2Name=name;
        plr2Text.setText(name);
        setIsPlr1Locally(false);
        System.out.println("set plr2 text: "+name);
    }
    /**
     * Sets the label for Player 1 when leaving the game.
     */
    public void plr1Leave()
    {
        plr1Text.setText("(waiting for player...)");
        this.plr1Name="";
    }
    /**
     * Sets the label for Player 2 when leaving the game.
     */
    public void plr2Leave()
    {
        plr2Text.setText("(waiting for player...)");
        this.plr2Name="";
    }
    /**
     * Sets the name for a player based on whether they are Player 1 or Player 2.
     *
     * @param isPlr1 True if the player is Player 1, false otherwise.
     * @param hisName The name of the player.
     */
    public void setPlrName(boolean isPlr1, String hisName)
    {
        Platform.runLater(()->{
            if(isPlr1){
                setPlr1Name(hisName);
            }else{
                setPlr2Name(hisName);
            }
        });
    }
    /**
     * Sets the ready status for a player based on whether they are Player 1 or Player 2.
     *
     * @param isPlr1 True if the player is Player 1, false otherwise.
     * @param isReady True if the player is ready, false otherwise.
     */
    public void setReady(boolean isPlr1, boolean isReady)
    {
        Platform.runLater(()->{
            if(isPlr1)
            {
                if(isReady){
                    Ready1Button.setStyle("-fx-background-color: green;");
                    System.out.println("USTAWIA PLR1 NA READY");
                    this.plr1Ready=true;
                }else{
                    Ready1Button.setStyle("-fx-background-color: red;");
                    System.out.println("USTAWIA PLR1 NA NOT READY");
                    this.plr1Ready=false;
                }
            }else{
                if(isReady){
                    Ready2Button.setStyle("-fx-background-color: green;");
                    System.out.println("USTAWIA PLR2 NA READY");
                    this.plr2Ready=true;
                }else{
                    Ready2Button.setStyle("-fx-background-color: red;");
                    System.out.println("USTAWIA PLR2 NA NOT READY");
                    this.plr2Ready=false;
                }
            }
        });
    }
    /**
     * Initializes the game controller. Sets up the game board, players, and button actions.
     */
    @FXML
    void initialize() { //zmienic konstruktor zeby nie tworzyl nowych graczej, a bral juz istniejacych
        localGameController=this;
        System.out.println("local game controlled okreslony");

        CommandClass newCommand=new CommandClass(Commands.joinRoom);
        newCommand.setRoomId(Client.localClient.roomId);
        newCommand.setPlayerName(Client.localClient.playerName);
        Client.localClient.sendMessage(newCommand);

        p1 = new Player('X');
        p2 = new Player('O');
        board = new Board();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++) {
                ImageView imageView = new ImageView(getURL("empty.png"));
                final int col = i;
                final int row = j;
                imageView.setOnMouseClicked(evt -> makeMove(row, col) );
                Map.setHgap(10);
                Map.setVgap(10);
                Map.add(imageView, i, j);
            }
        }
        Turn.setText("Player X turn");


        backButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClientFiles/start.fxml"));
                Parent root = loader.load();

                CommandClass leaveCommand=new CommandClass(Commands.leaveRoom);
                Client.localClient.sendMessage(leaveCommand);

                Scene scene = new Scene(root);

                Stage stage = (Stage) backButton.getScene().getWindow();

                stage.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
    /**
     * Handles the ready status of Player 1.
     */
    public void onPlr1Ready()
    {
        if(isPlr1Locally)
        {
            sendReadyCommand();
        }
    }
    /**
     * Handles the ready status of Player 2.
     */
    public void onPlr2Ready()
    {
        if(!isPlr1Locally)
        {
            sendReadyCommand();
        }
    }
    /**
     * Handles the restart of the game.
     */
    public void onRestart()
    {
        sendRestartCommand();
    }
    /**
     * Sends a ready command to the server.
     * This method is used to inform the server that the player is ready to start the game.
     */
    private void sendReadyCommand()
    {
        CommandClass newReadyCommand=new CommandClass(Commands.ready);
        Client.localClient.sendMessage(newReadyCommand);
    }
    /**
     * Sends a restart command to the server.
     * This method is used to inform the server that the player wants to restart the game.
     */
    private void sendRestartCommand()
    {
        CommandClass newRestartCommand=new CommandClass(Commands.restart);
        Client.localClient.sendMessage(newRestartCommand);
    }
    /**
     * Makes a move on the game board.
     * This method is called when a player clicks on a cell on the game board. It sends the move information to the server.
     *
     * @param row The row index of the clicked cell.
     * @param col The column index of the clicked cell.
     */
    private void makeMove(int row,int col)
    {
        if(!(plr1Ready&&plr2Ready)){return;}
        // to dzieje sie po kliknieciu w jakies pole planszy do gry
        System.out.println("Makes Move");

        if(isPlr1Locally^kolejPlr1){
            System.out.println("Nie twoja kolej");
            return;
        } // jezeli nie jest jego kolej to nic nie robi

        CommandClass newMoveMessage=new CommandClass(Commands.setChar);
        newMoveMessage.setCharX(row);
        newMoveMessage.setCharY(col);
        Client.localClient.sendMessage(newMoveMessage);
    }
    /**
     * Handles a move received from the server.
     * This method is called when the server informs about a move made by a player.
     *
     * @param row The row index of the move.
     * @param col The column index of the move.
     */
    public void handleMove(int row, int col) {
        // to dzieje sie tylko po przyjsciu informacji od serwera
        System.out.println("HANDLES MOVE");
        kolejPlr1=!kolejPlr1; // czeka na ruch innego gracza albo swoj

        Platform.runLater(()->{
            if (!gameEnded) {
                Player currentPlayer = (player == 0) ? p1 : p2;

                if (board.isEmpty(row, col)) {
                    currentPlayer.play(row, col, board);
                    player = (player == 0) ? 1 : 0;

                    if (board.detectWin() != null) {
                        Turn.setText("Player " + currentPlayer.getMark() + " is win");
                        String[] position = board.detectWin();
                        for (String pos : position) {
                            int x = Integer.parseInt(pos.split(",")[0]);
                            int y = Integer.parseInt(pos.split(",")[1]);
                            if (board.getWinCondition() == Board.WinCondition.DIAGONAL_LEFT || board.getWinCondition() == Board.WinCondition.DIAGONAL_RIGHT)
                                currentPlayer.setMark((player == 0) ? '4' : '3');
                            else
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
                            Turn.setText("Player " + ((player == 0) ? "X" : "O") + " turn");
                        }
                    }

                    update(board.getGrid());
                }
            }
        });
    }
    /**
     * Handles the game restart.
     */
    public void handleRestart() {
        Platform.runLater(()->{
            Turn.setText("Player X turn");
        });

        player = 0;
        gameEnded = false;
        board.reset();
        p1.setMark('X');
        p2.setMark('O');
        update(board.getGrid());

        setReady(false,false);
        setReady(true,false);

        kolejPlr1=true;
    }
    /**
     * Gets the node in the GridPane at the specified row and column.
     *
     * @param row The row index.
     * @param column The column index.
     * @param gridPane The GridPane.
     * @return The node at the specified row and column.
     */
    public static Node getNode(final int row, final int column, GridPane gridPane) {
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
    /**
     * Generates the file URL for the given image name.
     *
     * @param name The name of the image file.
     * @return The file URL for the specified image.
     */
    private String getURL(String name) {
        File f = new File(name);
        String absolute = f.getAbsolutePath();
        absolute = absolute.substring(0, absolute.length() - name.length());
        return "file:" + File.separator + absolute + "src" + File.separator + "main" +
                File.separator + "java" + File.separator + "ClientFiles" + File.separator + "Images" + File.separator + name;
    }
    /**
     * Updates the game board UI based on the provided grid.
     *
     * @param grid The 2D array representing the game board state.
     */
    void update(char[][] grid) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                switch (grid[i][j]) {
                    case 'X': {
                        ImageView imv = (ImageView) getNode(i, j,Map);
                        imv.setImage(new Image(getURL("x_blue.png")));
                    }
                    break;
                    case 'O': {
                        ImageView imv = (ImageView) getNode(i, j,Map);
                        imv.setImage(new Image(getURL("o_red.png")));
                    }
                    break;
                    case '.': {
                        ImageView imv = (ImageView) getNode(i, j,Map);
                        imv.setImage(new Image(getURL("empty.png")));
                    }
                    break;
                    case '1': {
                        ImageView imv = (ImageView) getNode(i, j,Map);
                        if (board.getWinCondition() == Board.WinCondition.ROW)
                            imv.setImage(new Image(getURL("x_win_1.png")));
                        else
                            imv.setImage(new Image(getURL("x_win_2.png")));
                    }
                    break;
                    case '2': {
                        ImageView imv = (ImageView) getNode(i, j,Map);
                        if (board.getWinCondition() == Board.WinCondition.ROW)
                            imv.setImage(new Image(getURL("o_win_1.png")));
                        else
                            imv.setImage(new Image(getURL("o_win_2.png")));
                    }
                    break;
                    case '3': {
                        ImageView imv = (ImageView) getNode(i, j,Map);
                        if (board.getWinCondition() == Board.WinCondition.DIAGONAL_RIGHT)
                            imv.setImage(new Image(getURL("x_win_d1.png")));
                        else
                            imv.setImage(new Image(getURL("x_win_d2.png")));
                    }
                    break;
                    case '4': {
                        ImageView imv = (ImageView) getNode(i, j,Map);
                        if (board.getWinCondition() == Board.WinCondition.DIAGONAL_RIGHT)
                            imv.setImage(new Image(getURL("o_win_d1.png")));
                        else
                            imv.setImage(new Image(getURL("o_win_d2.png")));
                    }
                    break;
                }
            }
        }
    }
}