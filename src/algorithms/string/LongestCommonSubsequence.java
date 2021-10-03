package algorithms.string;
/***
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

    A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
    
    For example, "ace" is a subsequence of "abcde".
    A common subsequence of two strings is a subsequence that is common to both strings.
    
     
    
    Example 1:
    
    Input: text1 = "abcde", text2 = "ace" 
    Output: 3  
    Explanation: The longest common subsequence is "ace" and its length is 3.
    Example 2:
    
    Input: text1 = "abc", text2 = "abc"
    Output: 3
    Explanation: The longest common subsequence is "abc" and its length is 3.
    Example 3:
    
    Input: text1 = "abc", text2 = "def"
    Output: 0
    Explanation: There is no such common subsequence, so the result is 0.
     
    
    Constraints:
    
    1 <= text1.length, text2.length <= 1000
    text1 and text2 consist of only lowercase English characters.
 * @author miche
 *
 */
public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        /*
         * dp[i][j]，代表一个“状态”，它的含义是t1下标小于i的部分和t2下标小于j的部分的匹配状况
         * dp[i][j], which represents a "state", which means the match between the part with t1 subscript less than i and the part with t2 subscript less than j
         */
        int[][] dp = new int[len1+1][len2+1];
        for(int i = 1; i <= len1; i++) {
            for(int j = 1; j <= len2; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        return dp[len1][len2];
    }

}
