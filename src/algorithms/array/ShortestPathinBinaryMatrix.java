package algorithms.array;

import java.util.LinkedList;
import java.util.Queue;

/***
 * In an N by N square grid, each cell is either empty (0) or blocked (1).

    A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:
    
    Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
    C_1 is at location (0, 0) (ie. has value grid[0][0])
    C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
    If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
    Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.
    
     
    
    Example 1:
    
    Input: [[0,1],[1,0]]
    
    
    Output: 2
    
    Example 2:
    
    Input: [[0,0,0],[1,1,0],[1,1,0]]
    
    
    Output: 4
    
     
    
    Note:
    
    1 <= grid.length == grid[0].length <= 100
    grid[r][c] is 0 or 1
 * @author miche
 *
 */
public class ShortestPathinBinaryMatrix {

    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid == null || grid.length == 0) return -1;
        int N = grid.length;
        if(grid[0][0] != 0 || grid[N-1][N-1] != 0) return -1;
        int ans = 1;
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}, {1,1},{1,-1},{-1,1},{-1,-1}};
        boolean[][] visited = new boolean[N][N];
        visited[0][0] = true;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(0); // 0*N+0 = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int coor = queue.poll();
                int r = coor / N;
                int c = coor % N;
                if(r == N-1 && c == N-1) {
                    return ans;
                }
                for(int[]dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || grid[nr][nc] != 0) {
                        continue;
                    }
                    visited[nr][nc] = true;
                    queue.offer(nr*N+nc);
                }
            }
            ans++;
        }
        return -1;
    }

}
