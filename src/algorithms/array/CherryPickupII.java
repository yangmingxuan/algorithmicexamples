package algorithms.array;

/***
 * You are given a rows x cols matrix grid representing a field of cherries where grid[i][j] represents the number of cherries that you can collect from the (i, j) cell.

    You have two robots that can collect cherries for you:
    
    Robot #1 is located at the top-left corner (0, 0), and
    Robot #2 is located at the top-right corner (0, cols - 1).
    Return the maximum number of cherries collection using both robots by following the rules below:
    
    From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
    When any robot passes through a cell, It picks up all cherries, and the cell becomes an empty cell.
    When both robots stay in the same cell, only one takes the cherries.
    Both robots cannot move outside of the grid at any moment.
    Both robots should reach the bottom row in grid.
     
    
    Example 1:
    
    
    Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
    Output: 24
    Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
    Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
    Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
    Total of cherries: 12 + 12 = 24.
    Example 2:
    
    
    Input: grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
    Output: 28
    Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
    Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
    Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
    Total of cherries: 17 + 11 = 28.
     
    
    Constraints:
    
    rows == grid.length
    cols == grid[i].length
    2 <= rows, cols <= 70
    0 <= grid[i][j] <= 100
 * @author michel
 *
 */
public class CherryPickupII {

    public int cherryPickup(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        Integer[][][] DP = new Integer[rows][cols][cols];
        //robot1 start from (0,0), robot2 start from (0,cols-1)
        return dp(0, 0, cols-1, grid, DP);
    }

    private int dp(int row, int col1, int col2, int[][] grid, Integer[][][] DP) {
        int result = 0;
        if(col1 < 0 || col1 >= grid[0].length || col2 < 0 || col2 >= grid[0].length) {
            return 0;
        }
        if(DP[row][col1][col2] != null) return DP[row][col1][col2];
        
        result += grid[row][col1];
        //When both robots stay in the same cell, only one takes the cherries.
        if(col1 != col2) {
            result += grid[row][col2];
        }
        
        if(row < grid.length-1) {
            int maxnum = 0;
            //From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
            for(int newcol1 = col1-1; newcol1 <= col1+1; newcol1++) {
                for(int newcol2 = col2-1; newcol2 <= col2+1; newcol2++) {
                    maxnum = Math.max(maxnum, dp(row+1, newcol1, newcol2, grid, DP));
                }
            }
            result += maxnum;
        }
        
        DP[row][col1][col2] = result;
        return result;
    }


    public int cherryPickup2(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[][][] dp = new int[rows][cols][cols];
        
        for(int row = rows-1; row >= 0; row--) {
            for(int col1 = cols-1; col1 >= 0; col1--) {
                for(int col2 = cols-1; col2 >= 0; col2--) {
                    int result = grid[row][col1];
                    //When both robots stay in the same cell, only one takes the cherries.
                    if(col1 != col2) {
                        result += grid[row][col2];
                    }
                    
                    if(row < rows-1) {
                        int maxnum = 0;
                        for(int newcol1 = col1+1; newcol1 >= col1-1; newcol1--) {
                            for(int newcol2 = col2+1; newcol2 >= col2-1; newcol2--) {
                                if(newcol1 >= 0 && newcol1 < cols && newcol2 >= 0 && newcol2 < cols)
                                    maxnum = Math.max(maxnum, dp[row+1][newcol1][newcol2]);
                            }
                        }
                        result += maxnum;
                    }
                    
                    dp[row][col1][col2] = result;
                }
            }
        }
        
        return dp[0][0][cols-1];
    }
}
