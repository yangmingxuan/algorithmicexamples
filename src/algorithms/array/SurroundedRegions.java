package algorithms.array;
/***
 * Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.

    A region is captured by flipping all 'O's into 'X's in that surrounded region.
    
     
    
    Example 1:
    
    
    Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
    Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
    Explanation: Surrounded regions should not be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
    Example 2:
    
    Input: board = [["X"]]
    Output: [["X"]]
     
    
    Constraints:
    
    m == board.length
    n == board[i].length
    1 <= m, n <= 200
    board[i][j] is 'X' or 'O'.
 * @author miche
 *
 */
public class SurroundedRegions {

    public void solve(char[][] board) {
        if(board.length == 0) return;
        for(int i = 0; i < board[0].length; i++){
            if(board[0][i] == 'O')
                DFS(board, 0, i);
            if(board[board.length - 1][i] == 'O')
                DFS(board, board.length - 1, i);
        }
        
        for(int i = 0; i < board.length; i++){
            if(board[i][0] == 'O')
                DFS(board, i, 0);
            if(board[i][board[0].length - 1] == 'O')
                DFS(board, i, board[0].length - 1);
        }
        
       /**
        * If it is an O on the boundary, or an O connected to the boundary O in four dimensions, do not change, other transfer into X
        **/
        for(int i =0; i <board.length; i++){
           for(int j =0; j <board[0].length; j++){
               if(board[i][j] == '#')
                   board[i][j] = 'O';
               else
                   board[i][j] = 'X';
           }
       }
       
       return;
    }

    /**
     * If it is an O on the boundary, or an O connected to the boundary O in four dimensions, set the flag
    **/
    private void DFS(char[][] board, int i, int j){
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O')
            return;
        board[i][j] = '#';
        DFS(board, i + 1, j);
        DFS(board, i - 1, j);
        DFS(board, i, j + 1);
        DFS(board, i, j - 1);
    }

}
