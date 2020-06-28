package algorithms.string;

import java.util.HashMap;

/***
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:

    'A' -> 1
    'B' -> 2
    ...
    'Z' -> 26
    Given a non-empty string containing only digits, determine the total number of ways to decode it.
    
    Example 1:
    
    Input: "12"
    Output: 2
    Explanation: It could be decoded as "AB" (1 2) or "L" (12).
    Example 2:
    
    Input: "226"
    Output: 3
    Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * @author miche
 *
 */
public class DecodeWays {

    public int numDecodings(String s) {
        if(s== null || s.isEmpty()) return 0;
        HashMap<Integer, Integer> memo = new HashMap<Integer, Integer>();
        int ans = recursive(s, 0, memo);
        return ans;
    }
    
    public int recursive(String s, int index, HashMap<Integer, Integer> memo) {
        if(index == s.length()) return 1;
        
        //if string start with '0' can not be decode
        if(s.charAt(index) == '0') {
            return 0;
        } else if(index == s.length() - 1) {
            return 1;
        }
        
        if(memo.containsKey(index)) {
            return memo.get(index);
        }

        int ans = recursive(s, index+1, memo);
        if(Integer.parseInt(s.substring(index, index+2)) <= 26) {
            ans += recursive(s, index+2, memo);
        }
        
        memo.put(index, ans);
        return ans;
    }
    
    public int numDecodings2(String s) {
        if(s== null || s.isEmpty()) return 0;
        int[] dp = new int[s.length()+1];
        dp[0] = 1;
        
        //if s.length = 1. dp[1] = 1 unless s.charAt(0) = '0';
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        
        for(int i = 2; i < dp.length; i++) {
            //if the char is not '0'
            if(s.charAt(i-1) != '0') {
                dp[i] += dp[i-1];
            }
            
            //Check if successful two digit decode is possible.
            int d = Integer.parseInt(s.substring(i-2, i));
            if(d >= 10 && d <= 26) {
                dp[i] += dp[i-2];
            }
        }
        
        return dp[s.length()];
    }
}
