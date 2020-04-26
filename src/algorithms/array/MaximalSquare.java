package algorithms.array;

/***
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

    Example:
    
    Input: 
    
    1 0 1 0 0
    1 0 1 1 1
    1 1 1 1 1
    1 0 0 1 0
    
    Output: 4
 * @author miche
 *
 */
public class MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int rows = matrix.length, cols = matrix[0].length;
        int[][] dp = new int[rows+1][cols+1];
        int maxSquareLen = 0;
        for(int row = 1; row <= rows; row++) {
            for(int col = 1; col <= cols; col++) {
                if(matrix[row-1][col-1] == '1') {
                    dp[row][col] = 1 + Math.min(Math.min(dp[row-1][col], dp[row][col-1]), dp[row-1][col-1]);
                    maxSquareLen = Math.max(maxSquareLen, dp[row][col]);
                }
            }
        }
        return maxSquareLen*maxSquareLen;
    }

}
