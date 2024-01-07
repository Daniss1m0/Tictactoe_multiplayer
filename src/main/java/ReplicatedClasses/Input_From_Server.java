package ReplicatedClasses;

import java.io.Serial;
import java.io.Serializable;

public class Input_From_Server implements Serializable { // klasa sluzaca do sprawdzania do jakiej klasy trzeba rzutowac obiekt
    @Serial
    private static final long serialVersionUID = 13;


    public String infoType;
    public String playerName;
    public int charX;
    public int charY;

    public boolean isPlr1;
    public boolean isReady;

    public boolean restartBoard;

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

    public boolean isPlr1() {
        return isPlr1;
    }

    public void setPlr1(boolean plr1) {
        isPlr1 = plr1;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
}
