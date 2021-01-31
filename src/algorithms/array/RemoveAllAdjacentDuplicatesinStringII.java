package algorithms.array;

import java.util.Stack;

/***
 * Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.

    We repeatedly make k duplicate removals on s until we no longer can.
    
    Return the final string after all such duplicate removals have been made.
    
    It is guaranteed that the answer is unique.
    
     
    
    Example 1:
    
    Input: s = "abcd", k = 2
    Output: "abcd"
    Explanation: There's nothing to delete.
    Example 2:
    
    Input: s = "deeedbbcccbdaa", k = 3
    Output: "aa"
    Explanation: 
    First delete "eee" and "ccc", get "ddbbbdaa"
    Then delete "bbb", get "dddaa"
    Finally delete "ddd", get "aa"
    Example 3:
    
    Input: s = "pbbcggttciiippooaais", k = 2
    Output: "ps"
     
    
    Constraints:
    
    1 <= s.length <= 10^5
    2 <= k <= 10^4
    s only contains lower case English letters.
 * @author miche
 *
 */
public class RemoveAllAdjacentDuplicatesinStringII {

    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i < sb.length(); i++) {
            if(!stack.isEmpty() && sb.charAt(i) == sb.charAt(i-1)) {
                int count = stack.pop()+1;
                if(count == k) {
                    sb.delete(i-k+1, i+1);
                    i -= k;
                } else {
                    stack.add(count);
                }
            } else {
                stack.add(1);
            }
        }
        
        return sb.toString();
    }

    public String removeDuplicates2(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int[] sc = new int[sb.length()];
        
        for(int i = 0; i < sb.length(); i++) {
            if(i > 0 && sb.charAt(i) == sb.charAt(i-1)) {
                int count = sc[i-1] + 1;
                if(count == k) {
                    sb.delete(i-k+1, i+1);
                    i -= k;
                } else {
                    sc[i] = count;
                }
            } else {
                sc[i] = 1;
            }
        }
        
        return sb.toString();
    }
}
