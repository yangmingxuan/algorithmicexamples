package algorithms.array;

import java.util.HashSet;

/***
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

    Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.
    
    Example 1:
    11000
    11000
    00011
    00011
    Given the above grid map, return 1.
    Example 2:
    11011
    10000
    00001
    11011
    Given the above grid map, return 3.
    
    Notice that:
    11
    1
    and
     1
    11
    are considered different island shapes, because we do not consider reflection / rotation.
    Note: The length of each dimension in the given grid does not exceed 50.
 * @author miche
 *
 */
public class NumberofDistinctIslands {

    private int[][] grid;
    private boolean[][] visited;
    private HashSet<Integer> shape;
    public int numDistinctIslands(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        this.grid = grid;
        visited = new boolean[grid.length][grid[0].length];
        HashSet<HashSet<Integer>> distinctIslands = new HashSet<HashSet<Integer>>();
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                if(grid[r][c] == 1 && !visited[r][c]) {
                    shape = new HashSet<Integer>();
                    dfs(r, c, r, c);
                    if(!shape.isEmpty()) {
                        distinctIslands.add(shape);
                    }
                }
            }
        }
        return distinctIslands.size();
    }

    public void dfs(int r, int c, int r0, int c0) {
        if(grid[r][c] == 0 || visited[r][c]) {
            return;
        }
        visited[r][c] = true;
        int element = (r - r0) * grid[0].length * 10 + (c - c0);
        shape.add(element);
        if(r < grid.length - 1) {
            //go down
            dfs(r+1, c, r0, c0);
        }
        if(r > 0) {
            //go up
            dfs(r-1, c, r0, c0);
        }
        if(c < grid[0].length - 1) {
            //go right
            dfs(r, c+1, r0, c0);
        }
        if(c > 0) {
            //go left
            dfs(r, c-1, r0, c0);
        }
    }
}
