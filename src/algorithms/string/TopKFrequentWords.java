package algorithms.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import javafx.util.Pair;

/***
 * Given a non-empty list of words, return the k most frequent elements.

    Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
    
    Example 1:
    Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
    Output: ["i", "love"]
    Explanation: "i" and "love" are the two most frequent words.
        Note that "i" comes before "love" due to a lower alphabetical order.
    Example 2:
    Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
    Output: ["the", "is", "sunny", "day"]
    Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
        with the number of occurrence being 4, 3, 2 and 1 respectively.
    Note:
    You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
    Input words contain only lowercase letters.
    Follow up:
    Try to solve it in O(n log k) time and O(n) extra space.
 * @author miche
 *
 */
public class TopKFrequentWords {

    public List<String> topKFrequent(String[] words, int k) {
        List<String> lret = new ArrayList<String>();
        if(words == null || words.length == 0 || k == 0) return lret;
        Map<String, Integer> count = new HashMap<String, Integer>();
        for(int i = 0; i < words.length; i++) {
            count.put(words[i], count.getOrDefault(words[i], 0) + 1);
        }
        List<String> countWords = new ArrayList<String>();
        for(Map.Entry<String, Integer> set : count.entrySet()) {
            countWords.add(set.getValue()+" " + set.getKey());
        }
        Collections.sort(countWords, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                int idx1 = o1.indexOf(' '), idx2 = o2.indexOf(' ');
                int count1 = Integer.parseInt(o1.substring(0, idx1));
                int count2 = Integer.parseInt(o2.substring(0, idx2));
                return count1 == count2 ? o1.substring(idx1+1).compareTo(o2.substring(idx2+1)) : count2-count1;
            }
            
        });
        for(int i = 0; i < k; i++) {
            int idx1 = countWords.get(i).indexOf(' ');
            lret.add(countWords.get(i).substring(idx1+1));
        }
        
        return lret;
    }

    public List<String> topKFrequent2(String[] words, int k) {
        List<String> lret = new ArrayList<String>();
        if(words == null || words.length == 0 || k == 0) return lret;
        Map<String, Integer> count = new HashMap<String, Integer>();
        for(int i = 0; i < words.length; i++) {
            count.put(words[i], count.getOrDefault(words[i], 0) + 1);
        }
        List<String>[] countWords = new List[words.length];
        for(Map.Entry<String, Integer> set : count.entrySet()) {
            if(countWords[set.getValue()-1] == null) {
                countWords[set.getValue()-1] = new ArrayList<String>();
            }
            countWords[set.getValue()-1].add(set.getKey());
        }
        
        for(int i = words.length-1; i >= 0 && k > 0; i--) {
            if(countWords[i] != null) {
                Collections.sort(countWords[i]);
                if(countWords[i].size() <= k) {
                    lret.addAll(countWords[i]);
                    k -= countWords[i].size();
                } else {
                    for(int j = 0; j < k; j++) {
                        lret.add(countWords[i].get(j));
                    }
                    break;
                }
            }
        }

        return lret;
    }

    public List<String> topKFrequent3(String[] words, int k) {
        List<String> lret = new ArrayList<String>();
        if(words == null || words.length == 0 || k == 0) return lret;
        Map<String, Integer> count = new HashMap<String, Integer>();
        for(int i = 0; i < words.length; i++) {
            count.put(words[i], count.getOrDefault(words[i], 0) + 1);
        }
        PriorityQueue<String> queue = new PriorityQueue<String>((a,b)->{
            if(count.get(a) == count.get(b)) return a.compareTo(b);
            else return count.get(b)-count.get(a);
        });
        queue.addAll(count.keySet());
        for(int i = 0; i < k; i++) {
            lret.add(queue.poll());
        }
        return lret;
    }
}
