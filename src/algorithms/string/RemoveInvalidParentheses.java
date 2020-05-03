package algorithms.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/***
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

    Note: The input string may contain letters other than the parentheses ( and ).
    
    Example 1:
    
    Input: "()())()"
    Output: ["()()()", "(())()"]
    Example 2:
    
    Input: "(a)())()"
    Output: ["(a)()()", "(a())()"]
    Example 3:
    
    Input: ")("
    Output: [""]
 * @author miche
 *
 */
public class RemoveInvalidParentheses {

    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<String>();
        Set<String> set = new HashSet<String>();
        Queue<String> que = new LinkedList<String>();
        boolean found = false;
        que.offer(s);
        while(!que.isEmpty()) {
            String str = que.poll();
            int count = isValidParentheses(str);
            if(count == 0) {
                result.add(str);
                found = true;
            }
            if(!found) {
                //If it has already matched, it will not continue to split the string, otherwise it will be reduced to ""
                nextString(str, set, que, count);
            }
        }

        return result;
    }

    public void nextString(String s, Set<String> set, Queue<String> que, int count) {
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(' && count > 0 || s.charAt(i) == ')' && count < 0) {
                String next = s.substring(0, i) + s.substring(i+1);
                if(!set.contains(next)) {
                    que.offer(next);
                    set.add(next);
                }
            }
        }
    }
    /***
     * A positive return value indicates that there are more '(', and a 0 indicates a match
     * @param s
     * @return
     */
    public int isValidParentheses(String s) {
        int count = 0;
        char[] sarray = s.toCharArray();
        for(char c: sarray) {
            if(c == '(') {
                count++;
            } else if(c == ')') {
                count--;
                if(count < 0) {
                    return count;
                }
            }
        }
        return count;
    }
}
