package com.example.tictactoemultiplayer;

public class Player {

    private int Player_ID;
    private String NickName;
    private int Age;
    private int Wins;

    public Player(int player_ID, String nickName, int age, int wins) {
        Player_ID = player_ID;
        NickName = nickName;
        Age = age;
        Wins = wins;
    }
    //?
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
