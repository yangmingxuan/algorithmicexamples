package algorithms.string;

/***
 * Given a string s, return the last substring of s in lexicographical order.

 

    Example 1:
    
    Input: "abab"
    Output: "bab"
    Explanation: The substrings are ["a", "ab", "aba", "abab", "b", "ba", "bab"]. The lexicographically maximum substring is "bab".
    Example 2:
    
    Input: "leetcode"
    Output: "tcode"
     
    
    Note:
    
    1 <= s.length <= 4 * 10^5
    s contains only lowercase English letters.
 * @author miche
 *
 */
public class LastSubstringinLexicographicalOrder {

    /***
     * Explanation: 要找排序最大的子串，就是找字符串中排序最大的字符，存在相同的多个最大的字符时，比较后面的第二个字符，
     * B: 以此类推，直到最后
     * To find the lexicographically maximum substring is to find the highest sorted character in the string. 
     * When there are multiple largest characters in the same string, compare the second character after that, and so on, until the end
     * @param s
     * @return
     */
    public String lastSubstring(String s) {
        if(s == null || s.length() == 0) return s;
        int slow = 0, fast = 1, len = 0;
        while(fast+len < s.length()) {
            if(s.charAt(slow+len) == s.charAt(fast+len)) {
                //if equal, compare the next character
                len++;
            } else if(s.charAt(slow+len) > s.charAt(fast+len)) {
                //the slow character is higher than fast one, compare the next first character
                fast += (len+1);
                len = 0;
            } else {
                //the fast character is higher than slow one, compare the next sub string
                slow = fast;
                fast = slow + 1;
                len = 0;
            }
        }
        return s.substring(slow);
    }

}
