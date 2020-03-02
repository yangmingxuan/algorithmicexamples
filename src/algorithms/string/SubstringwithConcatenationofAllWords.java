package algorithms.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.


    Example 1:
    
    Input:
      s = "barfoothefoobarman",
      words = ["foo","bar"]
    Output: [0,9]
    Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
    The output order does not matter, returning [9,0] is fine too.
    Example 2:
    
    Input:
      s = "wordgoodgoodgoodbestword",
      words = ["word","good","best","word"]
    Output: []
 * @author miche
 *
 */
public class SubstringwithConcatenationofAllWords {

    public SubstringwithConcatenationofAllWords() {
        // TODO Auto-generated constructor stub
    }

    /***
     * Runtime: 3 ms, faster than 99.95% of Java online submissions for Substring with Concatenation of All Words.
     * Memory Usage: 41.5 MB, less than 35.71% of Java online submissions for Substring with Concatenation of All Words.     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> lret = new ArrayList<Integer>();
        if(s == null || s.isEmpty() || words == null || words.length == 0) {
            return lret;
        }
        int step = words[0].length();
        int allStep = step * words.length;
        Map<String, Integer> keywordMap = new HashMap<String, Integer>();
        for(int i = 0; i < words.length; i++) {
            //Construct keywords
            keywordMap.put(words[i], keywordMap.getOrDefault(words[i], 0) + 1);
        }
        Map<String, Integer> bingoMap = new HashMap<String, Integer>();
        for(int i = 0; i < step; i++) {
            int start = i;
            while(start <= s.length() - allStep) {
                //If all keywords are hit, then the conditions are met.
                bingoMap.clear();
                //bingoMap.putAll(keywordMap);
                int n = words.length;
                for(int j = start+allStep-step; j >= start; j-= step) {
                    String subs = s.substring(j, j+step);
                    int count = bingoMap.getOrDefault(subs, 0) + 1;
                    if(count > keywordMap.getOrDefault(subs, 0)) {
                        break;
                    }
                    bingoMap.put(subs, count);
                    n--;
                }
                //If all keywords are hit, bingoMap is empty
                if(n == 0) {
                    lret.add(start);
                }
                start += step * Math.max(n, 1);
            }
        }
        return lret;
    }

    /***
     * Runtime: 122 ms
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> lret = new ArrayList<Integer>();
        if(s == null || s.isEmpty() || words == null || words.length == 0) {
            return lret;
        }
        int step = words[0].length();
        int allStep = step * words.length;
        Map<String, Integer> keywordMap = new HashMap<String, Integer>();
        for(int i = 0; i < words.length; i++) {
            //Construct keywords
            keywordMap.put(words[i], keywordMap.getOrDefault(words[i], 0) + 1);
        }
        Map<String, Integer> bingoMap = new HashMap<String, Integer>();
        for(int i = 0; i < step; i++) {
            int start = i;
            while(start <= s.length() - allStep) {
                //If all keywords are hit, then the conditions are met.
                bingoMap.clear();
                bingoMap.putAll(keywordMap);
                for(int j = start; j < start+allStep; j+= step) {
                    String subs = s.substring(j, j+step);
                    if(bingoMap.get(subs) != null) {
                        //if keyword are hit, count - 1 or remove
                        int count = bingoMap.get(subs);
                        if(count > 1) {
                            bingoMap.put(subs, count-1);
                        } else {
                            bingoMap.remove(subs);
                        }
                    } else {
                        break;
                    }
                }
                //If all keywords are hit, bingoMap is empty
                if(bingoMap.isEmpty()) {
                    lret.add(start);
                }
                start += step;
            }
        }
        return lret;
    }

    
    public static void main(String[] argv) {
        String s = "wordgoodgoodgoodbestword"; 
        String[] words = {"word","good","best","good"};
        SubstringwithConcatenationofAllWords sc = new SubstringwithConcatenationofAllWords();
        List<Integer> lret = sc.findSubstring(s, words);
    }
}
