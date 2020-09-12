package algorithms.struct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.util.Pair;

/***
 * Time Based Key-Value Store
 * Create a timebased key-value store class TimeMap, that supports two operations.

    1. set(string key, string value, int timestamp)
    
    Stores the key and value, along with the given timestamp.
    2. get(string key, int timestamp)
    
    Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
    If there are multiple such values, it returns the one with the largest timestamp_prev.
    If there are no values, it returns the empty string ("").
     
    
    Example 1:
    
    Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
    Output: [null,null,"bar","bar",null,"bar2","bar2"]
    Explanation:   
    TimeMap kv;   
    kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1   
    kv.get("foo", 1);  // output "bar"   
    kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"   
    kv.set("foo", "bar2", 4);   
    kv.get("foo", 4); // output "bar2"   
    kv.get("foo", 5); //output "bar2"   
    
    Example 2:
    
    Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
    Output: [null,null,null,"","high","high","low","low"]
     
    
    Note:
    
    All key/value strings are lowercase.
    All key/value strings have length in the range [1, 100]
    The timestamps for all TimeMap.set operations are strictly increasing.
    1 <= timestamp <= 10^7
    TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.
 * @author miche
 *
 */
public class TimeMap {

    Map<String, ArrayList<Pair<Integer, String>>> timemap;
    /** Initialize your data structure here. */
    public TimeMap() {
        timemap = new HashMap<String, ArrayList<Pair<Integer, String>>>();
    }
    
    public void set(String key, String value, int timestamp) {
        if(!timemap.containsKey(key)) {
            timemap.put(key, new ArrayList<Pair<Integer, String>>());
        }
        
        //The timestamps for all TimeMap.set operations are strictly increasing.
        timemap.get(key).add(new Pair<Integer, String>(timestamp, value));
    }
    
    public String get(String key, int timestamp) {
        ArrayList<Pair<Integer, String>> list = timemap.get(key);
        if(list == null) {
            return "";
        }
        int found = binarySearch(list, timestamp);
        if(found >= 0) {
            return list.get(found).getValue();
        }
        if(found == -1) {
            //not exist timestamp <= timestamp
            return "";
        }
        return list.get(-found-2).getValue();
    }

    public int binarySearch(ArrayList<Pair<Integer, String>> list, int key) {
        int l = 0, r = list.size() - 1;
        while(l <= r) {
            int mid = (l + r) >>> 1; //(l+r) / 2
            if(list.get(mid).getKey() == key) {
                //found
                return mid;
            }
            if(list.get(mid).getKey() < key) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        
        //not found
        return -(l+1);
    }
}