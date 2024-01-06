package ReplicatedClasses;

import java.io.Serial;
import java.io.Serializable;
import ClientFiles.Board;

public class Player implements Serializable {

    @Serial private static final long serialVersionUID = 11;

    private int Player_ID;
    private String NickName;
    private int Age;
    private int Wins;

    private char mark;

    public Player() {}

    public Player(char mark){
        this.mark = mark;
    }

    //konstruktor dla Inserta (moze zmienimy)
    public Player(String nickName, int age) {
        NickName = nickName;
        Age = age;
    }
    //konstruktor dla Selecta
    public Player(int PlayerID, String nickName, int age, int wins) {
        Player_ID = PlayerID;
        NickName = nickName;
        Age = age;
        Wins = wins;
    }

    public void play(int x, int y, Board board){
        board.set(x,y,mark);
    }
    public char getMark(){
        return mark;
    }
    public void setMark(char mark){
        this.mark = mark;
    }

    public int getPlayer_ID() {
        return Player_ID;
    }

    public String getNickName() {
        return NickName;
    }

    public int getAge() {
        return Age;
    }

    public int getWins() {
        return Wins;
    }

}
