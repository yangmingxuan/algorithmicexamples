package algorithms.trie;

import java.util.HashMap;

/***
 * Implement the MapSum class:

    MapSum() Initializes the MapSum object.
    void insert(String key, int val) Inserts the key-val pair into the map. If the key already existed, the original key-value pair will be overridden to the new one.
    int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.
     
    
    Example 1:
    
    Input
    ["MapSum", "insert", "sum", "insert", "sum"]
    [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
    Output
    [null, null, 3, null, 5]
    
    Explanation
    MapSum mapSum = new MapSum();
    mapSum.insert("apple", 3);  
    mapSum.sum("ap");           // return 3 (apple = 3)
    mapSum.insert("app", 2);    
    mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
     
    
    Constraints:
    
    1 <= key.length, prefix.length <= 50
    key and prefix consist of only lowercase English letters.
    1 <= val <= 1000
    At most 50 calls will be made to insert and sum.
 * @author miche
 *
 */
public class MapSum {

    HashMap<String, Integer> map;
    TrieNodeSum root;
    
    /** Initialize your data structure here. */
    public MapSum() {
        map = new HashMap<String, Integer>();
        root = new TrieNodeSum();
    }
    
    public void insert(String key, int val) {
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, val);
        TrieNodeSum cur = root;
        cur.score += delta;
        for(char c : key.toCharArray()) {
            if(!cur.containsKey(c)) {
                cur.put(c, new TrieNodeSum());
            }
            cur = (TrieNodeSum) cur.get(c);
            cur.score += delta;
        }
    }
    
    public int sum(String prefix) {
        TrieNodeSum cur = root;
        for(char c : prefix.toCharArray()) {
            cur = (TrieNodeSum) cur.get(c);
            if(cur == null) {
                return 0;
            }
        }
        return cur.score;
    }

}

class TrieNodeSum extends TrieNode {
    int score;
}
