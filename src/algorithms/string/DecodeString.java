package algorithms.string;

/***
 * Given an encoded string, return its decoded string.

    The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
    
    You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
    
    Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
    
     
    
    Example 1:
    
    Input: s = "3[a]2[bc]"
    Output: "aaabcbc"
    Example 2:
    
    Input: s = "3[a2[c]]"
    Output: "accaccacc"
    Example 3:
    
    Input: s = "2[abc]3[cd]ef"
    Output: "abcabccdcdcdef"
    Example 4:
    
    Input: s = "abc3[cd]xyz"
    Output: "abccdcdcdxyz"
 * @author miche
 *
 */
public class DecodeString {

    int index = 0;
    public String decodeString(String s) {
        if(s == null || s.isEmpty()) return s;
        StringBuilder sb = new StringBuilder();
        while(index < s.length()) {
            if(Character.isDigit(s.charAt(index))) {
                sb.append(digitString(s));
            } else {
                sb.append(s.charAt(index));
                index++;
            }
        }
        
        return sb.toString();
    }

    public String digitString(String s) {
        int num = 0;
        boolean firstNum = true;
        StringBuilder sb = new StringBuilder();
        while(index < s.length() && s.charAt(index) != ']') {
            if(Character.isDigit(s.charAt(index))) {
                if(firstNum) {
                    num = num * 10 + (s.charAt(index) - '0');
                    index++;
                } else {
                    sb.append(digitString(s));
                }
            } else if(s.charAt(index) == '[') {
                firstNum = false;
                index++;
            } else {
                sb.append(s.charAt(index));
                index++;
            }
        }
        if(index < s.length()) {
            //s.charAt(index) == ']'
            index++;
        }
        return getRepeatString(num, sb);
    }

    public String getRepeatString(int repeat, StringBuilder s) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < repeat; i++) {
            sb.append(s);
        }
        return sb.toString();
    }
}
