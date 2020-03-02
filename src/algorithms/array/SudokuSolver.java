package algorithms.array;

/***
 * Write a program to solve a Sudoku puzzle by filling the empty cells.

    A sudoku solution must satisfy all of the following rules:
    
    Each of the digits 1-9 must occur exactly once in each row.
    Each of the digits 1-9 must occur exactly once in each column.
    Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
    Empty cells are indicated by the character '.'.
    
    
    A sudoku puzzle...
        {
         {'5','3','.','.','7','.','.','.','.'},
         {'6','.','.','1','9','5','.','.','.'},
         {'.','9','8','.','.','.','.','6','.'},
         {'8','.','.','.','6','.','.','.','3'},
         {'4','.','.','8','.','3','.','.','1'},
         {'7','.','.','.','2','.','.','.','6'},
         {'.','6','.','.','.','.','2','8','.'},
         {'.','.','.','4','1','9','.','.','5'},
         {'.','.','.','.','8','.','.','7','9'}
       }
    
    
    ...and its solution .

        [
          ["5","3","4","6","7","8","9","1","2"],
          ["6","7","2","1","9","5","3","4","8"],
          ["1","9","8","3","4","2","5","6","7"],
          ["8","5","9","7","6","1","4","2","3"],
          ["4","2","6","8","5","3","7","9","1"],
          ["7","1","3","9","2","4","8","5","6"],
          ["9","6","1","5","3","7","2","8","4"],
          ["2","8","7","4","1","9","6","3","5"],
          ["3","4","5","2","8","6","1","7","9"]
        ]

    Note:
    
    The given board contain only digits 1-9 and the character '.'.
    You may assume that the given Sudoku puzzle will have a single unique solution.
    The given board size is always 9x9.

 * @author miche
 *
 */
public class SudokuSolver {

    public SudokuSolver() {
        // TODO Auto-generated constructor stub
    }

    //Record which number exists on each row
    private boolean[][] rowsExit = new boolean[9][9];

    //Record which number exists on each column
    private boolean[][] colsExit = new boolean[9][9];

    //Record which number exists on each grid
    private boolean[][] gridExit = new boolean[9][9];

    public boolean isValid(int num, int row, int col) {
        int grid = 3*(row/3)+(col/3);
        if(rowsExit[row][num-1] || colsExit[col][num-1] || gridExit[grid][num-1]) {
            return false;
        }
        return true;
    }

    public void putNumber(char[][] board, int num, int row, int col) {
        rowsExit[row][num-1] = true;
        colsExit[col][num-1] = true;
        int grid = 3*(row/3)+(col/3);
        gridExit[grid][num-1] = true;
        board[row][col] = (char)(num + '0');
    }

    public void removeNumber(char[][] board, int num, int row, int col) {
        rowsExit[row][num-1] = false;
        colsExit[col][num-1] = false;
        int grid = 3*(row/3)+(col/3);
        gridExit[grid][num-1] = false;
        board[row][col] = '.';
    }

    public void solveSudoku(char[][] board) {
        //initial the exit
        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
                if(board[row][col] != '.') {
                    int num = board[row][col] - '0';
                    rowsExit[row][num-1] = true;
                    colsExit[col][num-1] = true;
                    int grid = 3*(row/3)+(col/3);
                    gridExit[grid][num-1] = true;
                }
            }
        }

        trackBack(board, 0);
    }

    //Consider the entire Sudoku as 81 small cells from 0 to 80
    public boolean trackBack(char[][] board, int n) {
        if(n == 81) return true;
        int row = n / 9;
        int col = n % 9;
        if(board[row][col] != '.') {
            //If a number already exists, fill in the next cell
            return trackBack(board, n+1);
        } else {
            for(int num = 1; num <= 9; num++) {
                if(isValid(num, row, col)) {
                    putNumber(board, num, row, col);
                    if(trackBack(board, n+1)) {
                        return true;
                    } else {
                        removeNumber(board, num, row, col);
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] argv) {
        char[][] board = 
                {
                 {'8','.','.','3','.','.','.','1','.'},
                 {'.','.','.','.','.','.','.','.','.'},
                 {'.','.','4','.','9','.','.','2','.'},
                 {'.','.','5','.','.','6','.','.','.'},
                 {'.','.','7','.','.','.','.','.','.'},
                 {'9','2','.','.','.','.','4','.','1'},
                 {'1','.','.','.','2','.','5','.','8'},
                 {'.','.','.','.','3','.','.','.','.'},
                 {'3','.','.','.','5','4','.','.','7'}
               };

        SudokuSolver sudoku = new SudokuSolver();
        sudoku.solveSudoku(board);
        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }
}
