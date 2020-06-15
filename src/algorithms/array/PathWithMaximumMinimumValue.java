package algorithms.array;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/****
 * Given a matrix of integers A with R rows and C columns, find the maximum score of a path starting at [0,0] and ending at [R-1,C-1].

    The score of a path is the minimum value in that path.  For example, the value of the path 8 →  4 →  5 →  9 is 4.
    
    A path moves some number of times from one visited cell to any neighbouring unvisited cell in one of the 4 cardinal directions (north, east, west, south).
    
     
    
    Example 1:
    5---4---5
            |
    1   2   6
            |
    7   4   6
    
    
    Input: [[5,4,5],[1,2,6],[7,4,6]]
    Output: 4
    Explanation: 
    The path with the maximum score is highlighted in yellow. 

    Example 2:
    2---2   1   2---2---2
        |       |       |
    1   2---2---2   1   2
    
    
    Input: [[2,2,1,2,2,2],[1,2,2,2,1,2]]
    Output: 2

    Example 3:
    3-->4-->6-->3-->4
                    |
    0   2   1   1   7
                    |
    8<--8<--3   2   7
    |       |       |
    3   2   4<--9<--8
    |
    4   1   2   0   0
    |
    4-->6-->5-->4-->3
    
    
    Input: [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
    Output: 3
     
    
    Note:
    
    1 <= R, C <= 100
    0 <= A[i][j] <= 10^9
 * @author miche
 *
 */
public class PathWithMaximumMinimumValue {

    public int maximumMinimumPath(int[][] A) {
        if(A == null || A.length == 0 || A[0].length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int m = A.length, n = A[0].length;
        
        PriorityQueue<Neighbor> queue = new PriorityQueue<Neighbor>((a,b)->A[b.row][b.col]-A[a.row][a.col]);
        Set<Integer> visited = new HashSet<Integer>();
        queue.offer(new Neighbor(A[0][0], A[0][0], 0, 0));
        
        int[] dx = {0,0,1,-1}, dy = {1,-1,0,0};
        
        while(!queue.isEmpty()) {
            Neighbor node = queue.poll();
            visited.add(node.row*n+node.col);
            
            if(node.row == m - 1 && node.col == n - 1 && node.sum > max) {
                max = node.sum;
                min = node.minVal;
                break;
            }
            
            for(int i = 0; i < 4; i++) {
                int nRow = node.row + dy[i];
                int nCol = node.col + dx[i];
                
                if(nRow >= 0 && nRow < m && nCol >= 0 && nCol < n && !visited.contains(nRow*n+nCol)) {
                    queue.offer(new Neighbor(Math.min(node.minVal, A[nRow][nCol]), node.sum+A[nRow][nCol], nRow, nCol));
                }
            }
        }
        
        return min;
    }

    class Neighbor {
        int minVal;
        int sum;
        int row;
        int col;
        
        Neighbor(int minVal, int sum, int row, int col) {
            this.minVal = minVal;
            this.sum = sum;
            this.row = row;
            this.col = col;
        }
    }
}
