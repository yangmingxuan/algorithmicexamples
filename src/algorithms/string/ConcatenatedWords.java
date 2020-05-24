package algorithms.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
    A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
    
    Example:
    Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
    
    Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
    
    Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
     "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
    "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
    Note:
    The number of elements of the given array will not exceed 10,000
    The length sum of elements in the given array will not exceed 600,000.
    All the input string will only include lower case letters.
    The returned elements order does not matter.
 * @author miche
 *
 */
public class ConcatenatedWords {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<String>();
        if(words == null || words.length == 0) return ans;
        Set<String> allWords = new HashSet<String>();
        for(String word : words) {
            allWords.add(word);
        }
        for(String word: words) {
            if(isConcatenated(word, allWords, 0)) {
                ans.add(word);
            }
        }
        return ans;
    }

    public boolean isConcatenated(String word, Set<String> allWords, int count) {
        if(word.length() == 0 && count > 1) return true;
        for(int i = 1; i <= word.length(); i++) {
            String str = word.substring(0, i);
            if(allWords.contains(str)) {
                if(isConcatenated(word.substring(i), allWords, count+1)) {
                    //concatenated by one more word
                    return true;
                }
            }
        }
        return false;
    }
}
