package algorithms.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/***
 * Given an array of strings, group anagrams together.

    Example:
    
    Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
    Output:
    [
      ["ate","eat","tea"],
      ["nat","tan"],
      ["bat"]
    ]
    Note:
    
    All inputs will be in lowercase.
    The order of your output does not matter.
 * @author miche
 *
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> lret = new ArrayList<List<String>>();
        Map<String, Integer> existAnagram = new HashMap<String, Integer>();
        int index = 0;
        for(String str:strs) {
            int[] echars = new int[26];
            for(char c:str.toCharArray()) {
                echars[c-'a'] += 1;
            }
            StringBuilder sbtmp = new StringBuilder();
            for(int i = 0; i < 26; i++) {
                sbtmp.append(echars[i]);
            }
            if(existAnagram.containsKey(sbtmp.toString())) {
                lret.get(existAnagram.get(sbtmp.toString())).add(str);
            } else {
                List<String> ltmp = new ArrayList<String>();
                ltmp.add(str);
                lret.add(ltmp);
                existAnagram.put(sbtmp.toString(), index);
                index++;
            }
        }
        return lret;
    }

    /***
     * quicker than groupAnagrams
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> lret = new ArrayList<List<String>>();
        Map<String, Integer> existAnagram = new HashMap<String, Integer>();
        int index = 0;
        for(String str:strs) {
            char[] ctmps = str.toCharArray();
            Arrays.sort(ctmps);
            String stmp = new String(ctmps);
            if(existAnagram.containsKey(stmp)) {
                lret.get(existAnagram.get(stmp)).add(str);
            } else {
                List<String> ltmp = new ArrayList<String>();
                ltmp.add(str);
                lret.add(ltmp);
                existAnagram.put(stmp, index);
                index++;
            }
        }
        return lret;
    }

    /***
     * use hash
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams3(String[] strs) {
        List<List<String>> lret = new ArrayList<List<String>>();
        HashMap<Integer, Integer> existAnagram = new HashMap<Integer, Integer>();
        int index = 0;
        for(String str: strs) {
            int hash = getHash(str);
            if(existAnagram.containsKey(hash)) {
                lret.get(existAnagram.get(hash)).add(str);
            } else {
                List<String> ltmp = new ArrayList<String>();
                ltmp.add(str);
                lret.add(ltmp);
                existAnagram.put(hash, index);
                index++;
            }
        }
        return lret;
    }

    private int getHash(String s) {
        int hash = 0;
        for(char ch : s.toCharArray()) {
            int id = ch - 'a' + 1;
            hash += (id * (17+id) * (23+id) * (29+id) * (41+id));
        }
        return hash;
    }
 
    public static void main(String[] atgv) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        GroupAnagrams gag = new GroupAnagrams();
        List<List<String>> lret = gag.groupAnagrams2(strs);
        System.out.print(lret.toString());
    }
}
