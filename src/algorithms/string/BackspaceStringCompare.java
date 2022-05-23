package algorithms.string;

/***
 * Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.

    Note that after backspacing an empty text, the text will continue empty.
    
     
    
    Example 1:
    
    Input: s = "ab#c", t = "ad#c"
    Output: true
    Explanation: Both s and t become "ac".
    Example 2:
    
    Input: s = "ab##", t = "c#d#"
    Output: true
    Explanation: Both s and t become "".
    Example 3:
    
    Input: s = "a#c", t = "b"
    Output: false
    Explanation: s becomes "c" while t becomes "b".
     
    
    Constraints:
    
    1 <= s.length, t.length <= 200
    s and t only contain lowercase letters and '#' characters.
     
    
    Follow up: Can you solve it in O(n) time and O(1) space?
 * @author miche
 *
 */
public class BackspaceStringCompare {

    public boolean backspaceCompare(String s, String t) {
        int ptrS = s.length()-1, ptrT = t.length()-1;
        int backS = 0, backT = 0;
        while(ptrS >= 0 || ptrT >= 0) {
            while(ptrS >= 0) {
                if(s.charAt(ptrS) == '#') {
                    backS++;
                    ptrS--;
                } else if(backS > 0) {
                    backS--;
                    ptrS--;
                } else {
                    break;
                }
            }
            while(ptrT >= 0) {
                if(t.charAt(ptrT) == '#') {
                    backT++;
                    ptrT--;
                } else if(backT > 0) {
                    backT--;
                    ptrT--;
                } else {
                    break;
                }
            }
            
            if((ptrS >= 0) != (ptrT >= 0)) return false;
            if(ptrS >= 0 && ptrT >= 0 && s.charAt(ptrS) != t.charAt(ptrT)) return false;
            ptrS--;
            ptrT--;
        }
        return true;
    }

}
