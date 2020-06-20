package algorithms.array;

/***
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

    Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.
    
    The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
    
     
    
    Example 1:
    
    Input 1: a maze represented by a 2D array
    
    0 0 1 0 0
    0 0 0 0 0
    0 0 0 1 0
    1 1 0 1 1
    0 0 0 0 0
    
    Input 2: start coordinate (rowStart, colStart) = (0, 4)
    Input 3: destination coordinate (rowDest, colDest) = (4, 4)
    
    Output: true
    
    Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
    
    Example 2:
    
    Input 1: a maze represented by a 2D array
    
    0 0 1 0 0
    0 0 0 0 0
    0 0 0 1 0
    1 1 0 1 1
    0 0 0 0 0
    
    Input 2: start coordinate (rowStart, colStart) = (0, 4)
    Input 3: destination coordinate (rowDest, colDest) = (3, 2)
    
    Output: false
    
    Explanation: There is no way for the ball to stop at the destination.
    
     
    
    Note:
    
    There is only one ball and one destination in the maze.
    Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
    The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
    The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 * @author miche
 *
 */
public class TheMaze {

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
            
        return dfs(maze, start, destination, visited);
    }

    public boolean dfs(int[][] maze, int[] start, int[] destination, boolean[][] visited) {
        int row = start[0], col = start[1];
        if(row < 0 || row >= maze.length || col < 0 || col >= maze[0].length) {
            return false;
        }
        if(maze[row][col] == 1 || visited[row][col]) {
            return false;
        }
        if(row == destination[0] && col == destination[1]) {
            //at the destination
            return true;
        }
        
        visited[row][col] = true;
        int l = col - 1, r = col + 1, u = row - 1, d = row + 1;
        //left
        while(l >= 0 && maze[row][l] == 0) {
            l--;
        }
        int[] nextl = {row, l+1};
        if(dfs(maze, nextl, destination, visited)) {
            return true;
        }
        //right
        while(r < maze[0].length && maze[row][r] == 0) {
            r++;
        }
        int[] nextr = {row, r-1};
        if(dfs(maze, nextr, destination, visited)) {
            return true;
        }
        //up
        while(u >= 0 && maze[u][col] == 0) {
            u--;
        }
        int[] nextu = {u+1, col};
        if(dfs(maze, nextu, destination, visited)) {
            return true;
        }
        //down
        while(d < maze.length && maze[d][col] == 0) {
            d++;
        }
        int[] nextd = {d-1, col};
        return dfs(maze, nextd, destination, visited);
    }
}
