package algorithms.string;

import java.util.Arrays;

/***
 * Given an array of strings strs, return the length of the longest uncommon subsequence between them. If the longest uncommon subsequence does not exist, return -1.

    An uncommon subsequence between an array of strings is a string that is a subsequence of one string but not the others.
    
    A subsequence of a string s is a string that can be obtained after deleting any number of characters from s.
    
    For example, "abc" is a subsequence of "aebdc" because you can delete the underlined characters in "aebdc" to get "abc". Other subsequences of "aebdc" include "aebdc", "aeb", and "" (empty string).
     
    
    Example 1:
    
    Input: strs = ["aba","cdc","eae"]
    Output: 3
    Example 2:
    
    Input: strs = ["aaa","aaa","aa"]
    Output: -1
     
    
    Constraints:
    
    1 <= strs.length <= 50
    1 <= strs[i].length <= 10
    strs[i] consists of lowercase English letters.
 * @author miche
 *
 */
public class LongestUncommonSubsequenceII {

    public int findLUSlength(String[] strs) {
        //Arrays.sort(strs, (a,b) -> b.length()-a.length());
        int ans = -1;
        for(int i = 0; i < strs.length; i++) {
            //if(strs[i].length() < ans) break;
            if(strs[i].length() < ans) continue;
            boolean isContain = false;
            for(int j = 0; j < strs.length; j++) {
                if(i != j && containString(strs[j], strs[i])) {
                    isContain = true;
                    break;
                }
            }
            if(!isContain) {
                ans = Math.max(ans, strs[i].length());
            }
        }
        
        return ans;
    }

    /***
     * For example, t="abc" is a subsequence of s="aebdc", return true
     * @param s
     * @param t
     */
    private boolean containString(String s, String t) {
        int lens = s.length(), lent = t.length();
        /*
        while(lens > 0 && lent > 0) {
            int i = s.length() - lens;
            int j = t.length() - lent;
            if(s.charAt(i) == t.charAt(j)) {
                lent--;
            }
            lens--;
        }
        return lent == 0;
        */
        int i = 0, j = 0;
        while(i < lens && j < lent) {
            if(s.charAt(i) == t.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == lent;
    }
}
