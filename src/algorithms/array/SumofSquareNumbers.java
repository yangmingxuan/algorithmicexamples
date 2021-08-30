package algorithms.array;

/***
 * Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.

 

    Example 1:
    
    Input: c = 5
    Output: true
    Explanation: 1 * 1 + 2 * 2 = 5
    Example 2:
    
    Input: c = 3
    Output: false
    Example 3:
    
    Input: c = 4
    Output: true
    Example 4:
    
    Input: c = 2
    Output: true
    Example 5:
    
    Input: c = 1
    Output: true
     
    
    Constraints:
    
    0 <= c <= 231 - 1
 * @author miche
 *
 */
public class SumofSquareNumbers {

    public boolean judgeSquareSum(int c) {
        for(long a = 0; a*a <= c; a++) {
            //double b = Math.sqrt(c-a*a);
            //if(b == (int)b) return true;
            if(isSquare(c-a*a)) return true;
        }
        return false;
    }

    public boolean isSquare(long b) {
        //if(b < 0) return false;
        if(b < 2) return true;
        long l = 2, r = b/2;
        while(l <= r) {
            long mid = (l+r) / 2;
            long qd = mid*mid;
            if(qd == b) return true;
            if(qd < b) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }
}
