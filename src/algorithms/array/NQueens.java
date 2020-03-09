package algorithms.array;

import java.util.ArrayList;
import java.util.List;

/***
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



    Given an integer n, return all distinct solutions to the n-queens puzzle.
    
    Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
    
    Example:
    
    Input: 4
    Output: [
     [".Q..",  // Solution 1
      "...Q",
      "Q...",
      "..Q."],
    
     ["..Q.",  // Solution 2
      "Q...",
      "...Q",
      ".Q.."]
    ]
    Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 * @author miche
 *
 */
public class NQueens {

    private List<List<String>> lret;
    private boolean[] rowsExist;
    private boolean[] colsExist;
    private boolean[] sumExist;
    private boolean[] diffExist;
    private int n;
    private int count = 0;

    public NQueens() {
        // TODO Auto-generated constructor stub
    }

    public List<List<String>> solveNQueens(int m) {
        this.n = m;
        lret = new ArrayList<List<String>>();
        for(int i = 0; i < n; i++) {
            this.rowsExist = new boolean[n];
            this.colsExist = new boolean[n];
            this.sumExist = new boolean[n*2-1];
            this.diffExist = new boolean[n*2-1];
            char[][] chessSet = initChessSet();
            putQueen(chessSet, 0, i);
            trackBack(chessSet,n);
        }
        
        return lret;
    }

    public char[][] initChessSet() {
        char[][] chessSet = new char[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                chessSet[i][j] = '.';
            }
        }
        return chessSet;
    }

    public boolean isValid(int row, int col) {
        //The diagonal of the queen cannot have other pieces
        //The sum or difference of the coordinates is equal to the same diagonal.
        int sum = row + col;
        int diff = row - col;
        if(rowsExist[row] || colsExist[col] || sumExist[sum] || diffExist[diff+n-1]) {
            return false;
        }
        return true;
    }

    public void putQueen(char[][] chessSet, int row, int col) {
        int sum = row + col;
        int diff = row - col;
        rowsExist[row] = true;
        colsExist[col] = true;
        sumExist[sum] = true;
        diffExist[diff+n-1] = true;
        chessSet[row][col] = 'Q';
    }

    public void removeQueen(char[][] chessSet, int row, int col) {
        int sum = row + col;
        int diff = row - col;
        rowsExist[row] = false;
        colsExist[col] = false;
        sumExist[sum] = false;
        diffExist[diff+n-1] = false;
        chessSet[row][col] = '.';
    }

    
    ///Consider the chess set as n*n small cells from 0 to n*n-1
    public void trackBack(char[][] chessSet, int m) {
        if(m == n*n) {
            List<String> lrow = new ArrayList<String>();
            for(int i = 0; i < n; i++) {
                String str = new String(chessSet[i]);
                if(str.indexOf('Q') >= 0) {
                    lrow.add(str);
                }
            }
            if(lrow.size() == n) {
                lret.add(lrow);
                count++;
                return;
            } else {
                return;
            }
        }

        int row = m / n;
        int col = m % n;
        if(isValid(row, col)) {
            putQueen(chessSet, row, col);
            trackBack(chessSet, m+1);
            removeQueen(chessSet, row, col);
        }
        trackBack(chessSet, m+1);
    }

    public int totalNQueens(int n) {
        solveNQueens(n);
        return count;
    }

    public static void main(String[] argv) {
        int m = 4;
        NQueens nq = new NQueens();
        List<List<String>> ans = nq.solveNQueens(m);
        System.out.println(ans.toString());
    }
}
