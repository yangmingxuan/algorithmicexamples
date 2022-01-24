package algorithms.string;

import java.util.HashMap;

/***
 * Given a pattern and a string s, find if s follows the same pattern.

    Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
    
     
    
    Example 1:
    
    Input: pattern = "abba", s = "dog cat cat dog"
    Output: true
    Example 2:
    
    Input: pattern = "abba", s = "dog cat cat fish"
    Output: false
    Example 3:
    
    Input: pattern = "aaaa", s = "dog cat cat dog"
    Output: false
     
    
    Constraints:
    
    1 <= pattern.length <= 300
    pattern contains only lower-case English letters.
    1 <= s.length <= 3000
    s contains only lowercase English letters and spaces ' '.
    s does not contain any leading or trailing spaces.
    All the words in s are separated by a single space.
 * @author miche
 *
 */
public class WordPattern {

    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if(pattern.length() != words.length) {
            return false;
        }
        char[] chpattern = pattern.toCharArray();
        HashMap<Character, String>maps = new HashMap<Character, String>();
        HashMap<String, Character>rmaps = new HashMap<String, Character>();
        for(int i = 0; i < words.length; i++) {
            char key = chpattern[i];
            String word = words[i];
            if(!maps.containsKey(key)) {
                if(rmaps.containsKey(word)) {
                    return false;
                }
                maps.put(key, word);
                rmaps.put(word, key);
            } else if(maps.get(key).compareTo(word) != 0) {
                return false;
            }
        }
        
        return true;
    }

}
