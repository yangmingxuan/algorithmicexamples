package algorithms.array;

/***
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

    The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
    
    How many possible unique paths are there?
    
    
    Above is a 7 x 3 grid. How many possible unique paths are there?
    
     
    
    Example 1:
    
    Input: m = 3, n = 2
    Output: 3
    Explanation:
    From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
    1. Right -> Right -> Down
    2. Right -> Down -> Right
    3. Down -> Right -> Right
    Example 2:
    
    Input: m = 7, n = 3
    Output: 28
     
    
    Constraints:
    
    1 <= m, n <= 100
    It's guaranteed that the answer will be less than or equal to 2 * 10 ^ 9.
 * @author miche
 *
 */
public class UniquePaths {

    public UniquePaths() {
        // TODO Auto-generated constructor stub
    }
 
    public int uniquePaths(int m, int n) {
        int[][] ans = new int[n][m];
        for(int i = 0; i < m; i++) {
            ans[0][i] = 1;
        }
        for(int i = 0; i < n; i++) {
            ans[i][0] = 1;
        }
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                ans[i][j] = ans[i-1][j] + ans[i][j-1];
            }
        }
        /* debug
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                System.out.print(ans[i][j] + "\t");
            }
            System.out.println();
        }
        */
        return ans[n-1][m-1];
    }

    public static void main(String argv[]) {
        int m = 7, n = 3;
        UniquePaths up = new UniquePaths();
        up.uniquePaths(m, n);
    }
}
