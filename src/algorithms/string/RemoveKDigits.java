package algorithms.string;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/***
 * Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.

 

    Example 1:
    
    Input: num = "1432219", k = 3
    Output: "1219"
    Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
    Example 2:
    
    Input: num = "10200", k = 1
    Output: "200"
    Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
    Example 3:
    
    Input: num = "10", k = 2
    Output: "0"
    Explanation: Remove all the digits from the number and it is left with nothing which is 0.
     
    
    Constraints:
    
    1 <= k <= num.length <= 10^5
    num consists of only digits.
    num does not have any leading zeros except for the zero itself.
 * @author miche
 *
 */
public class RemoveKDigits {

    public String removeKdigits(String num, int k) {
        if(k >= num.length()) return "0";
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i < num.length(); i++) {
            char ch = num.charAt(i);
            while(!stack.isEmpty() && stack.peek() > ch && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(ch);
        }
        while(k > 0) {
            stack.pop();
            k--;
        }
        int len = stack.size();
        //if(len == 0) return "0";
        char[] newnum = new char[len];
        for(int i = len-1; i >= 0; i--) {
            newnum[i] = stack.pop();
        }
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while(idx < len && newnum[idx] == '0') idx++;
        if(idx == len) return "0";
        for(int i = idx; i < len; i++) {
            sb.append(newnum[i]);
        }
        
        return sb.toString();
    }

    public String removeKdigits2(String num, int k) {
        if(k >= num.length()) return "0";
        Deque<Character> stack = new LinkedList<Character>();
        for(int i = 0; i < num.length(); i++) {
            char ch = num.charAt(i);
            while(k > 0 && !stack.isEmpty() && stack.peekLast() > ch) {
                stack.pollLast();
                k--;
            }
            stack.offer(ch);
        }
        int len = stack.size()-k;
        int idx = 0;
        while(idx < len && stack.peek() == '0') {
            stack.poll();
            idx++;
        }
        if(idx == len) return "0";
        StringBuilder sb = new StringBuilder();
        for(int i = idx; i < len; i++) {
            sb.append(stack.poll());
        }
        return sb.toString();
    }
}
