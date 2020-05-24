package algorithms.struct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

/***
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

    get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
    put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
    
    Note that the number of times an item is used is the number of calls to the get and put functions for that item since it was inserted. This number is set to zero when the item is removed.
    
     
    
    Follow up:
    Could you do both operations in O(1) time complexity?
    
     
    
    Example:
    
    LFUCache cache = new LFUCache( 2  ); // capacity 
    
    cache.put(1, 1);
    cache.put(2, 2);
    cache.get(1);       // returns 1
    cache.put(3, 3);    // evicts key 2
    cache.get(2);       // returns -1 (not found)
    cache.get(3);       // returns 3.
    cache.put(4, 4);    // evicts key 1.
    cache.get(1);       // returns -1 (not found)
    cache.get(3);       // returns 3
    cache.get(4);       // returns 4
 * @author miche
 *
 */
public class LFUCache {

    int capacity;
    int minFreq;
    HashMap<Integer, MemberCache> cache;
    ArrayList<LinkedHashSet<Integer>> freqlist;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.cache = new HashMap<Integer, MemberCache>();
        this.freqlist = new ArrayList<LinkedHashSet<Integer>>();
    }

    public int get(int key) {
        if(capacity <= 0 || !cache.containsKey(key)) {
            return -1;
        }
        MemberCache ca = cache.get(key);
        int freq = ca.cacheIdx;
        //remove key from the old freq use
        freqlist.get(freq).remove(key);
        //if freq is min cout set the new min
        if(freq == minFreq && freqlist.get(minFreq).isEmpty()) {
            minFreq++;
        }
        freq++; //increase the use count
        if(freq == freqlist.size()) {
            freqlist.add(new LinkedHashSet<Integer>());
        }
        //put key to the new freq use
        ca.cacheIdx = freq;
        freqlist.get(freq).add(key);
        
        return ca.val;
    }
    
    public void put(int key, int value) {
        if(capacity <= 0) {
            return;
        }
        //if new key and full size, remove the min used one
        if(capacity == cache.size() && !cache.containsKey(key)) {
            //LinkedHashSet guarantees the access order
            int removeKey = freqlist.get(minFreq).iterator().next();
            freqlist.get(minFreq).remove(removeKey);
            cache.remove(removeKey);
        }
        if(!cache.containsKey(key)) {
            minFreq = 0;
            if(freqlist.size() == 0) {
                freqlist.add(new LinkedHashSet<Integer>());
            }
            freqlist.get(minFreq).add(key);
            cache.put(key, new MemberCache(value, minFreq));
        } else {
            MemberCache ca = cache.get(key);
            int freq = ca.cacheIdx;
            //remove key from the old freq use
            freqlist.get(freq).remove(key);
            //if freq is min cout set the new min
            if(freq == minFreq && freqlist.get(minFreq).isEmpty()) {
                minFreq++;
            }
            freq++; //increase the use count
            if(freq == freqlist.size()) {
                freqlist.add(new LinkedHashSet<Integer>());
            }
            //put key to the new freq use
            ca.val = value;
            ca.cacheIdx = freq;
            freqlist.get(freq).add(key);
        }
    }
}
class MemberCache {
    int val;
    int cacheIdx;  //in LRU is index, in LFU is frequence count
    
    MemberCache(int val, int cacheIdx) {
        this.val = val;
        this.cacheIdx = cacheIdx;
    }
}
