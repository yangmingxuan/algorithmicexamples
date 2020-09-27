package algorithms.array;

import java.util.HashMap;
import java.util.Map;

/***
 * Given a string, find the first non-repeating character in it and return its index. If it doesn't exist, return -1.

    Examples:
    
    s = "leetcode"
    return 0.
    
    s = "loveleetcode"
    return 2.
     
    
    Note: You may assume the string contains only lowercase English letters.
 * @author miche
 *
 */
public class FirstUniqueCharacterinaString {

    public int firstUniqChar(String s) {
        if(s == null || s.length() == 0) return -1;
        char[] letters = new char[26];
        for(char ch : s.toCharArray()) {
            letters[ch-'a']++;
        }
        
        /*
        for(int i = 0; i < s.length(); i++) {
            if(letters[s.charAt(i)-'a'] == 1) return i;
        }
        return -1;
        */
        int ans = Integer.MAX_VALUE;
        for(char ch = 'a'; ch <= 'z'; ch++) {
            if(letters[ch-'a'] == 1) {
                ans = Math.min(ans, s.indexOf(ch));
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    public int firstUniqChar2(String s) {
        if(s == null || s.length() == 0) return -1;
        Map<Character, Integer> letters = new HashMap<Character, Integer>();
        for(char ch : s.toCharArray()) {
            letters.put(ch, letters.getOrDefault(ch, 0)+1);
        }
        
        /*
        for(int i = 0; i < s.length(); i++) {
            if(1 == letters.get(s.charAt(i))) return i;
        }
        return -1;
        */
        int ans = Integer.MAX_VALUE;
        for(Map.Entry<Character, Integer> entry : letters.entrySet()) {
            if(entry.getValue() == 1) {
                ans = Math.min(ans, s.indexOf(entry.getKey()));
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
