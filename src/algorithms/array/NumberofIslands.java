package algorithms.array;

/***
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

    Example 1:
    
    Input:
    11110
    11010
    11000
    00000
    
    Output: 1
    Example 2:
    
    Input:
    11000
    11000
    00100
    00011
    
    Output: 3
 * @author miche
 *
 */
public class NumberofIslands {

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int count = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                if(grid[row][col] == '1' && !visited[row][col]) {
                    dfs(grid, visited, row, col);
                    count++;
                }
            }
        }

        return count;
    }

    public void dfs(char[][] grid, boolean[][] visited, int row, int col) {
        if(grid[row][col] == '0' || visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        if(row < grid.length - 1) {
            //go down
            dfs(grid, visited, row+1, col);
        }
        if(row > 0) {
            //go up
            dfs(grid, visited, row-1, col);
        }
        if(col < grid[0].length - 1) {
            //go right
            dfs(grid, visited, row, col+1);
        }
        if(col > 0) {
            //go left
            dfs(grid, visited, row, col-1);
        }
    }
}
