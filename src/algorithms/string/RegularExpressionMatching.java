package algorithms.string;

/***
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

    '.' Matches any single character.
    '*' Matches zero or more of the preceding element.
    The matching should cover the entire input string (not partial).
    
    Note:
    
    s could be empty and contains only lowercase letters a-z.
    p could be empty and contains only lowercase letters a-z, and characters like . or *.
    
    Example 1:
    Input:
    s = "aa"
    p = "a"
    Output: false
    Explanation: "a" does not match the entire string "aa".
    
    Example 2:
    Input:
    s = "aa"
    p = "a*"
    Output: true
    Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
    
    Example 3:
    Input:
    s = "ab"
    p = ".*"
    Output: true
    Explanation: ".*" means "zero or more (*) of any character (.)".
    
    Example 4:
    Input:
    s = "aab"
    p = "c*a*b"
    Output: true
    Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
    
    Example 5:
    Input:
    s = "mississippi"
    p = "mis*is*p*."
    Output: false
 
 * @author miche
 *
 * Analysis: if first c match, then compare the other string; * is special, it can be 0 times 
 */
public class RegularExpressionMatching {

    public RegularExpressionMatching() {
        // TODO Auto-generated constructor stub
    }

    /***
     * recursive :
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        if(p.isEmpty()) return s.isEmpty();
        boolean firstmatch = (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'));
        //from example 4 and 5
        if(p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch2(s, p.substring(2)) || (firstmatch && isMatch2(s.substring(1), p));
        } else {
            return firstmatch && isMatch2(s.substring(1), p.substring(1));
        }
    }

    /***
     * Dynamic Programming
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        //"empty string" and "empty regular expression" match
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && i > 0) {
                //'*' Matches zero or more of the preceding element.
                //When the empty string encounters *, the match depends on the preceding element
                dp[0][i+1] = dp[0][i-1];
            }
        }

        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j < p.length(); j++) {
                if(p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if(p.charAt(j) == '*' && j > 0) {
                    dp[i+1][j+1] = dp[i+1][j-1] || ((s.charAt(i) == p.charAt(j-1) || p.charAt(j-1) == '.') && dp[i][j+1]);
                    //dp[i+1][j+1] = dp[i+1][j-1] || ((s.charAt(i)==p.charAt(j-1) || p.charAt(j-1)=='.') && dp[i][j-1]) || ((s.charAt(i)==p.charAt(j-1) || p.charAt(j-1)=='.') && dp[i-1][j]);
                }
            }
        }

        return dp[s.length()][p.length()];
    }
}
