package ReplicatedClasses;

import java.io.Serializable;

public class BoardBoxCommunication implements Serializable {

    public int boxNumber=0;

    public BoardBoxCommunication(int boxNumber) {
        this.boxNumber = boxNumber;
    }

    public int getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(int boxNumber) {
        this.boxNumber = boxNumber;
    }
}
