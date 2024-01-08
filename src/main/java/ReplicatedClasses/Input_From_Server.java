package ReplicatedClasses;

import java.io.Serial;
import java.io.Serializable;
/**
 * The Input_From_Server class represents information received from the server.
 * It is used to determine the type of information and facilitate proper casting of objects.
 * This class implements Serializable to support object serialization.
 */
public class Input_From_Server implements Serializable {
    @Serial
    private static final long serialVersionUID = 13;

    public String infoType;
    public String playerName;
    public int charX;
    public int charY;

    public boolean isPlr1;
    public boolean isReady;

    public boolean restartBoard;
    /**
     * Default constructor for the {@code Input_From_Server} class.
     */
    public Input_From_Server(){}
    /**
     * Gets the type of information.
     *
     * @return The type of information.
     */
    public String getInfoType() {
        return infoType;
    }
    /**
     * Gets the player's name associated with the information.
     *
     * @return The player's name.
     */
    public String getPlayerName() {
        return playerName;
    }
    /**
     * Gets the x-coordinate of a character on the game board.
     *
     * @return The x-coordinate.
     */
    public int getCharX() {
        return charX;
    }
    /**
     * Gets the y-coordinate of a character on the game board.
     *
     * @return The Y-coordinate.
     */
    public int getCharY() {
        return charY;
    }
    /**
     * Checks if the player is Player 1.
     *
     * @return true if the player is Player 1, {@code false} otherwise.
     */
    public boolean isPlr1() {
        return isPlr1;
    }
    /**
     * Checks if the player is ready.
     *
     * @return true if the player is ready, {@code false} otherwise.
     */
    public boolean isReady() {
        return isReady;
    }

}
