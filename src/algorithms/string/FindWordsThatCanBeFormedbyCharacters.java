package algorithms.string;

import java.util.Arrays;

/***
 * You are given an array of strings words and a string chars.

    A string is good if it can be formed by characters from chars (each character can only be used once).
    
    Return the sum of lengths of all good strings in words.
    
     
    
    Example 1:
    
    Input: words = ["cat","bt","hat","tree"], chars = "atach"
    Output: 6
    Explanation: 
    The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.
    Example 2:
    
    Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
    Output: 10
    Explanation: 
    The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.
     
    
    Note:
    
    1 <= words.length <= 1000
    1 <= words[i].length, chars.length <= 100
    All strings contain lowercase English letters only.
 * @author miche
 *
 */
public class FindWordsThatCanBeFormedbyCharacters {

    public int countCharacters(String[] words, String chars) {
        if(chars == null || chars.length() == 0) return 0;
        int charCount[] = new int[26];
        char[] arr = chars.toCharArray();
        for(char ch : arr) {
            charCount[ch-'a'] ++; //+= 1;
        }
        int[] map;
        int sum = 0;
        for(String word : words) {
            map = Arrays.copyOf(charCount, 26); //charCount.clone();
            boolean isComplete = true;
            for(char ch : word.toCharArray()) {
                if(map[ch-'a'] > 0) {
                    map[ch-'a'] --; //-= 1;
                } else {
                    isComplete = false;
                    break;
                }
            }
            if(isComplete) {
                sum += word.length();
            }
        }
        
        return sum;
    }

}
