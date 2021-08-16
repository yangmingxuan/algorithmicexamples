package algorithms.string;
/***
 * Given a string s, partition s such that every substring of the partition is a palindrome.

    Return the minimum cuts needed for a palindrome partitioning of s.
    
     
    
    Example 1:
    
    Input: s = "aab"
    Output: 1
    Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
    Example 2:
    
    Input: s = "a"
    Output: 0
    Example 3:
    
    Input: s = "ab"
    Output: 1
     
    
    Constraints:
    
    1 <= s.length <= 2000
    s consists of lower-case English letters only.
 * @author miche
 *
 */
public class PalindromePartitioningII {

    public int minCut(String s) {
        char[] stoc = s.toCharArray();
        int n = s.length();
        int[] dp = new int[n]; //dp[i] indicates that the length of s is i+1, it needs to be cut the minimum times to be divided into palindrome parts.
        for(int i = 0; i < n; i++) {
            dp[i] = i;
        }
        
        for(int i = 0; i < n; i++) {
            solve(stoc, dp, i, i); //form like "a", "aba", "cabac"
            solve(stoc, dp, i, i+1); //form like "aa", "baab", "cbaabc"
        }
        
        return dp[n-1];
    }

    public void solve(char[] stoc, int[] dp, int l, int r) {
        int n = stoc.length;
        while(l >= 0 && r < n && stoc[l] == stoc[r]) {
            if(l == 0) {
                dp[r] = 0; //from 0 to r is Palindrome String
            } else {
                dp[r] = Math.min(dp[l-1] + 1, dp[r]); //from l to r is Palindrome String, so the value is dp[l-1]+1
            }
            l--;
            r++;
        }
    }
}
