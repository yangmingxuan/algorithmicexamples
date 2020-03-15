package algorithms.array;

import java.util.ArrayList;
import java.util.List;

/***
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

    Example 1:
    
    Input:
    [
     [ 1, 2, 3 ],
     [ 4, 5, 6 ],
     [ 7, 8, 9 ]
    ]
    Output: [1,2,3,6,9,8,7,4,5]
    Example 2:
    
    Input:
    [
      [1, 2, 3, 4],
      [5, 6, 7, 8],
      [9,10,11,12]
    ]
    Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 * @author miche
 *
 */
public class SpiralMatrix {

    public SpiralMatrix() {
        // TODO Auto-generated constructor stub
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> lret = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0) {
            return lret;
        }
        int rowTop = 0, rowBotom = matrix.length-1, rowCur;
        int colLeft = 0, colRight = matrix[0].length-1, colCur;
        
        while(rowTop <= rowBotom && colLeft <= colRight) {
            for(colCur = colLeft; colCur <= colRight; colCur++) {
                lret.add(matrix[rowTop][colCur]);
            }
            for(rowCur = rowTop+1; rowCur <= rowBotom; rowCur++) {
                lret.add(matrix[rowCur][colRight]);
            }
            if(rowTop < rowBotom && colLeft < colRight) {
                for(colCur = colRight-1; colCur >= colLeft; colCur--) {
                    lret.add(matrix[rowBotom][colCur]);
                }
                for(rowCur = rowBotom -1; rowCur > rowTop; rowCur--) {
                    lret.add(matrix[rowCur][colLeft]);
                }
            }
            rowTop++;
            rowBotom--;
            colLeft++;
            colRight--;
        }

        return lret;
    }

    public static void main(String[] argv) {
        int[][] matrix = {
                {1, 2, 3, 4, 17},
                {5, 6, 7, 8, 18},
                {9,10,11,12, 19},
                {13,14,15,16,20},
                {21,22,23,24,25}
        };

        SpiralMatrix sm = new SpiralMatrix();
        List<Integer> lret = sm.spiralOrder(matrix);
        System.out.print(lret.toString());
    }
}
