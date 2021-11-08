package algorithms.string;

import java.util.ArrayList;

/***
 * Given an input string s, reverse the order of the words.

    A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
    
    Return a string of the words in reverse order concatenated by a single space.
    
    Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
    
     
    
    Example 1:
    
    Input: s = "the sky is blue"
    Output: "blue is sky the"
    Example 2:
    
    Input: s = "  hello world  "
    Output: "world hello"
    Explanation: Your reversed string should not contain leading or trailing spaces.
    Example 3:
    
    Input: s = "a good   example"
    Output: "example good a"
    Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
    Example 4:
    
    Input: s = "  Bob    Loves  Alice   "
    Output: "Alice Loves Bob"
    Example 5:
    
    Input: s = "Alice does not even like bob"
    Output: "bob like even not does Alice"
     
    
    Constraints:
    
    1 <= s.length <= 104
    s contains English letters (upper-case and lower-case), digits, and spaces ' '.
    There is at least one word in s.
 
 * @author miche
 *
 */
public class ReverseWordsinaString {

    public String reverseWords(String s) {
        ArrayList<String> sps = new ArrayList<String>();
        boolean isinword = false;
        StringBuilder word = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ' ') {
                if(isinword) {
                    sps.add(word.toString());
                    word = new StringBuilder();
                    isinword = false;
                }
            } else {
                word.append(s.charAt(i));
                isinword = true;
            }
        }
        if(isinword) sps.add(word.toString());
        if(sps.size() == 0) return "";
        StringBuilder ans = new StringBuilder();
        ans.append(sps.get(sps.size()-1));
        for(int i = sps.size()-2; i >= 0; i--) {
            ans.append(' ');
            ans.append(sps.get(i));
        }
        return ans.toString();
    }

    public String reverseWords2(String s) {
        char[] sc = s.toCharArray();
        StringBuilder ans = new StringBuilder();
        for(int i = sc.length-1; i >= 0; i--) {
            if(sc[i] == ' ') continue;
            int lastindex = i;
            while(i >= 0 && sc[i] != ' ') {
                i--;
            }
            for(int j = i+1; j <= lastindex; j++) {
                ans.append(sc[j]);
            }
            ans.append(' ');
        }
        ans.deleteCharAt(ans.length()-1);
        return ans.toString();
    }
}
