package ReplicatedClasses;

import java.io.Serial;
import java.io.Serializable;
import ClientFiles.Board;
/**
 * The Player class represents a player in the game. It implements Serializable to support object serialization.
 */
public class Player implements Serializable {

    @Serial private static final long serialVersionUID = 11;

    public int getPlayer_ID() {
        return Player_ID;
    }

    private int Player_ID;
    private String NickName;
    private int Age;

    public int getWins() {
        return Wins;
    }

    public void setWins(int wins) {
        Wins = wins;
    }

    private int Wins;

    private char mark;
    /**
     * Default constructor for the Player class.
     */
    public Player() {}
    /**
     * Parameterized constructor for creating a player with a specific mark.
     *
     * @param mark The mark associated with the player.
     */
    public Player(char mark){
        this.mark = mark;
    }

    /**
     * Constructor for creating a player with a nickname and age. Used for inserting into the database.
     *
     * @param nickName The nickname of the player.
     * @param age      The age of the player.
     */
    public Player(String nickName, int age) {
        NickName = nickName;
        Age = age;
    }
    /**
     * Constructor for creating a player with a specific ID, nickname, age, and number of wins.
     * Used for selecting from the database.
     *
     * @param PlayerID The ID of the player.
     * @param nickName The nickname of the player.
     * @param age      The age of the player.
     * @param wins     The number of wins by the player.
     */
    public Player(int PlayerID, String nickName, int age, int wins) {
        Player_ID = PlayerID;
        NickName = nickName;
        Age = age;
        Wins = wins;
    }
    /**
     * Makes a move on the game board at the specified position (x, y) using the player's mark.
     *
     * @param x     The x-coordinate of the move.
     * @param y     The y-coordinate of the move.
     * @param board The game board.
     */
    public void play(int x, int y, Board board){
        board.set(x,y,mark);
    }
    /**
     * Gets the mark associated with the player.
     *
     * @return The player's mark.
     */
    public char getMark(){
        return mark;
    }
    /**
     * Sets the mark associated with the player.
     *
     * @param mark The player's mark.
     */
    public void setMark(char mark){
        this.mark = mark;
    }
    /**
     * Gets the nickname of the player.
     *
     * @return The player's nickname.
     */
    public String getNickName() {
        return NickName;
    }
    /**
     * Gets the age of the player.
     *
     * @return The player's age.
     */
    public int getAge() {
        return Age;
    }

}
