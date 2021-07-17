package algorithms.string;

import java.util.Arrays;
import java.util.HashMap;

/***
 * Given two strings s and t, determine if they are isomorphic.

    Two strings s and t are isomorphic if the characters in s can be replaced to get t.
    
    All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.
    
     
    
    Example 1:
    
    Input: s = "egg", t = "add"
    Output: true
    Example 2:
    
    Input: s = "foo", t = "bar"
    Output: false
    Example 3:
    
    Input: s = "paper", t = "title"
    Output: true
     
    
    Constraints:
    
    1 <= s.length <= 5 * 104
    t.length == s.length
    s and t consist of any valid ascii character.
 * @author miche
 *
 */
public class IsomorphicStrings {

    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> stot = new HashMap<Character, Character>();
        HashMap<Character, Character> ttos = new HashMap<Character, Character>();
        for(int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if(!stot.containsKey(sc) && !ttos.containsKey(tc)) {
                stot.put(sc, tc);
                ttos.put(tc, sc);
            } else {
                if(stot.get(sc) == null || ttos.get(tc) == null || stot.get(sc) != tc || ttos.get(tc) != sc) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isIsomorphic2(String s, String t) {
        int[] mapstoc = new int[256];
        Arrays.fill(mapstoc, -1);
        int[] mapttos = new int[256];
        Arrays.fill(mapttos, -1);
        for(int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if(mapstoc[sc] == -1 && mapttos[tc] == -1) {
                mapstoc[sc] = tc;
                mapttos[tc] = sc;
            } else {
                if(mapstoc[sc] != tc || mapttos[tc] != sc) {
                    return false;
                }
            }
        }
        return true;
    }
}
