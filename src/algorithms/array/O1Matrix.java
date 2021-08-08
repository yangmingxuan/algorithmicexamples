package algorithms.array;

import java.util.LinkedList;
import java.util.Queue;

/***
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

    The distance between two adjacent cells is 1.
    
     
    
    Example 1:
    
    
    Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
    Output: [[0,0,0],[0,1,0],[0,0,0]]
    Example 2:
    
    
    Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
    Output: [[0,0,0],[0,1,0],[1,2,1]]
     
    
    Constraints:
    
    m == mat.length
    n == mat[i].length
    1 <= m, n <= 104
    1 <= m * n <= 104
    mat[i][j] is either 0 or 1.
    There is at least one 0 in mat.
 * @author miche
 *
 */
public class O1Matrix {

    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        Queue<int[]> queue = new LinkedList<int[]>(); 
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(mat[i][j] != 0) {
                    mat[i][j] = -1;
                } else {
                    queue.offer(new int[] {i,j});
                }
            }
        }
        
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
        while(!queue.isEmpty()) {
            int[] rowcol = queue.poll();
            for(int[] direction : directions) {
                int newrow = rowcol[0] + direction[0];
                int newcol = rowcol[1] + direction[1];
                if(newrow >= m || newrow < 0 || newcol >= n || newcol < 0 || mat[newrow][newcol] != -1) {
                    continue;
                }
                mat[newrow][newcol] = mat[rowcol[0]][rowcol[1]] + 1;
                queue.offer(new int[] {newrow, newcol});
            }
        }
        return mat;
    }

}
