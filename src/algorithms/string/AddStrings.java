package algorithms.string;

/***
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

    Note:
    
    The length of both num1 and num2 is < 5100.
    Both num1 and num2 contains only digits 0-9.
    Both num1 and num2 does not contain any leading zero.
    You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * @author miche
 *
 */
public class AddStrings {

    public String addStrings(String num1, String num2) {
        if(num1 == null) return num2;
        if(num2 == null) return num1;
        
        StringBuilder ans = new StringBuilder();
        int carry = 0;
        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;
        while(len1 >= 0 || len2 >= 0) {
            int x1 = len1 >= 0 ? num1.charAt(len1) - '0' : 0;
            int x2 = len2 >= 0 ? num2.charAt(len2) - '0' : 0;
            int val = x1 + x2 + carry;
            carry = val / 10;
            val %= 10;
            ans.append(val);
            len1--;
            len2--;
        }
        
        if(carry > 0) {
            ans.append(carry);
        }
        
        return ans.reverse().toString();
    }

}
