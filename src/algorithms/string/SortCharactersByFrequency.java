package algorithms.string;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/***
 * Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.

    Return the sorted string. If there are multiple answers, return any of them.
    
     
    
    Example 1:
    
    Input: s = "tree"
    Output: "eert"
    Explanation: 'e' appears twice while 'r' and 't' both appear once.
    So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
    Example 2:
    
    Input: s = "cccaaa"
    Output: "aaaccc"
    Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
    Note that "cacaca" is incorrect, as the same characters must be together.
    Example 3:
    
    Input: s = "Aabb"
    Output: "bbAa"
    Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
    Note that 'A' and 'a' are treated as two different characters.
     
    
    Constraints:
    
    1 <= s.length <= 5 * 105
    s consists of uppercase and lowercase English letters and digits.
 * @author miche
 *
 */
public class SortCharactersByFrequency {

    public String frequencySort(String s) {
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        char[] sc = s.toCharArray();
        for(char ch : sc) {
            count.put(ch, count.getOrDefault(ch, 0)+1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> pqueue = new PriorityQueue<Map.Entry<Character, Integer>>((a,b) -> b.getValue()-a.getValue());
        for(Map.Entry<Character, Integer> entry : count.entrySet()) {
            pqueue.offer(entry);
        }
        int index = 0;
        while(!pqueue.isEmpty()) {
            Map.Entry<Character, Integer> entry = pqueue.poll();
            char ch = entry.getKey();
            int nums = entry.getValue();
            while(nums > 0) {
                sc[index++] = ch;
                nums--;
            }
        }
        return new String(sc);
    }

}
