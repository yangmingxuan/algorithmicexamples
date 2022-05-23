package algorithms.sort;

import java.util.LinkedList;
import java.util.Queue;

/***
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

    A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
    
    Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
    
     
    
    Example 1:
    
    
    
    Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
    Output: 2
    Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
    This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
    Example 2:
    
    
    
    Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
    Output: 1
    Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
    Example 3:
    
    
    Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
    Output: 0
    Explanation: This route does not require any effort.
     
    
    Constraints:
    
    rows == heights.length
    columns == heights[i].length
    1 <= rows, columns <= 100
    1 <= heights[i][j] <= 10^6
 * @author miche
 *
 */
public class PathWithMinimumEffort {

    int[][] heights;
    int m, n;
    public int minimumEffortPath(int[][] heights) {
        this.heights = heights;
        this.m = heights.length;
        this.n = heights[0].length;
        
        int left = -1, right = 0;
        //find the max height
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                right = Math.max(right, heights[r][c]);
            }
        }
        
        //binary search the minimum effort
        while(left < right - 1) {
            int effort = (left+right) / 2;
            if(canReachEnd(effort)) {
                //find the smaller effort
                right = effort;
            } else {
                left = effort;
            }
        }
        
        return right;
    }

    //find if this effort can go to the end
    private boolean canReachEnd(int effort) {
        boolean[][] visited = new boolean[m][n];
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{0,0}); //the start cell
        visited[0][0] = true;
        while(!queue.isEmpty()) {
            int[] cell = queue.poll();
            if(cell[0] == m-1 && cell[1] == n-1) {
                //reach the end cell
                return true;
            }
            for(int[] dir : directions) {
                int row = cell[0] + dir[0];
                int col = cell[1] + dir[1];
                if(row < 0 || row >= m || col < 0 || col >= n || visited[row][col]) {
                    continue;
                }
                //cal the effort
                int ne = Math.abs(heights[row][col]-heights[cell[0]][cell[1]]);
                if(ne <= effort) {
                    visited[row][col] = true;
                    queue.offer(new int[]{row,col});
                }
            }
        }
        return false;
    }
}
