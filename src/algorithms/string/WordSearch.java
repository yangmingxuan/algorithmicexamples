package algorithms.string;

/***
 * Given a 2D board and a word, find if the word exists in the grid.

    The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
    
    Example:
    
    board =
    [
      ['A','B','C','E'],
      ['S','F','C','S'],
      ['A','D','E','E']
    ]
    
    Given word = "ABCCED", return true.
    Given word = "SEE", return true.
    Given word = "ABCB", return false.
     
    
    Constraints:
    
    board and word consists only of lowercase and uppercase English letters.
    1 <= board.length <= 200
    1 <= board[i].length <= 200
    1 <= word.length <= 10^3
 * @author miche
 *
 */
public class WordSearch {

    public boolean exist(char[][] board, String word) {
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[0].length; col++) {
                if(backtrack(board, word, row, col, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean backtrack(char[][] board, String word, int row, int col, int index) {
        int[] dx = {0, 0, 1,-1};
        int[] dy = {1,-1, 0, 0};

        //If the entire string is compared
        if(word.length() == index) {
            return true;
        }

        //If moved out of bounds, or characters are not equal
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length
                || board[row][col] != word.charAt(index)) {
            return false;
        }

        boolean ret = false;
        board[row][col] = '*'; //Compared
        for(int i = 0; i < 4; i++) {
            if(backtrack(board, word, row+dy[i], col+dx[i], index+1)) {
                ret = true;
                break;
            }
        }
        board[row][col] = word.charAt(index); //Assign the original value back

        return ret;
    }
}
