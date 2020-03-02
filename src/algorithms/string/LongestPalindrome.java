package algorithms.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

    Example 1:
    
    Input: "babad"
    Output: "bab"
    Note: "aba" is also a valid answer.
    Example 2:
    
    Input: "cbbd"
    Output: "bb"
 * @author miche
 *
 */
public class LongestPalindrome {

    public LongestPalindrome() {
        // TODO Auto-generated constructor stub
    }

    public String longestPalindrome(String s) {
        if(s == null || s.length() < 2) {
            return s;
        }
        int start = 0, end = 0, len, len1, len2;
        for(int i = 0; i < s.length(); i++) {
            len1 = aroundLen(s, i, i);
            len2 = aroundLen(s, i, i+1);
            len = Math.max(len1, len2);
            if(len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end+1);
    }

    public int aroundLen(String s, int left, int right) {
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static void main(String argv[]) {
        LongestPalindrome lp = new LongestPalindrome();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            if ((line = in.readLine()) != null) {
                System.out.println(lp.longestPalindrome(line));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
