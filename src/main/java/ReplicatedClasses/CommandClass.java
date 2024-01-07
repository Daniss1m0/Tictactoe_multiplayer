package ReplicatedClasses;


import java.io.Serial;
import java.io.Serializable;

public class CommandClass implements Serializable {
    @Serial private static final long serialVersionUID = 12;


    public String command="";
    public int squareNumber=0; // square from tic tac toe board
    public  String playerName;
    public  int playerAge;
    public int charX;
    public int charY;


    public long RoomId;

    public CommandClass(){} // konstruktor domyslny
    public CommandClass(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getSquareNumber() {
        return squareNumber;
    }

    public void setSquareNumber(int squareNumber) {
        this.squareNumber = squareNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public long getRoomId() {
        return RoomId;
    }

    public void setRoomId(long roomId) {
        RoomId = roomId;
    }

    public int getPlayerAge() {
        return playerAge;
    }

    public void setPlayerAge(int playerAge) {
        this.playerAge = playerAge;
    }

    public int getCharX() {
        return charX;
    }

    public void setCharX(int charX) {
        this.charX = charX;
    }

    public int getCharY() {
        return charY;
    }

    public void setCharY(int charY) {
        this.charY = charY;
    }
}


