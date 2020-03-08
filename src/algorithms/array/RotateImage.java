package algorithms.array;

/***
 * You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

    Example 1:
    
    Given input matrix = 
    [
      [1,2,3],
      [4,5,6],
      [7,8,9]
    ],
    
    rotate the input matrix in-place such that it becomes:
    [
      [7,4,1],
      [8,5,2],
      [9,6,3]
    ]
    Example 2:
    
    Given input matrix =
    [
      [ 5, 1, 9,11],
      [ 2, 4, 8,10],
      [13, 3, 6, 7],
      [15,14,12,16]
    ], 
    
    rotate the input matrix in-place such that it becomes:
    [
      [15,13, 2, 5],
      [14, 3, 4, 1],
      [12, 6, 8, 9],
      [16, 7,10,11]
    ]
 * @author miche
 *
 */
public class RotateImage {

    public RotateImage() {
        // TODO Auto-generated constructor stub
    }
    public void rotate(int[][] matrix) {
        int n = matrix.length, tmp;
        for(int i = 0; i < (n+1)/2; i++) {
            for(int j = 0; j < (n+1)/2; j++) {
                if(n % 2 == 1 && j == (n-1) /2) continue;
                tmp = matrix[i][j];
                matrix[i][j] = matrix[n-1 - j][i];
                matrix[n-1 - j][i] = matrix[n-1 - i][n-1 - j];
                matrix[n-1 - i][n-1 - j] = matrix[j][n-1 - i];
                matrix[j][n-1 - i] = tmp;
            }
        }
    }

    public static void main(String[] argv) {
        int[][] matrix = {
                {1 , 2 , 3 , 4, 5},
                {6 , 7 , 8 , 9, 10},
                {11,12 ,13 ,14, 15},
                {16,17 ,18 ,19, 20},
                {21,22 ,23, 24, 25}
        };
        RotateImage ri = new RotateImage();
        ri.rotate(matrix);
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
