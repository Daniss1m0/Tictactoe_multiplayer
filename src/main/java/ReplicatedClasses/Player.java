package ReplicatedClasses;

import java.io.Serializable;

public class Player implements Serializable {

    private int Player_ID;
    private String NickName;
    private int Age;
    private int Wins;

    public Player() {}

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

    //metody do gry?

}
