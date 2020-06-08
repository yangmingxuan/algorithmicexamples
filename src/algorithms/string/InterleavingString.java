package algorithms.string;

/***
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

    Example 1:
    
    Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
    Output: true
    Example 2:
    
    Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
    Output: false
 * @author miche
 *
 */
public class InterleavingString {

    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.isEmpty() && s2.isEmpty() && s3.isEmpty()) return true;
        if(s3.length() != s1.length() + s2.length()) return false;
        boolean dp[][] = new boolean[s1.length()+1][s2.length()+1];
        dp[0][0] = true; //if all length is 0
        //suppose s2.length is 0
        for(int i = 1; i <= s1.length(); i++) {
            dp[i][0] = dp[i-1][0] && s1.charAt(i-1) == s3.charAt(i-1);
        }
        //suppose s1.length is 0
        for(int j = 1; j <= s2.length(); j++) {
            dp[0][j] = dp[0][j-1] && s2.charAt(j-1) == s3.charAt(j-1);
        }
        
        for(int i = 1; i <= s1.length(); i++) {
            for(int j = 1; j <= s2.length(); j++) {
                dp[i][j] = dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1)
                        || dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1);
            }
        }
        
        return dp[s1.length()][s2.length()];
    }

}
