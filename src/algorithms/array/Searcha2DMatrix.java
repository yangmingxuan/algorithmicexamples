package algorithms.array;

/***
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

    Integers in each row are sorted from left to right.
    The first integer of each row is greater than the last integer of the previous row.
    Example 1:
    
    Input:
    matrix = [
      [1,   3,  5,  7],
      [10, 11, 16, 20],
      [23, 30, 34, 50]
    ]
    target = 3
    Output: true
    Example 2:
    
    Input:
    matrix = [
      [1,   3,  5,  7],
      [10, 11, 16, 20],
      [23, 30, 34, 50]
    ]
    target = 13
    Output: false
 * @author miche
 *
 */
public class Searcha2DMatrix {

    /**
     * treat the 2d matrix as a 1d array
     * O(log(m*n))
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int l = 0, r = m*n-1;
        while(l <= r) {
            int mid = (l+r) / 2;
            int midElement = matrix[mid/n][mid%n];
            if (midElement == target) {
                return true;
            } else if (midElement < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        
        return false;
    }

    /**
     * first seek the row , then find the target
     * O(log(m) + log(n) = log(m*n))
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int row = searchRow(matrix, target);
        if(row == -1) return false;
        return searchCol(matrix, target, row);
    }

    public int searchRow(int[][] matrix, int target) {
        int l = 0, r = matrix.length - 1, n = matrix[0].length;
        while(l <= r) {
            int mid = (l+r) / 2;
            if(matrix[mid][0] <= target && matrix[mid][n-1] >= target) {
                return mid;
            } else if(matrix[mid][0] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }
    
    public boolean searchCol(int[][] matrix, int target, int row) {
        int n = matrix[0].length, l = 0, r = n - 1;
        while(l <= r) {
            int mid = (l+r) / 2;
            if(matrix[row][mid] == target) {
                return true;
            } else if(matrix[row][mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }
}
