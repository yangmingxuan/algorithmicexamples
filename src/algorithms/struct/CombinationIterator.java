package algorithms.struct;

import java.util.Collections;
import java.util.LinkedList;

/***
 * Iterator for Combination
 * Design the CombinationIterator class:

    CombinationIterator(string characters, int combinationLength) Initializes the object with a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
    next() Returns the next combination of length combinationLength in lexicographical order.
    hasNext() Returns true if and only if there exists a next combination.
     
    
    Example 1:
    
    Input
    ["CombinationIterator", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
    [["abc", 2], [], [], [], [], [], []]
    Output
    [null, "ab", true, "ac", true, "bc", false]
    
    Explanation
    CombinationIterator itr = new CombinationIterator("abc", 2);
    itr.next();    // return "ab"
    itr.hasNext(); // return True
    itr.next();    // return "ac"
    itr.hasNext(); // return True
    itr.next();    // return "bc"
    itr.hasNext(); // return False
     
    
    Constraints:
    
    1 <= combinationLength <= characters.length <= 15
    All the characters of characters are unique.
    At most 10^4 calls will be made to next and hasNext.
    It's guaranteed that all calls of the function next are valid.
 * @author miche
 *
 */
public class CombinationIterator {

    LinkedList<String> queue = new LinkedList<String>();
    public CombinationIterator(String characters, int combinationLength) {
        //All characters are combined by combinationLength and sort
        getCobination(characters, combinationLength, 0, new StringBuilder());
        Collections.sort(queue);
    }
    
    public String next() {
        return queue.poll();
    }
    
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    private void getCobination(String chs, int len, int start, StringBuilder sb) {
        if(len == 0) {
            queue.offer(sb.toString());
            return;
        }
        for(int i = start; i <= chs.length()-len; i++) {
            sb.append(chs.charAt(i));
            getCobination(chs, len-1, i+1, sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
