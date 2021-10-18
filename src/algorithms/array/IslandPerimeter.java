package algorithms.array;
/***
 * You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.

    Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
    
    The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
    
     
    
    Example 1:
    
    
    Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
    Output: 16
    Explanation: The perimeter is the 16 yellow stripes in the image above.
    Example 2:
    
    Input: grid = [[1]]
    Output: 4
    Example 3:
    
    Input: grid = [[1,0]]
    Output: 4
     
    
    Constraints:
    
    row == grid.length
    col == grid[i].length
    1 <= row, col <= 100
    grid[i][j] is 0 or 1.
    There is exactly one island in grid.
 * @author miche
 *
 */
public class IslandPerimeter {

    public int islandPerimeter(int[][] grid) {
        int ans = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    ans += perimeter(grid, i, j);
                }
            }
        }
        return ans;
    }
    
    private int perimeter(int[][] grid, int row, int col) {
        int[] dirx = {0, 1, -1, 0};
        int[] diry = {1, 0, 0, -1};
        int pmt = 0;
        for(int i = 0; i < 4; i++) {
            int newrow = row + diry[i];
            int newcol = col + dirx[i];
            if(newrow < 0 || newcol < 0 || newrow >= grid.length || newcol >= grid[0].length || grid[newrow][newcol] == 0) {
                pmt += 1;
            }
        }
        return pmt;
    }

    public int islandPerimeter2(int[][] grid) {
        int ans = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    ans += 4;
                    
                    if(i > 0 && grid[i-1][j] == 1) {
                        ans -= 2;
                    }
                    if(j > 0 && grid[i][j-1] == 1) {
                        ans -= 2;
                    }
                }
            }
        }
        return ans;
    }
}
