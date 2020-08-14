package algorithms.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/***
 * Given a string s of '(' , ')' and lowercase English characters. 

    Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
    
    Formally, a parentheses string is valid if and only if:
    
    It is the empty string, contains only lowercase characters, or
    It can be written as AB (A concatenated with B), where A and B are valid strings, or
    It can be written as (A), where A is a valid string.
     
    
    Example 1:
    
    Input: s = "lee(t(c)o)de)"
    Output: "lee(t(c)o)de"
    Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
    Example 2:
    
    Input: s = "a)b(c)d"
    Output: "ab(c)d"
    Example 3:
    
    Input: s = "))(("
    Output: ""
    Explanation: An empty string is also valid.
    Example 4:
    
    Input: s = "(a(b(c)d)"
    Output: "a(b(c)d)"
     
    
    Constraints:
    
    1 <= s.length <= 10^5
    s[i] is one of  '(' , ')' and lowercase English letters.
 * @author miche
 *
 */
public class MinimumRemovetoMakeValidParentheses {

    public String minRemoveToMakeValid(String s) {
        if(s == null || s.length() == 0) return s;
        
        List<Integer> del = new ArrayList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        //get the extra )
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.add(i);
            } else if(s.charAt(i) == ')') {
                if(!stack.isEmpty()) {
                    stack.pop();
                } else {
                    del.add(i);
                }
            }
        }
        //get the extra (
        while(!stack.isEmpty()) {
            del.add(stack.pop());
        }
        
        Collections.sort(del);
        
        
        StringBuilder ans = new StringBuilder(s);
        for(int i = del.size() - 1; i >= 0; i--) {
            ans.deleteCharAt(del.get(i));
        }
        return ans.toString();
    }

}
