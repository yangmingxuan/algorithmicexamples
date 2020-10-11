package algorithms.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/***
 * Given a list of unique words, return all the pairs of the distinct indices (i, j) in the given list, so that the concatenation of the two words words[i] + words[j] is a palindrome.

 

    Example 1:
    
    Input: words = ["abcd","dcba","lls","s","sssll"]
    Output: [[0,1],[1,0],[3,2],[2,4]]
    Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
    Example 2:
    
    Input: words = ["bat","tab","cat"]
    Output: [[0,1],[1,0]]
    Explanation: The palindromes are ["battab","tabbat"]
    Example 3:
    
    Input: words = ["a",""]
    Output: [[0,1],[1,0]]
     
    
    Constraints:
    
    1 <= words.length <= 5000
    0 <= words[i] <= 300
    words[i] consists of lower-case English letters.
 * @author miche
 *
 */
public class PalindromePairs {

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for(int i = 0; i < words.length; i++) {
            for(int j = 0; j < words.length; j++) {
                if(i == j) continue;
                if(isPalidromeString(words[i].concat(words[j]), 0, words[i].length()+words[j].length()-1)) {
                    ans.add(Arrays.asList(i,j));
                }
            }
        }
        return ans;
    }

    public boolean isPalidromeString(String str, int l, int r) {
        while(l < r) {
            if(str.charAt(l) != str.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    public List<String> prefixPalidrome(String word) {
        List<String> prefix = new ArrayList<String>();
        for(int i = 0; i < word.length(); i++) {
            if(isPalidromeString(word, i, word.length()-1)) {
                prefix.add(word.substring(0, i));
            }
        }
        
        return prefix;
    }

    public List<String> suffixPalidrome(String word) {
        List<String> suffix = new ArrayList<String>();
        for(int i = 0; i < word.length(); i++) {
            if(isPalidromeString(word, 0, i)) {
                suffix.add(word.substring(i+1));
            }
        }
        return suffix;
    }
    
    public List<List<Integer>> palindromePairs2(String[] words) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        HashMap<String, Integer> wordIndex = new HashMap<String, Integer>();
        for(int i = 0; i < words.length; i++) {
            wordIndex.put(words[i], i);
        }
        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            
            //find the reverse word
            String reverseWord = new StringBuilder(word).reverse().toString();
            if(wordIndex.containsKey(reverseWord) && wordIndex.get(reverseWord) != i) {
                ans.add(Arrays.asList(i, wordIndex.get(reverseWord)));
            }

            //find the prefix's reverse word, word+reversePrefix will be palidrome
            List<String> allprefix = prefixPalidrome(word);
            for(String prefix : allprefix) {
                String reversePrefix = new StringBuilder(prefix).reverse().toString();
                if(wordIndex.containsKey(reversePrefix)) {
                    ans.add(Arrays.asList(i, wordIndex.get(reversePrefix)));
                }
            }

            //find the suffix's reverse word, reverseSuffix+word will be palidrome
            List<String> allsuffix = suffixPalidrome(word);
            for(String suffix : allsuffix) {
                String reverseSuffix = new StringBuilder(suffix).reverse().toString();
                if(wordIndex.containsKey(reverseSuffix)) {
                    ans.add(Arrays.asList(wordIndex.get(reverseSuffix), i));
                }
            }
        }
        
        return ans;
    }
}
