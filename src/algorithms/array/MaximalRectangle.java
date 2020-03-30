package algorithms.array;

/***
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

    Example:
    
    Input:
    [
      ["1","0","1","0","0"],
      ["1","0","1","1","1"],
      ["1","1","1","1","1"],
      ["1","0","0","1","0"]
    ]
    Output: 6
 * @author miche
 *
 */
public class MaximalRectangle {

    /***
     * dp[row][col] represents the cur largest length at row , from 0 to col
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;
        int max = 0;
        int dp[][] = new int[matrix.length][matrix[0].length];
        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[0].length; col++) {
                if(matrix[row][col] == '1') {
                    if(col == 0) dp[row][col] = 1;
                    else dp[row][col] = dp[row][col-1] + 1;
                    int length = dp[row][col];
                    for(int k = row; k >= 0; k--) {
                        length = Math.min(length, dp[k][col]);
                        if(length > 0) {
                            int height = row - k + 1;
                            max = Math.max(max, length*height);
                        }
                    }
                }
            }
        }
        
        return max;
    }


    /***
     * Treat each row as a largestRectangleArea
     * @param matrix
     * @return
     */
    public int maximalRectangle2(char[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;
        int max = 0;
        int heights[] = new int[matrix[0].length];
        for(int col = 0; col < matrix[0].length; col++) {
            if(matrix[0][col] == '1') heights[col] = 1;
        }
        LargestRectangleinHistogram lrh = new LargestRectangleinHistogram();
        max = lrh.largestRectangleArea(heights);
        for(int row = 1; row < matrix.length; row++) {
            for(int col = 0; col < matrix[0].length; col++) {
                if(matrix[row][col] == '1') heights[col]++;
                else heights[col] = 0;
            }
            max = Math.max(max, lrh.largestRectangleArea(heights));
        }

        return max;
    }
}
