package algorithms.string;

/***
 * Given two binary strings, return their sum (also a binary string).

    The input strings are both non-empty and contains only characters 1 or 0.
    
    Example 1:
    
    Input: a = "11", b = "1"
    Output: "100"
    Example 2:
    
    Input: a = "1010", b = "1011"
    Output: "10101"
     
    
    Constraints:
    
    Each string consists only of '0' or '1' characters.
    1 <= a.length, b.length <= 10^4
    Each string is either "0" or doesn't contain any leading zero.
 * @author miche
 *
 */
public class AddBinary {

    public String addBinary(String a, String b) {
        if(a == null) return b;
        if(b == null) return a;
        StringBuilder ans = new StringBuilder();
        int lena = a.length() - 1, lenb = b.length() - 1;
        int carry = 0;
        while(lena >= 0 || lenb >= 0) {
            int x1 = lena >=0 ? a.charAt(lena) - '0' : 0;
            int x2 = lenb >=0 ? b.charAt(lenb) - '0' : 0;
            int val = x1 + x2 + carry;
            carry = val / 2;
            val %= 2;
            ans.append(val);
            lena--;
            lenb--;
        }
        
        if(carry > 0) {
            ans.append(carry);
        }
        
        return ans.reverse().toString();
    }

}
