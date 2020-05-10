package algorithms.struct;

import java.util.Arrays;

/***
 * Design a Tic-tac-toe game that is played between two players on a n x n grid.

    You may assume the following rules:
    
    A move is guaranteed to be valid and is placed on an empty block.
    Once a winning condition is reached, no more moves is allowed.
    A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
    Example:
    Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.
    
    TicTacToe toe = new TicTacToe(3);
    
    toe.move(0, 0, 1); -> Returns 0 (no one wins)
    |X| | |
    | | | |    // Player 1 makes a move at (0, 0).
    | | | |
    
    toe.move(0, 2, 2); -> Returns 0 (no one wins)
    |X| |O|
    | | | |    // Player 2 makes a move at (0, 2).
    | | | |
    
    toe.move(2, 2, 1); -> Returns 0 (no one wins)
    |X| |O|
    | | | |    // Player 1 makes a move at (2, 2).
    | | |X|
    
    toe.move(1, 1, 2); -> Returns 0 (no one wins)
    |X| |O|
    | |O| |    // Player 2 makes a move at (1, 1).
    | | |X|
    
    toe.move(2, 0, 1); -> Returns 0 (no one wins)
    |X| |O|
    | |O| |    // Player 1 makes a move at (2, 0).
    |X| |X|
    
    toe.move(1, 0, 2); -> Returns 0 (no one wins)
    |X| |O|
    |O|O| |    // Player 2 makes a move at (1, 0).
    |X| |X|
    
    toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
    |X| |O|
    |O|O| |    // Player 1 makes a move at (2, 1).
    |X|X|X|
    Follow up:
    Could you do better than O(n2) per move() operation?
 * @author miche
 *
 */
public class TicTacToe {

    private int N;
    private char[][] grid;
    private char[] cells = {' ', 'X', 'O'};

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        N = n;
        grid = new char[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(grid[i], ' ');
        }
    }

    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins.
    */
    public int move(int row, int col, int player) {
        if(row < 0 || row >= N || col < 0 || col >= N) return -1;
        if(grid[row][col] != cells[0]) return -1;
        if(player <= 0 || player >= cells.length) return -1;
        grid[row][col] = cells[player];
        if(isWin(row, col, player)) return player;
        return 0;
    }

    public boolean isWin(int row, int col, int player) {
        boolean isWin = true;
        for(int i = 0; i < N; i++) {
            if(grid[row][i] != cells[player]) {
                isWin = false;
            }
        }
        if(isWin) return true;

        isWin = true;
        for(int i = 0; i < N; i++) {
            if(grid[i][col] != cells[player]) {
                isWin = false;
            }
        }
        if(isWin) return true;

        if(row - col != 0 && row + col != N-1) {
            return false;
        }

        isWin = true;
        for(int i = 0; i < N; i++) {
            if(grid[i][i] != cells[player]) {
                isWin = false;
            }
        }
        if(isWin) return true;
  
        isWin = true;
        for(int i = 0; i < N; i++) {
            if(grid[i][N-1-i] != cells[player]) {
                isWin = false;
            }
        }
        if(isWin) return true;
        
        return false;
    }
}

class TicTacToe2 {
    private int N;
    private int[] rows;
    private int[] cols;
    private int diagonal, antidiagonal;
    private int[] cells = {0, 1, -1};

    /** Initialize your data structure here. */
    public TicTacToe2(int n) {
        N = n;
        rows = new int[n];
        cols = new int[n];
    }

    /** Player {player} makes a move at ({row}, {col}).
    @param row The row of the board.
    @param col The column of the board.
    @param player The player, can be either 1 or 2.
    @return The current winning condition, can be either:
            0: No one wins.
            1: Player 1 wins.
            2: Player 2 wins.
*/
    public int move(int row, int col, int player) {
        if(row < 0 || row >= N || col < 0 || col >= N) return -1;
        if(player <= 0 || player >= cells.length) return -1;
        rows[row] += cells[player];
        cols[col] += cells[player];
        if(row == col) diagonal += cells[player];
        if(row == N-1-col) antidiagonal += cells[player];
        
        if(rows[row] == N || cols[col] == N || diagonal == N || antidiagonal == N) {
            return 1;
        }
        if(rows[row] == -N || cols[col] == -N || diagonal == -N || antidiagonal == -N) {
            return 2;
        }
        
        return 0;
    }
    
}
