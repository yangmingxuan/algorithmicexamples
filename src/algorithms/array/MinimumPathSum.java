package algorithms.array;

/***
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

    Note: You can only move either down or right at any point in time.
    
    Example:
    
    Input:
    [
      [1,3,1],
      [1,5,1],
      [4,2,1]
    ]
    Output: 7
    Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 * @author miche
 *
 */
public class MinimumPathSum {

    public MinimumPathSum() {
        // TODO Auto-generated constructor stub
    }

    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int rows = grid.length, cols = grid[0].length;
        for(int col = 1; col < cols; col++) {
            grid[0][col] += grid[0][col-1];
        }
        for(int row = 1; row < rows; row++) {
            grid[row][0] += grid[row-1][0];
        }
        for(int row = 1; row < rows; row++) {
            for(int col = 1; col < cols; col++) {
                grid[row][col] += Math.min(grid[row-1][col], grid[row][col-1]);
            }
        }

        return grid[rows-1][cols-1];
    }

    public static void main(String[] argv) {
        int[][] grid = {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        
        MinimumPathSum mps = new MinimumPathSum();
        mps.minPathSum(grid);

        int rows = grid.length, cols = grid[0].length;
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                System.out.print(grid[row][col] + "\t");
            }
            System.out.println();
        }

    }
}
