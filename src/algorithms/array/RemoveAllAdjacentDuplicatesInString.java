package algorithms.array;

/***
 * You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing two adjacent and equal letters and removing them.

    We repeatedly make duplicate removals on s until we no longer can.
    
    Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.
    
     
    
    Example 1:
    
    Input: s = "abbaca"
    Output: "ca"
    Explanation: 
    For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
    Example 2:
    
    Input: s = "azxxzy"
    Output: "ay"
     
    
    Constraints:
    
    1 <= s.length <= 10^5
    s consists of lowercase English letters.
 * @author miche
 *
 */
public class RemoveAllAdjacentDuplicatesInString {

    public String removeDuplicates(String s) {
        /*
        StringBuilder sb = new StringBuilder();
        int[] sc = new int[sb.length()];
        int count = 1;
        for(int i = 0; i < sb.length(); i++) {
            if(i > 0 && sb.charAt(i) == sb.charAt(i-1)) {
                count = sc[i-1] + 1;
            } else if(count > 1) {
                sb.delete(i-count, i);
                i = i-count-1;
                if(i < 0) i = 0;
                count = 1;
            }
            sc[i] = count;
        }
        if(count > 1) sb.delete(sb.length()-count, sb.length());
        return sb.toString();
        */
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(sb.length() > 0 && s.charAt(i) == sb.charAt(sb.length()-1)) {
                sb.deleteCharAt(sb.length()-1);
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public String removeDuplicates2(String s) {
        char[] cs = s.toCharArray();
        int slow = 0, fast = 0;
        while(fast < s.length()) {
            if(slow > 0 && cs[fast] == cs[slow-1]) {
                slow--;
            } else {
                cs[slow] = cs[fast];
                slow++;
            }
            fast++;
        }
        return new String(cs, 0, slow);
    }
}
