package algorithms.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/***
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

    An input string is valid if:
    
    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.
    Note that an empty string is also considered valid.
    
    Example 1:
    
    Input: "()"
    Output: true
    Example 2:
    
    Input: "()[]{}"
    Output: true
    Example 3:
    
    Input: "(]"
    Output: false
    Example 4:
    
    Input: "([)]"
    Output: false
    Example 5:
    
    Input: "{[]}"
    Output: true
 * @author miche
 *
 */
public class ValidParentheses {

    public ValidParentheses() {
        // TODO Auto-generated constructor stub
    }

    /***
     * Runtime: 1 ms, faster than 98.56% of Java online submissions for Valid Parentheses.
     * Memory Usage: 37.5 MB, less than 5.06% of Java online submissions for Valid Parentheses.

     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Map<Character, Character> brackets = new HashMap<Character, Character>();
        brackets.put(')', '(');
        brackets.put(']', '[');
        brackets.put('}', '{');
        Stack<Character> match = new Stack<Character>();
        char leftBracket, popBracket;
        for(char c: s.toCharArray()) {
            if(brackets.get(c) == null) {
                match.push(c);
            } else {
                if(match.isEmpty()) {
                    return false;
                }
                popBracket = match.pop();
                leftBracket = brackets.get(c);
                if(popBracket != leftBracket) {
                    return false;
                }
            }
        }

        return match.isEmpty();
    }

}
