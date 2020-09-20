package algorithms.string;

import java.util.ArrayList;
import java.util.List;

/***
 * Given a string s, partition s such that every substring of the partition is a palindrome.

    Return all possible palindrome partitioning of s.
    
    Example:
    
    Input: "aab"
    Output:
    [
      ["aa","b"],
      ["a","a","b"]
    ]
 * @author miche
 *
 */
public class PalindromePartitioning {

    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<List<String>>();
        List<String> tmp = new ArrayList<String>();
        backtrack(s, ans, tmp, 0);
        return ans;
    }

    public void backtrack(String s, List<List<String>> ans, List<String> tmp, int index) {
        if(index == s.length()) {
            ans.add(new ArrayList<String>(tmp));
            return;
        }
        for(int i = index; i < s.length(); i++) {
            String str = s.substring(index, i+1);
            if(isPalindrome(str)) {
                tmp.add(str);
                backtrack(s, ans, tmp, i+1);
                tmp.remove(tmp.size()-1);
            }
        }
    }

    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}
