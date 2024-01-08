package ReplicatedClasses;

import java.io.Serializable;
/**
 * The BoardBoxCommunication class represents a serializable object used to communicate information about a board box.
 * It contains the box number associated with a specific action.
 */
public class BoardBoxCommunication implements Serializable {

    public int boxNumber=0;
    /**
     * Constructor for the {@code BoardBoxCommunication}.
     *
     * @param boxNumber The box number associated with a specific action.
     */
    public BoardBoxCommunication(int boxNumber) {
        this.boxNumber = boxNumber;
    }

}
