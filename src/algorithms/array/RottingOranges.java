package algorithms.array;

import java.util.LinkedList;
import java.util.Queue;

import javafx.util.Pair;

/***
 * In a given grid, each cell can have one of three values:

    the value 0 representing an empty cell;
    the value 1 representing a fresh orange;
    the value 2 representing a rotten orange.
    Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
    
    Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
    
    Example 1:
    Input: [[2,1,1],[1,1,0],[0,1,1]]
    [[2,1,1],[1,1,0],[0,1,1]]-->[[2,2,1],[2,1,0],[0,1,1]]-->[[2,2,2],[2,2,0],[0,1,1]]
    -->[[2,2,2],[2,2,0],[0,2,1]]-->[[2,2,2],[2,2,0],[0,2,2]]
    Output: 4
    Example 2:
    
    Input: [[2,1,1],[0,1,1],[1,0,1]]
    Output: -1
    Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
    Example 3:
    
    Input: [[0,2]]
    Output: 0
    Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 * @author miche
 *
 */
public class RottingOranges {

    public int orangesRotting(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        Queue<Pair<Integer, Integer>> rotten = new LinkedList<Pair<Integer, Integer>>();
        int freshCount = 0, rows = grid.length, cols = grid[0].length, step = 0;
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                if(grid[row][col] == 1) {
                    freshCount++;
                } else if(grid[row][col] == 2) {
                    rotten.offer(new Pair<Integer, Integer>(row, col));
                }
            }
        }
        if(freshCount == 0) return 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while(!rotten.isEmpty()) {
            int size = rotten.size();
            for(int i = 0; i < size; i++) {
                Pair<Integer, Integer> cell = rotten.poll();
                int row = cell.getKey(), col = cell.getValue();
                for(int[] direct : directions) {
                    int nRow = row+direct[0], nCol = col+direct[1];
                    if(nRow >= 0 && nRow < rows && nCol >= 0 && nCol < cols) {
                        if(grid[nRow][nCol] == 1) {
                            grid[nRow][nCol] = 2;
                            freshCount--;
                            rotten.offer(new Pair<Integer, Integer>(nRow, nCol));
                        }
                    }
                }
            }
            step++;
        }

        return freshCount == 0 ? step-1 : -1;
    }

}
