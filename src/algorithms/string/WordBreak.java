package algorithms.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

    Note:
    
    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.
    Example 1:
    
    Input: s = "leetcode", wordDict = ["leet", "code"]
    Output: true
    Explanation: Return true because "leetcode" can be segmented as "leet code".
    Example 2:
    
    Input: s = "applepenapple", wordDict = ["apple", "pen"]
    Output: true
    Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
                 Note that you are allowed to reuse a dictionary word.
    Example 3:
    
    Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
    Output: false
 * @author miche
 *
 */
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        Boolean[] cb = new Boolean[s.length()];
        Set<String> set = new HashSet<String>(wordDict);
        return backtrack(s, 0, cb, wordDict);
    }

    public boolean backtrack(String s, int start, Boolean[] cb, Set<String> set) {
        if(start >= s.length()) return true;
        if(cb[start] != null) return cb[start];
        for(int i = start + 1; i <= s.length(); i++) {
            String str = s.substring(start, i);
            if(set.contains(str) && backtrack(s, i, cb, set)) {
                cb[start] = true;
                return true;
            }
        }
        cb[start] = false;
        return false;
    }

    public boolean backtrack(String s, int start, Boolean[] cb, List<String> wordDict) {
        if(start >= s.length()) return true;
        if(cb[start] != null) return cb[start];
        for(String word : wordDict) {
            if(s.indexOf(word, start) == start) {
                if(backtrack(s, start+word.length(), cb, wordDict)) {
                    cb[start] = true;
                    return true;
                }
            }
        }
        cb[start] = false;
        return false;
    }

    public static void main(String[] argv) {
        String s = "catsandog";
        String[] dict = {"cats", "dog", "sand", "and", "cat"};
        List<String> wordDict = Arrays.asList(dict);
        WordBreak wb = new WordBreak();
        boolean bln = wb.wordBreak(s, wordDict);
        System.out.println(bln);
    }
}

