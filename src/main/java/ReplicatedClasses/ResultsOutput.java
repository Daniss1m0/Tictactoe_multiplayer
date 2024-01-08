package ReplicatedClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * The ResultsOutput class is used for serializing and sending objects related to game results to clients.
 * It extends Input_From_Server and implements Serializable.
 */
public class ResultsOutput extends Input_From_Server implements Serializable { // klasa uzywana do serializacji i wysylania obiektow do klientow
    public List<Player> playerList= new ArrayList<>();
    //public String infoType="ResultsOutput";
    /**
     * Creates a new instance of {@code ResultsOutput} with the specified list of players and information type.
     *
     * @param playerList The list of players containing game results.
     * @param infoType   The information type associated with the object.
     */
    public ResultsOutput(List<Player> playerList,String infoType) {
        this.playerList = playerList;
        this.infoType=infoType;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }
    /**
     * Sets the list of players containing game results.
     *
     * @param playerList The list of players.
     */
    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
