package algorithms.array;

/***
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

        Each row must contain the digits 1-9 without repetition.
        Each column must contain the digits 1-9 without repetition.
        Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
        
        A partially filled sudoku which is valid.
        
        The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
        
        Example 1:
        
        Input:
        [
          ["5","3",".",".","7",".",".",".","."],
          ["6",".",".","1","9","5",".",".","."],
          [".","9","8",".",".",".",".","6","."],
          ["8",".",".",".","6",".",".",".","3"],
          ["4",".",".","8",".","3",".",".","1"],
          ["7",".",".",".","2",".",".",".","6"],
          [".","6",".",".",".",".","2","8","."],
          [".",".",".","4","1","9",".",".","5"],
          [".",".",".",".","8",".",".","7","9"]
        ]
        Output: true
        Example 2:
        
        Input:
        [
          ["8","3",".",".","7",".",".",".","."],
          ["6",".",".","1","9","5",".",".","."],
          [".","9","8",".",".",".",".","6","."],
          ["8",".",".",".","6",".",".",".","3"],
          ["4",".",".","8",".","3",".",".","1"],
          ["7",".",".",".","2",".",".",".","6"],
          [".","6",".",".",".",".","2","8","."],
          [".",".",".","4","1","9",".",".","5"],
          [".",".",".",".","8",".",".","7","9"]
        ]
        Output: false
        Explanation: Same as Example 1, except with the 5 in the top left corner being 
            modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
        Note:
        
        A Sudoku board (partially filled) could be valid but is not necessarily solvable.
        Only the filled cells need to be validated according to the mentioned rules.
        The given board contain only digits 1-9 and the character '.'.
        The given board size is always 9x9.

 * @author miche
 *
 */
public class ValidSudoku {

    public ValidSudoku() {
        // TODO Auto-generated constructor stub
    }

    public boolean isValidSudoku(char[][] board) {
        for(int row = 0; row < 9; row++) {
            if(!isValidItem(board[row])) {
                return false;
            }
            char[] cols = new char[9];
            for(int col = 0; col < 9; col++) {
                cols[col] = board[col][row];
                if((row+1) % 3 == 0 && (col+1) % 3 == 0) {
                    //construct a little grid 3*3
                    char[] items = new char[9];
                    for(int r = row; r > row-3; r--) {
                        for(int c = col; c > col -3; c--) {
                            items[(row-r)*3+(col-c)] = board[c][r];
                        }
                    }
                    if(!isValidItem(items)) {
                        return false;
                    }
                }
            }
            if(!isValidItem(cols)) {
                return false;
            }
        }
        return true;
    }

    /***
     * 
     * @param items -- 1*9 
     * @return
     */
    public boolean isValidItem(char[] items) {
        boolean[] isValid = new boolean[9];
        for(int i = 0; i < 9; i++) {
            if(items[i] == '.') continue;
            int val = items[i] - '0';
            isValid[val] = !isValid[val];
            if(!isValid[val]) return false;
        }
        return true;
    }


    //Record which number exists on each row
    private boolean[][] rowsExit = new boolean[9][9];

    //Record which number exists on each column
    private boolean[][] colsExit = new boolean[9][9];

    //Record which number exists on each grid
    private boolean[][] gridExit = new boolean[9][9];


    public boolean isValidSudoku2(char[][] board) {
        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
                if(board[row][col] != '.') {
                    int num = board[row][col]-'0';
                    rowsExit[row][num-1] = !rowsExit[row][num-1];
                    colsExit[col][num-1] = !colsExit[col][num-1];
                    int grid = 3*(row/3)+(col/3);
                    gridExit[grid][num-1] = !gridExit[grid][num-1];
                    if(!rowsExit[row][num-1] || !colsExit[col][num-1] || !gridExit[grid][num-1]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
