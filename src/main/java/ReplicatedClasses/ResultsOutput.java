package ReplicatedClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultsOutput extends Input_From_Server implements Serializable { // klasa uzywana do serializacji i wysylania obiektow do klientow
    public List<Player> playerList= new ArrayList<>();
    //public String infoType="ResultsOutput";

    public ResultsOutput(List<Player> playerList,String infoType) {
        this.playerList = playerList;
        this.infoType=infoType;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
