package algorithms.array;

import java.util.HashMap;

/***
 * Given an integer matrix, find the length of the longest increasing path.

    From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
    
    Example 1:
    
    Input: nums = 
    [
      [9,9,4],
      [6,6,8],
      [2,1,1]
    ] 
    Output: 4 
    Explanation: The longest increasing path is [1, 2, 6, 9].
    Example 2:
    
    Input: nums = 
    [
      [3,4,5],
      [3,2,6],
      [2,2,1]
    ] 
    Output: 4 
    Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * @author miche
 *
 */
public class LongestIncreasingPathinaMatrix {

    //use HashMap
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int longestPath = 0;
        HashMap<Integer, Integer> posLongestPath = new HashMap<Integer, Integer>();
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                longestPath = Math.max(longestPath, longestPath(matrix, posLongestPath, i, j));
            }
        }
        
        return longestPath;
    }

    public int longestPath(int[][] matrix, HashMap<Integer, Integer> posLongestPath, int row, int col) {
        //if(row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
        //    return 0;
        //}
        int position = row * matrix[0].length + col;
        if(posLongestPath.containsKey(position)) {
            return posLongestPath.get(position);
        }
        int newrow, newcol,maxpath = 0;
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
        for(int[] direction : directions) {
            newrow = row + direction[0];
            newcol = col + direction[1];
            if(newrow >= 0 && newrow < matrix.length && newcol >= 0 && newcol < matrix[0].length
                    && matrix[newrow][newcol] > matrix[row][col]) {
                maxpath = Math.max(maxpath, longestPath(matrix, posLongestPath, newrow, newcol));
            }
        }
        maxpath++; //include this position
        posLongestPath.put(position, maxpath);
        return maxpath;
    }


    //use int[][]
    public int longestIncreasingPath2(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int longestPath = 0;
        int[][] posLongestPath = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                longestPath = Math.max(longestPath, longestPath2(matrix, posLongestPath, i, j));
            }
        }
        
        return longestPath;
    }

    public int longestPath2(int[][] matrix, int[][] posLongestPath, int row, int col) {
        //if(row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
        //    return 0;
        //}
        if(posLongestPath[row][col] > 0) {
            return posLongestPath[row][col];
        }
        int newrow, newcol,maxpath = 0;
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
        for(int[] direction : directions) {
            newrow = row + direction[0];
            newcol = col + direction[1];
            if(newrow >= 0 && newrow < matrix.length && newcol >= 0 && newcol < matrix[0].length
                    && matrix[newrow][newcol] > matrix[row][col]) {
                maxpath = Math.max(maxpath, longestPath2(matrix, posLongestPath, newrow, newcol));
            }
        }
        maxpath++; //include this position
        posLongestPath[row][col] = maxpath;
        return maxpath;
    }
}
