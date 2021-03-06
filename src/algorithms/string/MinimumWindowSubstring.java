package algorithms.string;

import java.util.HashMap;
import java.util.Map;

/***
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

    Example:
    
    Input: S = "ADOBECODEBANC", T = "ABC"
    Output: "BANC"
    Note:
    
    If there is no such window in S that covers all characters in T, return the empty string "".
    If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 * @author miche
 *
 */
public class MinimumWindowSubstring {

    /***
     * Compare two map
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if(s.isEmpty() || t.isEmpty() || s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> tm = new HashMap<Character, Integer>();
        Map<Character, Integer> sm = new HashMap<Character, Integer>();
        char[] tcs = t.toCharArray();
        char[] scs = s.toCharArray();
        int left = 0, right = tcs.length - 1, minLen = Integer.MAX_VALUE, lb = 0, rb = 0;

        //initial
        for(int i = 0; i <= right; i++) {
            putInMap(tm, tcs[i]);
            putInMap(sm, scs[i]);
        }
        while(left <= scs.length - tcs.length && right <= scs.length) {
            if(right + 1 - left < tcs.length || !containAll(sm, tm)) {
                //if not include, expand the window
                right++;
                if(right < scs.length) {
                    putInMap(sm, scs[right]);
                }
            } else {
                //if include, narrow the window
                if(minLen > right + 1 - left) {
                    minLen = right + 1 - left;
                    lb = left;
                    rb = right+1;
                }
                cutFromMap(sm, scs[left]);
                left++;
            }
        }
        
        return rb == lb ? "" : s.substring(lb, rb);
    }

    public void putInMap(Map<Character, Integer>map, char c) {
        map.put(c, map.getOrDefault(c, 0) + 1);
    }

    public void cutFromMap(Map<Character, Integer>map, char c) {
        if(map.get(c) != null) {
            int count = map.get(c);
            if(count > 1) {
                map.put(c, count-1);
            } else {
                map.remove(c);
            }
        }
    }

    public boolean containAll(Map<Character, Integer> sm, Map<Character, Integer> tm) {
        for(Map.Entry<Character, Integer> m : tm.entrySet()) {
            if(!sm.containsKey(m.getKey())) {
                return false;
            }
            if(sm.get(m.getKey()) < m.getValue()) {
                return false;
            }
        }
        return true;
    }

    /***
     * Compare one map
     * @param s
     * @param t
     * @return
     */
    public String minWindow2(String s, String t) {
        if(s.isEmpty() || t.isEmpty() || s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> tm = new HashMap<Character, Integer>();
        char[] tcs = t.toCharArray();
        char[] scs = s.toCharArray();
        int left = 0, right = 0, minLen = Integer.MAX_VALUE, lb = 0, rb = 0;
        int total = tcs.length; //record if all found

      //initial
        for(int i = 0; i < tcs.length; i++) {
            tm.put(tcs[i], tm.getOrDefault(tcs[i], 0) + 1);
        }

        while(right < scs.length) {
            if(tm.get(scs[right]) != null) {
                if(tm.get(scs[right]) > 0) total--;
                tm.put(scs[right], tm.get(scs[right]) - 1);
            }
            while(total == 0) {
                //if window all include
                if(minLen > right + 1 - left) {
                    minLen = right + 1 - left;
                    lb = left;
                    rb = right+1;
                }
                if(tm.get(scs[left]) != null) {
                    tm.put(scs[left], tm.get(scs[left]) + 1);
                    if(tm.get(scs[left]) > 0) total++;
                }
                left++; //narrow the window
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(lb, rb);
    }

    /***
     * compare array
     * @param s
     * @param t
     * @return
     */
    public String minWindow3(String s, String t) {
        int [] tm = new int[128];
        char[] tcs = t.toCharArray();
        char[] scs = s.toCharArray();
        int left = 0, right = 0, minLen = Integer.MAX_VALUE, lb = 0, rb = 0;
        int total = tcs.length; //record if all found

      //initial
        for(int i = 0; i < tcs.length; i++) {
            tm[tcs[i]]++;
        }
        while(right < scs.length) {
            if(tm[scs[right]]-- > 0) total--;
            while(total == 0) {
                //if window all include
                if(minLen > right + 1 - left) {
                    minLen = right + 1 - left;
                    lb = left;
                    rb = right+1;
                }
                if(++tm[scs[left]] > 0) total++;
                left++; //narrow the window
            }
            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(lb, rb);
    }

}
