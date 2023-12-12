package ClientFiles;

import ReplicatedClasses.Player;

import java.sql.*;

public class DataBaseHandler extends Configs {

    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void signUpPlayer(Player player){
        String insert = "INSERT INTO " + Const.PLAYERS_TABLE + "(" + Const.PLAYERS_NICKNAME + "," + Const.PLAYERS_AGE + ")" + "VALUES(?,?)";
        try {
            PreparedStatement prST = getDbConnection().prepareStatement(insert);
            prST.setString(1, player.getNickName());
            prST.setInt(2, player.getAge());
            //prST.setInt(3, player.getWins());

            prST.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getPlayer(Player player){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.PLAYERS_TABLE;

        try {
            PreparedStatement prST = getDbConnection().prepareStatement(select);
            resSet = prST.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
}
