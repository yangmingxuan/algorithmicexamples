package algorithms.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

    Note:
    
    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.
    Example 1:
    
    Input:
    s = "catsanddog"
    wordDict = ["cat", "cats", "and", "sand", "dog"]
    Output:
    [
      "cats and dog",
      "cat sand dog"
    ]
    Example 2:
    
    Input:
    s = "pineapplepenapple"
    wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
    Output:
    [
      "pine apple pen apple",
      "pineapple pen apple",
      "pine applepen apple"
    ]
    Explanation: Note that you are allowed to reuse a dictionary word.
    Example 3:
    
    Input:
    s = "catsandog"
    wordDict = ["cats", "dog", "sand", "and", "cat"]
    Output:
    []
 * @author miche
 *
 */
public class WordBreakII {

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> lret = new ArrayList<String>();
        Boolean[] cb = new Boolean[s.length()];
        StringBuilder sb = new StringBuilder();
        backtrack(s, 0, cb, wordDict, lret, sb);
        return lret;
    }

    public boolean backtrack(String s, int start, Boolean[] cb, List<String> wordDict, List<String> lret, StringBuilder sb) {
        if(start >=  s.length()) {
            lret.add(sb.toString().trim());
            return true;
        }
        if(cb[start] != null && !cb[start]) return cb[start];
        for(String word : wordDict) {
            if(s.indexOf(word, start) == start) {
                //if(start == 0) sb.delete(0, sb.length());
                sb.append(word);
                sb.append(" ");
                if(backtrack(s, start+word.length(), cb, wordDict, lret, sb)) {
                    cb[start] = true;
                }
                sb.delete(sb.length()-word.length()-1, sb.length());
            }
        }
        if(cb[start] != null && cb[start]) return cb[start];
        cb[start] = false;
        return false;
    }

    public static void main(String[] argv) {
        String s = "pineapplepenapple";
        String[] dict = {"apple", "pen", "applepen", "pine", "pineapple"};
        List<String> wordDict = Arrays.asList(dict);
        WordBreakII wb = new WordBreakII();
        List<String> lret = wb.wordBreak(s, wordDict);
        System.out.println(lret.toString());
    }

}
