package algorithms.array;

import java.util.HashSet;

/***
 * You are given an m x n integer array grid where grid[i][j] could be:

    1 representing the starting square. There is exactly one starting square.
    2 representing the ending square. There is exactly one ending square.
    0 representing empty squares we can walk over.
    -1 representing obstacles that we cannot walk over.
    Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
    
     
    
    Example 1:
    
    
    Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
    Output: 2
    Explanation: We have the following two paths: 
    1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
    2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
    Example 2:
    
    
    Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
    Output: 4
    Explanation: We have the following four paths: 
    1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
    2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
    3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
    4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
    Example 3:
    
    
    Input: grid = [[0,1],[2,0]]
    Output: 0
    Explanation: There is no path that walks over every empty square exactly once.
    Note that the starting and ending square can be anywhere in the grid.
     
    
    Constraints:
    
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 20
    1 <= m * n <= 20
    -1 <= grid[i][j] <= 2
    There is exactly one starting cell and one ending cell.
 * @author miche
 *
 */
public class UniquePathsIII {

    int ans = 0;
    HashSet<Integer> positions;
    int m,n;
    public int uniquePathsIII(int[][] grid) {
        positions = new HashSet<Integer>();
        m = grid.length;
        n = grid[0].length;
        int start = 0, end = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int pos = j + i * n*n;
                if(grid[i][j] == 1) {
                    start = pos;
                } else if(grid[i][j] == 2) {
                    end = pos;
                }
                if(grid[i][j] != -1) {
                    //not obstacles
                    positions.add(pos); //save the all position except obstacles
                }
            }
        }
        
        //remove the start
        positions.remove(start);
        
        backtrack(start, end);
        
        return ans;
    }

    private void backtrack(int pos, int end) {
        if(pos == end) {
            if(positions.isEmpty()) {
                // walk over every position
                ans += 1;
            }
            return;
        }
        int[] directions = {1,-1, n*n, -n*n};
        for(int i = 0; i < 4; i++) {
            int newpos = pos + directions[i];
            if(!positions.contains(newpos)) {
                //no-exist or visited
                continue;
            }
            positions.remove(newpos);
            backtrack(newpos, end);
            positions.add(newpos);
        }
    }
}
