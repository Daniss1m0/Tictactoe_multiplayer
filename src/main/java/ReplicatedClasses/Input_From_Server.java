package ReplicatedClasses;

import java.io.Serializable;

public class Input_From_Server implements Serializable { // klasa sluzaca do sprawdzania do jakiej klasy trzeba rzutowac obiekt
    public String infoType;

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
}
