package algorithms.string;

/***
 * Given a string, your task is to count how many palindromic substrings in this string.

    The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
    
    Example 1:
    
    Input: "abc"
    Output: 3
    Explanation: Three palindromic strings: "a", "b", "c".
     
    
    Example 2:
    
    Input: "aaa"
    Output: 6
    Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
     
    
    Note:
    
    The input string length won't exceed 1000.
 * @author miche
 *
 */
public class PalindromicSubstrings {

    public int countSubstrings(String S) {
        int N = S.length(), ans = 0;
        /***
         * Judging whether it is a palindrome from the nth character or the nth and n+1 characters of the string,
         * and extending to the left and right sides to judge whether it is a palindrome.
         */
        for (int center = 0; center < 2*N; center++) {
            int left = center / 2;          //left = 0 to n-1
            int right = left + center % 2;  //right = left or left+1
            while (left >= 0 && right < N && S.charAt(left) == S.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }
        return ans;
    }

}
