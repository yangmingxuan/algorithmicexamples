package algorithms.string;

/***
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

    Example 1:
    Input: "aba"
    Output: True
    Example 2:
    Input: "abca"
    Output: True
    Explanation: You could delete the character 'c'.
    Note:
    The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 * @author miche
 *
 */
public class ValidPalindromeII {

    public boolean validPalindrome(String s) {
        if (s == null) return false;
        for(int i = 0; i < s.length() / 2; i++) {
            if(s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                int j = s.length() - 1 -i;
                return isValidPalindrome(s, i, j - 1) || isValidPalindrome(s, i+1, j);
            }
        }
        return true;
    }

    public boolean isValidPalindrome(String s, int i, int j) {
        for(int k = i; k <= (i+j)/2; k++) {
            if(s.charAt(k) != s.charAt(j-k+i)) return false;
        }
        return true;
    }

    public boolean validPalindrome2(String s) {
        if (s == null) return false;
        return checkValidPalidrome(s, 0, s.length() - 1, false);
    }
    
    public boolean checkValidPalidrome(String s, int i, int j, boolean deleteOnce) {
        while(i < j) {
            if(s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                if(deleteOnce) {
                    return false;
                }
                return checkValidPalidrome(s, i, j-1, true) || checkValidPalidrome(s, i+1, j, true);
            }
        }
        return true;
    }
}
