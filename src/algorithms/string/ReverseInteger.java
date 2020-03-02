package algorithms.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * Given a 32-bit signed integer, reverse digits of an integer.

    Example 1:
    
    Input: 123
    Output: 321
    Example 2:
    
    Input: -123
    Output: -321
    Example 3:
    
    Input: 120
    Output: 21

    Note:
    Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. 
    For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 * @author michel
 *
 */
public class ReverseInteger {

    public ReverseInteger() {
        // TODO Auto-generated constructor stub
    }

    /***
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Reverse Integer.
     * Memory Usage: 33.5 MB, less than 11.66% of Java online submissions for Reverse Integer.
     * @param x
     * @return
     */
    public int reverse(int x) {
        int a,b, c=0;
        boolean isminus = false;
        if(x < 0) isminus = true;
        a = Math.abs(x);
        if(a >=  Integer.MAX_VALUE) return 0;
        while(a > 0) {
            b = a % 10;
            a = a / 10;
            if(c > (Integer.MAX_VALUE - b)/10) return 0;
            c = c * 10 + b;
        }
        if(isminus) c *= -1;
        return c;
    }

    /***
     * 
     * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

        Example 1:
        
        Input: 121
        Output: true
        Example 2:
        
        Input: -121
        Output: false
        Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
        Example 3:
        
        Input: 10
        Output: false
        Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

     * Runtime: 6 ms, faster than 100.00% of Java online submissions for Palindrome Number.
     * Memory Usage: 36.2 MB, less than 5.02% of Java online submissions for Palindrome Number.
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if(x < 0 || x >= Integer.MAX_VALUE) return false;
        int a,b,c=0;
        a = x;
        while(a > 0) {
            b = a % 10;
            a /=  10;
            if((c > Integer.MAX_VALUE / 10) || (c == Integer.MAX_VALUE / 10) && b > 7) {
                c = Integer.MAX_VALUE;
                break;
            }
            c = c*10 + b;
        }
        if(x == c) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String argv[]) throws NumberFormatException, IOException {
        ReverseInteger ri = new ReverseInteger();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int x = Integer.parseInt(line);
            System.out.print(ri.reverse(x));
        }
    }
}
