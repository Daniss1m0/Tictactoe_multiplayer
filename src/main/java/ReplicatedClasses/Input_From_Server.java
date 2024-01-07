package ReplicatedClasses;

import java.io.Serial;
import java.io.Serializable;

public class Input_From_Server implements Serializable { // klasa sluzaca do sprawdzania do jakiej klasy trzeba rzutowac obiekt
    @Serial
    private static final long serialVersionUID = 13;


    public String infoType;
    public String playerName;
    public Input_From_Server(){}
    public Input_From_Server(String infoType) {
        this.infoType = infoType;
    }

    public String getInfoType() {
        return infoType;
    }
    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
