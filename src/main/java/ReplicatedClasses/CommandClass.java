package ReplicatedClasses;


import java.io.Serial;
import java.io.Serializable;
/**
 * The CommandClass class represents a serializable command object used in communication between the client and server.
 * It encapsulates various parameters and commands for different actions.
 */
public class CommandClass implements Serializable {
    @Serial private static final long serialVersionUID = 12;
    /**
     * The command string representing the action to be taken.
     */
    public String command="";
    public  String playerName;
    public  int playerAge;
    public int charX;
    public int charY;


    public long RoomId;
    /**
     * Default constructor for the CommandClass.
     */
    public CommandClass(){}
    /**
     * Parameterized constructor for the CmmandClass.
     *
     * @param command The command string representing the action to be taken.
     */
    public CommandClass(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setRoomId(long roomId) {
        RoomId = roomId;
    }

    public void setPlayerAge(int playerAge) {
        this.playerAge = playerAge;
    }

    public void setCharX(int charX) {
        this.charX = charX;
    }

    public void setCharY(int charY) {
        this.charY = charY;
    }
}


