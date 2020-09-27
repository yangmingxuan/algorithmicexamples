package algorithms.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

    Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
    
    The order of output does not matter.
    
    Example 1:
    
    Input:
    s: "cbaebabacd" p: "abc"
    
    Output:
    [0, 6]
    
    Explanation:
    The substring with start index = 0 is "cba", which is an anagram of "abc".
    The substring with start index = 6 is "bac", which is an anagram of "abc".
    Example 2:
    
    Input:
    s: "abab" p: "ab"
    
    Output:
    [0, 1, 2]
    
    Explanation:
    The substring with start index = 0 is "ab", which is an anagram of "ab".
    The substring with start index = 1 is "ba", which is an anagram of "ab".
    The substring with start index = 2 is "ab", which is an anagram of "ab".
 * @author miche
 *
 */
public class FindAllAnagramsinaString {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<Integer>();
        if(s == null || s.length() == 0) return ans;
        HashMap<Character, Integer> originMap = new HashMap<Character,Integer>();
        //analysis the p string
        for(char ch : p.toCharArray()) {
            originMap.put(ch, originMap.getOrDefault(ch, 0) + 1);
        }
        HashMap<Character, Integer> comparaMap = new HashMap<Character,Integer>();
        restorMap(originMap, comparaMap);
        
        int first_find = -1, cursor = 0;
        boolean infind = false;
        while(cursor < s.length()) {
            char ch = s.charAt(cursor);
            if(comparaMap.containsKey(ch)) {
                int left = comparaMap.get(ch);
                if(left == 1) {
                    comparaMap.remove(ch);
                } else {
                    comparaMap.put(ch, left-1);
                }
                if(!infind) {
                    infind = true;
                    first_find = cursor;
                }
                if(comparaMap.isEmpty()) {
                    //find the anagram string
                    ans.add(first_find);
                    //move the window
                    cursor++;
                    if(cursor >= s.length()) {
                        //the left string less then p
                        break;
                    }
                    //add the first_find count
                    comparaMap.put(s.charAt(first_find), 1);
                    if(p.length() == 1) {
                        infind = false;
                        first_find = -1;
                    } else {
                        first_find++;
                    }
                    continue;
                }
            } else {
                if(infind) {
                    if(originMap.containsKey(ch)) {
                        //repeat element, move the window to the repeat element's next
                        while(ch != s.charAt(first_find)) {
                            comparaMap.put(s.charAt(first_find), comparaMap.getOrDefault(s.charAt(first_find), 0)+1);
                            first_find++;
                        }
                        first_find++;
                    } else {
                        //all previous matches are invalid
                        restorMap(originMap, comparaMap);
                        infind = false;
                        first_find = -1;
                    }
                }
            }
            cursor++;
        }
        
        return ans;
    }

    public void restorMap(HashMap<Character, Integer> originMap, HashMap<Character, Integer> comparaMap) {
        for(Map.Entry<Character, Integer> entry : originMap.entrySet()) {
            comparaMap.put(entry.getKey(), entry.getValue());
        }
    }

    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> ans = new ArrayList<Integer>();
        if(s == null || s.length() == 0) return ans;
        char[] sch = s.toCharArray();
        char[] pch = p.toCharArray();
        int[] originChar = new int[26];
        for(char ch : pch) {
            originChar[ch-'a']++;
        }
        int m = s.length(), n = p.length();
        int[] comparaChar = new int[26];

        for(int i = 0; i < m; i++) {
            //move the window
            comparaChar[sch[i]-'a']++;
            if(i >= n) {
                comparaChar[sch[i-n]-'a']--;
            }
            if(Arrays.equals(originChar, comparaChar)) {
                ans.add(i-n+1);
            }
        }
        
        return ans;
    }
}
