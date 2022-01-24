package algorithms.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * Note:

    Only the space character ' ' is considered as whitespace character.
    Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
    Example 1:
    
    Input: "42"
    Output: 42
    Example 2:
    
    Input: "   -42"
    Output: -42
    Explanation: The first non-whitespace character is '-', which is the minus sign.
                 Then take as many numerical digits as possible, which gets 42.
    Example 3:
    
    Input: "4193 with words"
    Output: 4193
    Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
    Example 4:
    
    Input: "words and 987"
    Output: 0
    Explanation: The first non-whitespace character is 'w', which is not a numerical 
                 digit or a +/- sign. Therefore no valid conversion could be performed.
    Example 5:
    
    Input: "-91283472332"
    Output: -2147483648
    Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
                 Thefore INT_MIN (−2^31) is returned.
 * @author miche
 *
 */
public class StringtoInt {

    /***
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for String to Integer (atoi).
     * Memory Usage: 35.8 MB, less than 100.00% of Java online submissions for String to Integer (atoi).
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        
        char[] sary;
        int ret = 0;
        boolean isminus = false;
        if(str.trim().startsWith("-")) {
            sary = str.trim().substring(1).toCharArray();
            isminus = true;
        } else if(str.trim().startsWith("+")) {
            sary = str.trim().substring(1).toCharArray();
        } else {
            sary = str.trim().toCharArray();
        }
        for(char c : sary) {
            if(c >= '0' && c <= '9') {
                if(ret > Integer.MAX_VALUE / 10) {
                    if(isminus) {
                        return Integer.MIN_VALUE;
                    } else {
                        return Integer.MAX_VALUE;
                    }
                }
                if((ret == Integer.MAX_VALUE / 10) && c > '7' && !isminus) {
                    return Integer.MAX_VALUE;
                }
                if((ret == Integer.MAX_VALUE / 10) && c > '7' && isminus) {
                    return Integer.MIN_VALUE;
                }
                ret = ret * 10 + (c - '0');
            } else {
                break;
            }
        }
        if(isminus) ret *= -1;
        
        return ret;
    }

    public static void main(String argv[]) throws IOException {
        StringtoInt si = new StringtoInt();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.print(si.myAtoi(line));
        }
    }

}
