package algorithms.array;

import java.util.LinkedList;
import java.util.Queue;

/***
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

    Each 0 marks an empty land which you can pass by freely.
    Each 1 marks a building which you cannot pass through.
    Each 2 marks an obstacle which you cannot pass through.
    Example:
    
    Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
    
    1 - 0 - 2 - 0 - 1
    |   |   |   |   |
    0 - 0 - 0 - 0 - 0
    |   |   |   |   |
    0 - 0 - 1 - 0 - 0
    
    Output: 7 
    
    Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
                 the point (1,2) is an ideal empty land to build a house, as the total 
                 travel distance of 3+3+1=7 is minimal. So return 7.
    Note:
    There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 * @author miche
 *
 */
public class ShortestDistancefromAllBuildings {

    int m, n;
    int[][] distance;
    int[][] count;
    public int shortestDistance(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return -1;
        m = grid.length;
        n = grid[0].length;
        distance = new int[m][n];//record the sum of distance between the point and each building
        count = new int[m][n]; //record the number of the buildings that can be reached at this point
        int bulidCount = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    //calculate the distance from this building to each point
                    calculate(grid, i, j);
                    bulidCount++;
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(count[i][j] == bulidCount && grid[i][j] == 0) {
                    min = Math.min(min, distance[i][j]);
                }
            }
        }
        
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public void calculate(int[][] grid, int i, int j) {
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[] {i, j});
        visited[i][j] = true;
        int dist = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int[] node = queue.poll();
                distance[node[0]][node[1]] += dist;
                count[node[0]][node[1]] ++;
                for(int[] dir : dirs) {
                    int x = node[0] + dir[0];
                    int y = node[1] + dir[1];
                    if(x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || grid[x][y] != 0) {
                        continue;
                    }
                    visited[x][y] = true;
                    queue.offer(new int[] {x, y});
                }
            }
            dist++;
        }
    }
}
