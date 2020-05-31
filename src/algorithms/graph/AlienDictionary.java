package algorithms.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

    Example 1:
    
    Input:
    [
      "wrt",
      "wrf",
      "er",
      "ett",
      "rftt"
    ]
    
    Output: "wertf"
    Example 2:
    
    Input:
    [
      "z",
      "x"
    ]
    
    Output: "zx"
    Example 3:
    
    Input:
    [
      "z",
      "x",
      "z"
    ] 
    
    Output: "" 
    
    Explanation: The order is invalid, so return "".
    Note:
    
    You may assume all letters are in lowercase.
    If the order is invalid, return an empty string.
    There may be multiple valid order of letters, return any one of them is fine.
 * @author miche
 *
 */
public class AlienDictionary {

    Map<Character, List<Character>> adjList;
    Map<Character, Boolean> visited;
    StringBuilder ans;
    public String alienOrder(String[] words) {
        ans = new StringBuilder();
        adjList = new HashMap<Character, List<Character>>();
        visited = new HashMap<Character, Boolean>();
        if(words == null || words.length == 0) return ans.toString();
        
        //initial all Character
        for(String word : words) {
            for(char ch : word.toCharArray()) {
                adjList.putIfAbsent(ch, new ArrayList<Character>());
            }
        }

        //construct the adjList
        for(int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i+1];
            if(word1.startsWith(word2) && word1.length() > word2.length()) {
                //invalid order
                return "";
            }
            //the first different character indicate the order
            for(int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if(word1.charAt(j) != word2.charAt(j)) {
                    adjList.get(word2.charAt(j)).add(word1.charAt(j));
                    break;
                }
            }
        }

        for(Character ch : adjList.keySet()) {
            if(!dfs(ch)) {
                return "";
            }
        }
        
        if(ans.length() != adjList.size()) return "";
        
        return ans.toString();
    }

    public boolean dfs(Character ch) {
        if(visited.containsKey(ch)) {
            return visited.get(ch); //if have been visited
        }
        visited.put(ch, false); //initial false, if next char visit this, indicate there is a cycle
        
        for(Character preCh : adjList.get(ch)) {
            if(!dfs(preCh)) {
                //there is a cycle
                return false;
            }
        }
        
        visited.put(ch, true);
        ans.append(ch);
        return true;
    }
}
