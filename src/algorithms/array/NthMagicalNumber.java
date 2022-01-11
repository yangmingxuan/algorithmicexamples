package algorithms.array;

/***
 * A positive integer is magical if it is divisible by either a or b.

    Given the three integers n, a, and b, return the nth magical number. Since the answer may be very large, return it modulo 10^9 + 7.
    
     
    
    Example 1:
    
    Input: n = 1, a = 2, b = 3
    Output: 2
    Example 2:
    
    Input: n = 4, a = 2, b = 3
    Output: 6
    Example 3:
    
    Input: n = 5, a = 2, b = 4
    Output: 10
    Example 4:
    
    Input: n = 3, a = 6, b = 4
    Output: 8
     
    
    Constraints:
    
    1 <= n <= 10^9
    2 <= a, b <= 4 * 10^4
 * @author miche
 *
 */
public class NthMagicalNumber {

    
    public int nthMagicalNumber(int n, int a, int b) {
        int MOD = 1000000007;
        int gcd = GCD(a,b);
        int lcm = a/gcd * b; //Least common multiple 最小公倍数
        int m = lcm /a + lcm /b - 1; //There are several magical numbers for each least common multiple period
        long ans = 0;
        
        int k = n / m, l = n % m;
        ans = (long)k * lcm;
        if(l == 0) {
            return (int)(ans%MOD);
        }
        int i = 0, j = 0;
        long tmp = 0;
        while(i+j < l) {
            if((i+1) * a < (j+1) * b) {
                tmp = (long)(i+1) * a;
                i++;
            } else {
                tmp = (long)(j+1) * b;
                j++;
            }
        }
        ans += tmp;
        
        return (int)(ans%MOD);
    }
    
    public int nthMagicalNumber2(int n, int a, int b) {
        int MOD = 1000000007;
        int gcd = GCD(a,b);
        int lcm = a/gcd * b; //Least common multiple 最小公倍数
        
        long lo = 0, hi = (long)n * Math.min(a, b);
        while(lo < hi) {
            long mid = lo + (hi-lo) / 2;
            //If there are not enough magic numbers below mid
            if(mid/a + mid/b - mid/lcm < n) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return (int)(lo%MOD);
    }

    /***
     * Greatest common divisor 最大公约数
     * @param x
     * @param y
     * @return gcd
     */
    private int GCD(int x, int y) {
        if(x == 0) return y;
        return GCD(y%x, x);
    }
}
