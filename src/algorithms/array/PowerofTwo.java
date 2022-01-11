package algorithms.array;

/***
 * Given an integer n, return true if it is a power of two. Otherwise, return false.

    An integer n is a power of two, if there exists an integer x such that n == 2^x.
    
     
    
    Example 1:
    
    Input: n = 1
    Output: true
    Explanation: 20 = 1
    Example 2:
    
    Input: n = 16
    Output: true
    Explanation: 24 = 16
    Example 3:
    
    Input: n = 3
    Output: false
     
    
    Constraints:
    
    -2^31 <= n <= 2^31 - 1
     
    
    Follow up: Could you solve it without loops/recursion?
 * @author miche
 *
 */
public class PowerofTwo {

    public boolean isPowerOfTwo(int n) {
        /*
        if(n == 1) return true;
        if(n <= 0 || n % 2 != 0) return false;
        return isPowerOfTwo(n/2);
        */
        // 2^x always 100000....(x 0s)
        return n > 0 && (n & (n-1)) == 0;
    }

}
