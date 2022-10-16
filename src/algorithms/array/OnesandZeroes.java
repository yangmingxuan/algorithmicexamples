package algorithms.array;
/***
 * You are given an array of binary strings strs and two integers m and n.

    Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
    
    A set x is a subset of a set y if all elements of x are also elements of y.
    
     
    
    Example 1:
    
    Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
    Output: 4
    Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
    Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
    {"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.
    Example 2:
    
    Input: strs = ["10","0","1"], m = 1, n = 1
    Output: 2
    Explanation: The largest subset is {"0", "1"}, so the answer is 2.
     
    
    Constraints:
    
    1 <= strs.length <= 600
    1 <= strs[i].length <= 100
    strs[i] consists only of digits '0' and '1'.
    1 <= m, n <= 100
 * @author miche
 *
 */
public class OnesandZeroes {

    private int[] ZeroCount;
    private int[] OneCount;
    private Integer[][][] dp;
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        ZeroCount = new int[len];
        OneCount = new int[len];
        dp = new Integer[len][m+1][n+1];
        
        //count the zero and one
        for(int i = 0; i < len; i++) {
            for(char ch : strs[i].toCharArray()) {
                if(ch == '0') ZeroCount[i]++;
                else OneCount[i]++;
            }
        }
        
        return dfs(0, m, n);
    }

    private int dfs(int idx, int m, int n) {
        if(m < 0 || n < 0) return -600;
        if(idx >= OneCount.length) return 0;
        if(dp[idx][m][n] != null) return dp[idx][m][n];
        dp[idx][m][n] = Math.max(1+dfs(idx+1, m-ZeroCount[idx], n-OneCount[idx]), dfs(idx+1, m, n));
        return dp[idx][m][n];
    }
}