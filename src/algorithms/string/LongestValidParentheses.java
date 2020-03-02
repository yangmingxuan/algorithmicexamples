package algorithms.string;

import java.util.Stack;

/***
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

    Example 1:
    
    Input: "(()"
    Output: 2
    Explanation: The longest valid parentheses substring is "()"
    Example 2:
    
    Input: ")()())"
    Output: 4
    Explanation: The longest valid parentheses substring is "()()"
 * @author miche
 *
 */
public class LongestValidParentheses {

    public LongestValidParentheses() {
        // TODO Auto-generated constructor stub
    }

    /***
     * Runtime: 2 ms, faster than 74.50% of Java online submissions for Longest Valid Parentheses.
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int maxlen = 0;
        if(s == null || s.isEmpty()) {
            return maxlen;
        }

        Stack<Integer> match = new Stack<Integer>();
        match.push(-1);
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(') {
                match.push(i);
            } else {
                match.pop();
                if(match.isEmpty()) {
                    match.push(i);
                } else {
                    //The pair char is popped up, and the current length is the difference between the remaining unpaired position and the current position.
                    maxlen = Math.max(maxlen, i-match.peek());
                }
            }
        }

        return maxlen;
    }
}
