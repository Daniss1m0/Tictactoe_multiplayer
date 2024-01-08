package ClientFiles;
/**
 * The Board class represents the Tic Tac Toe game board and provides methods for managing the game state.
 */
public class Board {

    private final char[][] grid;
    private int counter;
    /**
     * Enumeration representing the possible win conditions for the game.
     */
    public enum WinCondition {
        NONE, ROW, COLUMN, DIAGONAL_RIGHT, DIAGONAL_LEFT
    }

    private WinCondition winCondition;

    public WinCondition getWinCondition() {
        return winCondition;
    }
    /**
     * Constructor to initialize the game board.
     */
    public Board() {
        grid = new char[3][3];
        reset();
    }
    /**
     * Resets the game board to its initial state.
     */
    public void reset() {
        counter = 0;
        winCondition = WinCondition.NONE;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                grid[i][j] = '.';
    }
    /**
     * Checks if a specific cell on the board is empty.
     *
     * @param x The row index.
     * @param y The column index.
     * @return True if the cell is empty, false otherwise.
     */
    public boolean isEmpty(int x, int y){
        return grid[x][y] == '.';
    }
    /**
     * Checks if the game board is full.
     *
     * @return True if the board is full, false otherwise.
     */
    public boolean isFull() {
        return counter == 9;
    }
    /**
     * Detects if there is a win condition on the board and returns the winning sequence.
     *
     * @return An array of strings representing the winning sequence, or null if there is no win.
     */
    public String[] detectWin() {
        StringBuilder sequence = new StringBuilder();
        for (int i = 0; i < 3; i++)
            if (grid[i][0] == grid[i][1] && grid[i][0] == grid[i][2])
                if (grid[i][0] != '.') {
                    sequence.append(i).append(",").append(0).append(" ");
                    sequence.append(i).append(",").append(1).append(" ");
                    sequence.append(i).append(",").append(2).append(" ");
                    winCondition = WinCondition.ROW;
                    return sequence.toString().split(" ");
                }
        for (int j = 0; j < 3; j++)
            if (grid[0][j] == grid[1][j] && grid[0][j] == grid[2][j])
                if (grid[0][j] != '.') {
                    sequence.append(0).append(",").append(j).append(" ");
                    sequence.append(1).append(",").append(j).append(" ");
                    sequence.append(2).append(",").append(j).append(" ");
                    winCondition = WinCondition.COLUMN;
                    return sequence.toString().split(" ");
                }
        if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2])
            if (grid[0][0] != '.') {
                sequence.append(0).append(",").append(0).append(" ");
                sequence.append(1).append(",").append(1).append(" ");
                sequence.append(2).append(",").append(2).append(" ");
                winCondition = WinCondition.DIAGONAL_RIGHT;
                return sequence.toString().split(" ");
            }
        if (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0])
            if (grid[1][1] != '.') {
                sequence.append(0).append(",").append(2).append(" ");
                sequence.append(1).append(",").append(1).append(" ");
                sequence.append(2).append(",").append(0).append(" ");
                winCondition = WinCondition.DIAGONAL_LEFT;
                return sequence.toString().split(" ");
            }
        return null;
    }
    /**
     * Sets the mark for a specific cell on the board.
     *
     * @param x    The row index.
     * @param y    The column index.
     * @param mark The mark to be set.
     */
    public void set(int x, int y, char mark) {
        grid[x][y] = mark;
        counter += 1;
    }
    /**
     * Gets the current state of the game board.
     *
     * @return A 2D array representing the game board.
     */
    public char[][] getGrid(){
        return grid;
    }
}