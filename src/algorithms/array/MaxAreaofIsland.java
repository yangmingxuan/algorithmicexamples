package algorithms.array;

/***
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

    Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
    
    Example 1:
    
    [[0,0,1,0,0,0,0,1,0,0,0,0,0],
     [0,0,0,0,0,0,0,1,1,1,0,0,0],
     [0,1,1,0,1,0,0,0,0,0,0,0,0],
     [0,1,0,0,1,1,0,0,1,0,1,0,0],
     [0,1,0,0,1,1,0,0,1,1,1,0,0],
     [0,0,0,0,0,0,0,0,0,0,1,0,0],
     [0,0,0,0,0,0,0,1,1,1,0,0,0],
     [0,0,0,0,0,0,0,1,1,0,0,0,0]]
    Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
    Example 2:
    
    [[0,0,0,0,0,0,0,0]]
    Given the above grid, return 0.
    Note: The length of each dimension in the given grid does not exceed 50.
 * @author miche
 *
 */
public class MaxAreaofIsland {

    int[][] grid;
    boolean[][] visited;
    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        this.grid = grid;
        this.visited = new boolean[grid.length][grid[0].length];
        int maxarea = 0;
        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                if(!visited[row][col] && grid[row][col] == 1) {
                    maxarea = Math.max(maxarea, area(row, col));
                }
            }
        }
        return maxarea;
    }

    public int area(int row, int col) {
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return 0;
        }
        if(visited[row][col] || grid[row][col] == 0) {
            return 0;
        }
        visited[row][col] = true;
        return (1 + area(row-1, col)+area(row+1,col)+area(row,col-1)+area(row,col+1));
    }
}
