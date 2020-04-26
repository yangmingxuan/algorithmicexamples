package algorithms.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

    Example 1:
    
    Input: n = 12
    Output: 3 
    Explanation: 12 = 4 + 4 + 4.
    Example 2:
    
    Input: n = 13
    Output: 2
    Explanation: 13 = 4 + 9.
 * @author miche
 *
 */
public class PerfectSquares {

    public int numSquares(int n) {
        if(n <= 3) return n;
        int root = (int)Math.sqrt(n);
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= root; j++) {
                int perfectSquare = j * j;
                if(i >= perfectSquare) {
                    dp[i] = Math.min(dp[i], 1+dp[i-perfectSquare]);
                } else {
                    break;
                }
            }
        }
        return dp[n];
    }

    /***
     * theorem on the sum of four squares or Lagrange quadratic sum theorem(拉格朗日四平方数和定理)
     * @param n
     * @return
     */
    public int numSquares2(int n) {
        while(n % 4 == 0) {
            n /= 4;
        }
        if(n % 8 == 7) {
            return 4;
        }
        if(isPerfectSquare(n)) {
            return 1;
        }
        for(int i = 1; i*i < n; i++) {
            if(isPerfectSquare(n - i*i)) {
                return 2;
            }
        }
        return 3;
    }

    public boolean isPerfectSquare(int n) {
        double  x = Math.sqrt(n);
        return (x - Math.floor(x)) == 0;
    }
}
