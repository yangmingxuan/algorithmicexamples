package algorithms.array;

import java.util.HashSet;

/***
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's, and return the matrix.

    You must do it in place.
    
     
    
    Example 1:
    
    
    Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
    Output: [[1,0,1],[0,0,0],[1,0,1]]
    Example 2:
    
    
    Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
    Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
     
    
    Constraints:
    
    m == matrix.length
    n == matrix[0].length
    1 <= m, n <= 200
    -231 <= matrix[i][j] <= 231 - 1
     
    
    Follow up:
    
    A straightforward solution using O(mn) space is probably a bad idea.
    A simple improvement uses O(m + n) space, but still not the best solution.
    Could you devise a constant space solution?
 * @author miche
 *
 */
public class SetMatrixZeroes {

    /***
     * space: O(m+n)
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        HashSet<Integer> rowSet = new HashSet<Integer>(),
                       colSet = new HashSet<Integer>();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == 0) {
                    rowSet.add(i);
                    colSet.add(j);
                }
            }
        }
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(rowSet.contains(i) || colSet.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /***
     * space: O(1)
     * @param matrix
     */
    public void setZeroes2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean firstColNeedToZero = false;
        for(int i = 0; i < m; i++) {
            if(matrix[i][0] == 0) {
                firstColNeedToZero = true;
            }
            for(int j = 1; j < n; j++) {
                if(matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        //matrix[j][0] to be processed at the end
        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 1; j--) {
                if(matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        if(firstColNeedToZero) {
            for(int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
