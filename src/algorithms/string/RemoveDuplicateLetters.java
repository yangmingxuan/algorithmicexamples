package algorithms.string;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/***
 * Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

 

    Example 1:
    
    Input: s = "bcabc"
    Output: "abc"
    Example 2:
    
    Input: s = "cbacdcbc"
    Output: "acdb"
     
    
    Constraints:
    
    1 <= s.length <= 104
    s consists of lowercase English letters.
 
 * @author michel
 *
 */
public class RemoveDuplicateLetters {

    /***
     * Explanation: 把还没有在栈中的字母依次压入栈中，并保证顺序小字母比大与它且后续还存在与字符串中的字母先入栈
     * Push the letters that are not yet in the stack into the stack in turn, 
     * and make sure that the smaller letters are on the stack first than the letters that are larger and still exist in the subsequent string.
     * 
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        int[] letterCount = new int[26];
        Deque<Character> stack = new LinkedList<Character>();
        boolean[] letterInStack = new boolean[26];
        char[] schar = s.toCharArray();
        
        //count the letter
        for(char ch : schar) {
            letterCount[ch-'a']++;
        }
        
        for(char ch : schar) {
            letterCount[ch-'a']--;  //If count==0 means there is no this letter after
            if(!letterInStack[ch-'a']) { //the letter not in the stack
                
                while(!stack.isEmpty() && stack.peekLast() > ch && letterCount[stack.peekLast()-'a'] > 0) {
                    //If the letter in the stack is larger than the current one and still exists in the following string(count>0), 
                    //then pop the letter from stack first and push the current letter into stack
                    char popchar = stack.pollLast();
                    letterInStack[popchar-'a'] = false;
                }
                
                stack.add(ch);
                letterInStack[ch-'a'] = true;
            }
        }
        
        StringBuilder ans = new StringBuilder();
        for(char ch : stack) {
            ans.append(ch);
        }
        
        return ans.toString();
    }

}
