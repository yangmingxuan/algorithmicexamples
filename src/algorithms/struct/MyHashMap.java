package algorithms.struct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***
 * Design a HashMap without using any built-in hash table libraries.

    To be specific, your design should include these functions:
    
    put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
    get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
    remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.
    
    Example:
    
    MyHashMap hashMap = new MyHashMap();
    hashMap.put(1, 1);          
    hashMap.put(2, 2);         
    hashMap.get(1);            // returns 1
    hashMap.get(3);            // returns -1 (not found)
    hashMap.put(2, 1);          // update the existing value
    hashMap.get(2);            // returns 1 
    hashMap.remove(2);          // remove the mapping for 2
    hashMap.get(2);            // returns -1 (not found) 
    
    Note:
    
    All keys and values will be in the range of [0, 1000000].
    The number of operations will be in the range of [1, 10000].
    Please do not use the built-in HashMap library.
 * @author miche
 *
 */
public class MyHashMap {

    List<Pair<Integer, Integer>> map;

    /** Initialize your data structure here. */
    public MyHashMap() {
        map = new ArrayList<Pair<Integer, Integer>>();
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int found = binarySearch(key);
        if(found < 0) {
            Pair<Integer, Integer> pair = new Pair<Integer, Integer>(key, value);
            map.add(-1*found-1, pair);
            //Collections.sort(map, (a,b)->a.key-b.key);
        } else {
            map.get(found).value = value;
        }
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int found = binarySearch(key);
        if(found >= 0) return map.get(found).value;
        return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int found = binarySearch(key);
        if(found >= 0) map.remove(found);
    }

    public int binarySearch(int key) {
        int l = 0, r = map.size() - 1;
        while(l <= r) {
            int mid = (l+r) >>> 1;  //(l+r) / 2;
            if(map.get(mid).key == key) {
                return mid; //found
            }
            if(map.get(mid).key < key) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -(l+1); //not found
    }

    class Pair<K, V> {
        public K key;
        public V value;
        
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
