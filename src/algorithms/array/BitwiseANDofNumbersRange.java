package algorithms.array;
/***
 * Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.



    Example 1:
    
    Input: left = 5, right = 7
    Output: 4
    Example 2:
    
    Input: left = 0, right = 0
    Output: 0
    Example 3:
    
    Input: left = 1, right = 2147483647
    Output: 0
    
    
    Constraints:
    
    0 <= left <= right <= 2^31 - 1

 * @author miche
 *
 */
public class BitwiseANDofNumbersRange {

    /*
    public int rangeBitwiseAnd(int left, int right) {
        if(left == 0 || right == 0) return 0;
        int a = (int)(Math.log(left) / Math.log(2));
        int b = (int)(Math.log(right) / Math.log(2));
        //如果两个数字跨越了一个2的N次幂，那么必然有数字1后面全部是0的数字，那么按位做与运算必然为0.
        //If two numbers span a power of two's NTH power, there must be a number is 1 followed by all 0s, and then the bitwise AND must be zero.
        if(b-a > 0) return 0;
        int ans = left;
        for(int x = left+1; x <= right; x++) {
            ans = ans & x;
        }
        return ans;
    }
    */
    public int rangeBitwiseAnd(int left, int right) {
        int count = 0;
        while (left != right) {
            left = left >> 1;
            right = right >> 1;
            count++;
        }
        return left << count;
    }

}
