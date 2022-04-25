package algorithms.array;

import java.util.ArrayDeque;
import java.util.Deque;

/***
 * The numeric value of a lowercase character is defined as its position (1-indexed) in the alphabet, so the numeric value of a is 1, the numeric value of b is 2, the numeric value of c is 3, and so on.

    The numeric value of a string consisting of lowercase characters is defined as the sum of its characters' numeric values. For example, the numeric value of the string "abe" is equal to 1 + 2 + 5 = 8.
    
    You are given two integers n and k. Return the lexicographically smallest string with length equal to n and numeric value equal to k.
    
    Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order, that is, either x is a prefix of y, or if i is the first position such that x[i] != y[i], then x[i] comes before y[i] in alphabetic order.
    
     
    
    Example 1:
    
    Input: n = 3, k = 27
    Output: "aay"
    Explanation: The numeric value of the string is 1 + 1 + 25 = 27, and it is the smallest string with such a value and length equal to 3.
    Example 2:
    
    Input: n = 5, k = 73
    Output: "aaszz"
     
    
    Constraints:
    
    1 <= n <= 10^5
    n <= k <= 26 * n
 * @author miche
 *
 */
public class SmallestStringWithAGivenNumericValue {

    public String getSmallestString(int n, int k) {
        Deque<Character> stack = new ArrayDeque<Character>();
        
        helper(n, k, stack);
        
        StringBuilder ans = new StringBuilder();
        while(!stack.isEmpty()) {
            ans.append(stack.pollLast());
        }
        return ans.toString();
    }
    
    private boolean helper(int n, int k, Deque<Character> stack) {
        if(n == 0 && k == 0) {
            return true;
        } else if(n <= 0 || k < n) {
            return false;
        }
        //If you build the string from the end to the beginning, it will always be optimal to put the highest possible character at the current index.
        for(int i = Math.min(k-n+1, 26); i > 0; i--) {
            stack.add((char)(i+'a'-1));
            if(helper(n-1, k-i, stack)) {
                return true;
            } else {
                stack.pollLast();
            }
        }
        
        return false;
    }

    public String getSmallestString2(int n, int k) {
        StringBuilder ans = new StringBuilder();
        int idx = 0;
        while(idx < n) {
            if(26*(n-idx) >= k) {
                ans.append('a');
                idx++;
                k--;
            } else {
                break;
            }
        }
        ans.deleteCharAt(idx-1);
        ans.append((char)(k%26 + 'a'));
        while(idx < n) {
            ans.append('z');
            idx++;
        }
        
        return ans.toString();
    }
    
    public String getSmallestString3(int n, int k) {
        char[] ans = new char[n];
        while(k-n+1 >= 26) {
            ans[n-1] = 'z';
            n--;
            k -= 26;
        }
        if(n > 0) {
            ans[n-1] = (char)(k-n+'a');
            n--;
        }
        while(n > 0) {
            ans[n-1] = 'a';
            n--;
        }
        return String.valueOf(ans);
    }
    
    public String getSmallestString4(int n, int k) {
        StringBuilder ans = new StringBuilder();
        int diff = k - n;
        int quotient = diff / 25, remainder = diff % 25;
        if(remainder > 0) {
            for(int i = 0; i < n-quotient-1; i++) {
                ans.append('a');
            }
            ans.append((char)(remainder+'a'));
        } else {
            for(int i = 0; i < n-quotient; i++) {
                ans.append('a');
            }
        }
        for(int i = 0; i < quotient; i++) {
            ans.append('z');
        }
        return ans.toString();
    }
}
