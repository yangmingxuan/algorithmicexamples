package algorithms.array;

/***
 * Let's play the minesweeper game (Wikipedia, online game)!

    You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.
    
    Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:
    
    If a mine ('M') is revealed, then the game is over - change it to 'X'.
    If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
    If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
    Return the board when no more squares will be revealed.
     
    
    Example 1:
    
    Input: 
    
    [['E', 'E', 'E', 'E', 'E'],
     ['E', 'E', 'M', 'E', 'E'],
     ['E', 'E', 'E', 'E', 'E'],
     ['E', 'E', 'E', 'E', 'E']]
    
    Click : [3,0]
    
    Output: 
    
    [['B', '1', 'E', '1', 'B'],
     ['B', '1', 'M', '1', 'B'],
     ['B', '1', '1', '1', 'B'],
     ['B', 'B', 'B', 'B', 'B']]
    
    Explanation:
    
    Example 2:
    
    Input: 
    
    [['B', '1', 'E', '1', 'B'],
     ['B', '1', 'M', '1', 'B'],
     ['B', '1', '1', '1', 'B'],
     ['B', 'B', 'B', 'B', 'B']]
    
    Click : [1,2]
    
    Output: 
    
    [['B', '1', 'E', '1', 'B'],
     ['B', '1', 'X', '1', 'B'],
     ['B', '1', '1', '1', 'B'],
     ['B', 'B', 'B', 'B', 'B']]
    
    Explanation:
    
     
    
    Note:
    
    The range of the input matrix's height and width is [1,50].
    The click position will only be an unrevealed square ('M' or 'E'), which also means the input board contains at least one clickable square.
    The input board won't be a stage when game is over (some mines have been revealed).
    For simplicity, not mentioned rules should be ignored in this problem. For example, you don't need to reveal all the unrevealed mines when the game is over, consider any cases that you will win the game or flag any squares.
 * @author miche
 *
 */
public class Minesweeper {

    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0 || board[0].length == 0 || click == null || click.length == 0) {
            return board;
        }
        int row = click[0], col = click[1];
        //click must in board (if row < 0 .... col >= board[0].length)
        if(board[row][col] == 'M') {
            board[row][col] = 'X';
            return board;
        }
        
        dfs(board, row, col);
    
        return board;
    }

    public void dfs(char[][] board, int row, int col) {
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            //out of board
            return;
        }
        if(board[row][col] == 'B' || board[row][col] == 'M' || Character.isDigit(board[row][col])) {
            //termination cases
            return;
        }
        int[][] directions = {{0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}}; // 8 directions.
        int count = 0; //represents how many mines are adjacent to this revealed square
        for(int[] x : directions) {
            int newRow = row + x[0];
            int newCol = col + x[1];
            if(newRow < 0 || newRow >= board.length || newCol < 0 || newCol >= board[0].length) {
                //out of board;
                continue;
            }
            if(board[newRow][newCol] == 'M') {
                count++;
            }
        }
        
        if(count > 0) {
            //mark the digit
            board[row][col] = (char)(count + '0');
        } else {
            board[row][col] = 'B';
            //go to the adjacent
            for(int[] x : directions) {
                dfs(board, row+x[0], col+x[1]);
            }
        }
    }

}
