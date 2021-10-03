package algorithms.array;
/***
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.

 

Example 1:


Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]
Example 2:

Input: n = 1
Output: [[1]]
 

Constraints:

1 <= n <= 20
 * @author miche
 *
 */
public class SpiralMatrixII {

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int count = 1;
        int rowTop = 0, rowBottom = n-1, row;
        int colLeft = 0, colRight = n-1, col;
        while(rowTop <= rowBottom && colLeft <= colRight) {
            for(col = colLeft; col <= colRight; col++) {
                matrix[rowTop][col] = count++;
            }
            for(row = rowTop+1; row <= rowBottom; row++) {
                matrix[row][colRight] = count++;
            }
            if(rowTop < rowBottom && colLeft < colRight) {
                for(col = colRight-1; col >= colLeft; col--) {
                    matrix[rowBottom][col] = count++;
                }
                for(row = rowBottom-1; row > rowTop; row--) {
                    matrix[row][colLeft] = count++;
                }
            }
            rowTop++;
            rowBottom--;
            colLeft++;
            colRight--;
        }
        return  matrix;
    }

}
