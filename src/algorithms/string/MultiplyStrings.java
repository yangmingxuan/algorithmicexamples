package algorithms.string;

/***
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

    Example 1:
    
    Input: num1 = "2", num2 = "3"
    Output: "6"
    Example 2:
    
    Input: num1 = "123", num2 = "456"
    Output: "56088"
    Note:
    
    The length of both num1 and num2 is < 110.
    Both num1 and num2 contain only digits 0-9.
    Both num1 and num2 do not contain any leading zero, except the number 0 itself.
    You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * @author miche
 *
 */
public class MultiplyStrings {

    public MultiplyStrings() {
        // TODO Auto-generated constructor stub
    }

    /***
     * Traditional vertical calculation
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        String ans = "0";
        if("0".equals(num1)) return ans;
        for(int i = num2.length()-1; i >= 0; i--) {
            int multiplicand = num2.charAt(i) - '0';
            if(multiplicand == 0) continue;
            StringBuilder singleProduct = new StringBuilder();
            int carry = 0;

            //Calculate the product of each bit multiply num1
            for(int j = num1.length() - 1; j >= 0; j--) {
                int multiplier = num1.charAt(j) - '0';
                int eachProduct = multiplicand * multiplier + carry;
                int t = eachProduct % 10;
                carry = eachProduct / 10;
                singleProduct.insert(0, t);
            }
            if(carry >0) singleProduct.insert(0, carry);
            carry = 0;

            //add the singleProduct and ans
            if(i < num2.length()-1) {
                String sgpdt = singleProduct.toString();
                StringBuilder sbsum = new StringBuilder();
                int shift = num2.length()-1 - i;
                if(shift >= ans.length()) {
                    for(int j = 0; j < shift - ans.length(); j++) {
                        sbsum.append('0');
                    }
                    sbsum.append(ans);
                } else {
                    sbsum.append(ans.substring(ans.length() - shift));
                }
                int cur = sgpdt.length() - 1;
                shift = ans.length()-1 - shift;
                while(shift >= 0 || cur >= 0) {
                    int ans1 = 0, sgpdt1 = 0;
                    if(shift >= 0) {
                        ans1 = ans.charAt(shift) - '0';
                    } else {
                        ans1 = 0;
                    }
                    if(cur >= 0) {
                        sgpdt1 = sgpdt.charAt(cur) - '0';
                    } else {
                        sgpdt1 = 0;
                    }
                    int sum1 = ans1 + sgpdt1 + carry;
                    int t = sum1 % 10;
                    carry = sum1 / 10;
                    sbsum.insert(0, t);
                    shift-- ;
                    cur--;
                }
                if(carry >0) sbsum.insert(0, carry);
                carry = 0;
                ans = sbsum.toString();
            } else {
                ans = singleProduct.toString();
            }
            
        }

        return ans;
    }

    /***
     * Horizontal and vertical slash calculation
     * @param num1
     * @param num2
     * @return
     */
    public String multiply2(String num1, String num2) {
        if("0".equals(num1) || "0".equals(num2)) return "0";
        int[] sumAll = new int[num1.length() + num2.length() - 1];
        for(int i = 0; i < num1.length(); i++) {
            for(int j = 0; j < num2.length(); j++) {
                sumAll[i+j] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }
        for(int i = sumAll.length - 1; i > 0; i--) {
            sumAll[i-1] += sumAll[i] /10;
            sumAll[i] %= 10;
        }
        StringBuilder product = new StringBuilder();
        for(int i = 0; i < sumAll.length; i++) {
            product.append(sumAll[i]);
        }
        return product.toString();
    }

    public static void main(String[] argv) {
        String num1 = "98765432123456789876504321", num2 = "1234567890987654321234567890";
        MultiplyStrings ms = new MultiplyStrings();
        System.out.println(ms.multiply2(num1, num2));
    }
}
