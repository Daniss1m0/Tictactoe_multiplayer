package ReplicatedClasses;


import java.io.Serializable;

public class CommandClass implements Serializable {
    public String command="";
    public int squareNumber=0; // square from tic tac toe board

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
}


