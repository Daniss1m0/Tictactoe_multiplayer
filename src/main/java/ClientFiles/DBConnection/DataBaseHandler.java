package ClientFiles.DBConnection;

import ReplicatedClasses.Player;

import java.sql.*;
/**
 * The DataBaseHandler class is responsible for handling database operations related to player data.
 */
public class DataBaseHandler extends Configs {

    Connection dbConnection;
    /**
     * Establishes a connection to the database.
     *
     * @return The established database connection.
     * @throws ClassNotFoundException If the database driver class is not found.
     * @throws SQLException           If a database access error occurs.
     */
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }
    /**
     * Inserts a new player into the database.
     *
     * @param player The player object to be inserted.
     */
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
    /**
     * Retrieves player data from the database.
     *
     * @param player The player object for which data is to be retrieved.
     * @return A ResultSet containing player data.
     */
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
